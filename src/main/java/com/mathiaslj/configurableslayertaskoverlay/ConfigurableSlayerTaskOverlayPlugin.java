/*
 * Copyright (c) 2022, BrastaSauce
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.mathiaslj.configurableslayertaskoverlay;

import javax.inject.Inject;

import com.mathiaslj.configurableslayertaskoverlay.models.SlayerTask;
import com.mathiaslj.configurableslayertaskoverlay.utils.SlayerTaskOverlay;
import com.mathiaslj.configurableslayertaskoverlay.utils.SlayerTaskWorldMapPoint;
import com.google.inject.Provides;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.MenuAction;
import net.runelite.api.NPC;
import net.runelite.api.Player;
import net.runelite.api.Tile;
import net.runelite.api.WorldView;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.npcoverlay.NpcOverlayService;
import net.runelite.client.events.PluginMessage;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.worldmap.WorldMapPointManager;
import net.runelite.client.util.Text;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.RenderOverview;
import net.runelite.api.MenuEntry;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@PluginDescriptor(
        name = "Configurable Slayer Task Overlay",
        description = "Configure overlay and map locations to display when receiving slayer task",
        tags = {"slayer", "overlay", "task", "configurable", "world icon", "shortest path"}
)
public class ConfigurableSlayerTaskOverlayPlugin extends Plugin {
    private static final Pattern SLAYER_ASSIGN_MESSAGE = Pattern.compile(".*new task is to kill \\d+ (?<name>.+?)s?\\..*");
    private static final Pattern SLAYER_CURRENT_MESSAGE = Pattern.compile(".*still hunting (?<name>.+?)s?[,;].*");
    private static final Pattern SLAYER_CURRENT_CHAT_MESSAGE = Pattern.compile("You're assigned to kill (?<name>.+?)s?[,;] only \\d+ more to go\\.");
    private static final Pattern KONAR_CHAT_PATTERN = Pattern.compile(".+ bring(?:ing)? balance to (?:\\d+ )?(?<name>.+?)s?(?:,|;|in).+");

    private long taskStartTime = 0;

    private final Set<NPC> targets = new HashSet<>();

    private final String DEBUG_MENU_WORLD_POINT_ONE = "Set WorldPoint1 (Turael Skipping)";
    private final String DEBUG_MENU_WORLD_POINT_TWO = "Set WorldPoint2 (Turael Skipping)";
    private final String DEBUG_MENU_RESET_WORLD_POINTS = "Reset WorldPoints (Turael Skipping)";
    private final String DEBUG_MENU_COPY_TO_CLIPBOARD = "Copy WorldPoints to clipboard (Turael Skipping)";

    private WorldPoint debugWorldPointOne;
    private WorldPoint debugWorldPointTwo;
    private SlayerTaskRegistry slayerTaskRegistry;

    @Inject
    private Client client;

    @Inject
    private ConfigurableSlayerTaskOverlayConfig config;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private NpcOverlayService npcOverlayService;

    @Inject
    private WorldMapPointManager worldMapPointManager;

    @Inject
    private SlayerTaskOverlay slayerTaskOverlay;

    @Inject
    private EventBus eventBus;

    @Inject
    private ConfigManager configManager;

    @Getter
    private SlayerTask currentSlayerTask;

    @Override
    protected void startUp() {
        overlayManager.add(slayerTaskOverlay);

        slayerTaskRegistry = new SlayerTaskRegistry(config, () -> {
            String saved = configManager.getConfiguration("configurable-slayer-task-overlay", "savedTaskLocations");
            return parseSavedLocations(saved);
        });
    }

    @Override
    protected void shutDown() {
        overlayManager.remove(slayerTaskOverlay);
        worldMapPointManager.removeIf(SlayerTaskWorldMapPoint.class::isInstance);

        completeTask();
    }

    @Subscribe
    public void onGameTick(GameTick gameTick) {
        // Check if task has timed out
        if (currentSlayerTask != null && hasTaskTimedOut()) {
            completeTask();
            return;
        }

        Widget chatBoxNpcName = client.getWidget(InterfaceID.ChatLeft.NAME);
        Widget chatBoxNpcText = client.getWidget(InterfaceID.ChatLeft.TEXT);

        // Check if current widget is a slayer master
        if (chatBoxNpcName != null && chatBoxNpcText != null &&
                (chatBoxNpcName.getText().equalsIgnoreCase("turael") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("aya") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("spria") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("krystilia") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("mazchna") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("achtryn") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("vannaka") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("chaeldar") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("konar quo maten") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("nieve") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("steve") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("duradel") ||
                        chatBoxNpcName.getText().equalsIgnoreCase("kuradel")
                )
        ) {
            String npcText = Text.sanitizeMultilineText(chatBoxNpcText.getText());
            String taskName = getTaskName(npcText);

            if (taskName != null) {
                startTask(taskName);
            }
        }
    }

    @Subscribe
    public void onChatMessage(ChatMessage event) {
        if (event.getType() != ChatMessageType.GAMEMESSAGE) {
            return;
        }

        String chatMessage = Text.removeTags(event.getMessage());

        if (currentSlayerTask == null) {
            String taskName = getTaskName(chatMessage);

            if (taskName != null) {
                startTask(taskName);
            }
        } else {
            if (chatMessage.startsWith("You've completed") && chatMessage.toLowerCase().contains("slayer master")) {
                completeTask();
            }
        }
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event) {
        // Ignore changes from other plugins
        if (!event.getGroup().equals(ConfigurableSlayerTaskOverlayConfig.CONFIG_GROUP_NAME)) {
            return;
        }

        if (event.getKey().equals("displayMapIcon"))
        {
            updateWorldMapIcons();
        }
        if (event.getKey().equals("useShortestPath"))
        {
            updateShortestPath();
        }
        if (event.getKey().endsWith("DisableBox"))
        {
            configManager.setConfiguration(
                    "configurable-slayer-task-overlay",
                    event.getKey(),
                    event.getNewValue()
            );
        }

        if (currentSlayerTask != null)
        {
            String taskName = currentSlayerTask.getName();
            String nameToCheck = taskName.replaceAll("\\s+", "") + "info";
                slayerTaskRegistry.rebuildTasks();
                // Refresh current task if one is active
                this.currentSlayerTask = slayerTaskRegistry.getSlayerTaskByNpcName(taskName);
        }

        // Set a dummy task
        if (event.getKey().equals("debugTask")) {
            if (event.getNewValue() == null) {
                return;
            }

            // Always clear task to reset area outline/tagged NPC's
            this.completeTask();

            if (!event.getNewValue().equals("None")) {
                this.startTask(event.getNewValue().toLowerCase().replace("_", " ").replaceFirst("s$", ""));
            }
        }
    }

    @Subscribe
    public void onMenuOpened(MenuOpened event) {
        if (currentSlayerTask == null) {
            return;
        }
        /*
        if (!config.enableWorldPointSelector()) {
            return;
        }

        // Only add the menu entry when you can walk, so it doesn't get added when you are right-clicking in the bank
        if (menuEntryAdded.getOption().equals("Walk here")) {
            // Add options in reverse, so it shows up correctly in the right click menu
            client.getMenu()
                    .createMenuEntry(-1)
                    .setOption(DEBUG_MENU_RESET_WORLD_POINTS)
                    .setTarget(menuEntryAdded.getTarget())
                    .setType(MenuAction.RUNELITE)
                    .onClick(menuEntry -> {
                        debugWorldPointOne = null;
                        debugWorldPointTwo = null;
                    });

            client.getMenu()
                    .createMenuEntry(-1)
                    .setOption(DEBUG_MENU_COPY_TO_CLIPBOARD)
                    .setTarget(menuEntryAdded.getTarget())
                    .setType(MenuAction.RUNELITE)
                    .onClick(menuEntry -> {
                        if (debugWorldPointOne != null && debugWorldPointTwo != null) {
                            String copyString = "new WorldPoint(" + debugWorldPointOne.getX() + ", " + debugWorldPointOne.getY() + ", " + debugWorldPointOne.getPlane() + "), " +
                                    "new WorldPoint(" + debugWorldPointTwo.getX() + ", " + debugWorldPointTwo.getY() + ", " + debugWorldPointTwo.getPlane() + ")";

                            StringSelection selection = new StringSelection(copyString);
                            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                            clipboard.setContents(selection, null);

                            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "Turael Skipping", "Copied the WorldPoints to your clipboard.", "Turael Skipping");
                        }
                    });

            client.getMenu()
                    .createMenuEntry(-1)
                    .setOption(DEBUG_MENU_WORLD_POINT_TWO)
                    .setTarget(menuEntryAdded.getTarget())
                    .setType(MenuAction.RUNELITE)
                    .setIdentifier(menuEntryAdded.getIdentifier());

            client.getMenu()
                    .createMenuEntry(-1)
                    .setOption(DEBUG_MENU_WORLD_POINT_ONE)
                    .setTarget(menuEntryAdded.getTarget())
                    .setType(MenuAction.RUNELITE)
                    .setIdentifier(menuEntryAdded.getIdentifier());
        }
        */

        // Check if the menu is for the world map
        final Widget map = client.getWidget(ComponentID.WORLD_MAP_MAPVIEW);
        if (map == null)
        {
            return;
        }

        if (currentSlayerTask == null)
        {
            return;
        }

        MenuEntry[] entries = event.getMenuEntries();

        String setOption = "Set";
        String setTarget = "<col=ff9040>" + currentSlayerTask.getName() + "</col> slayer task location";

// Prevent duplicates for Set entry
        boolean hasSetEntry = false;
        for (MenuEntry entry : entries)
        {
            if (entry.getOption().equals(setOption) && entry.getTarget().equals(setTarget))
            {
                hasSetEntry = true;
                break;
            }
        }

// Always add "Set location" entry
        if (!hasSetEntry)
        {
            client.createMenuEntry(entries.length)
                    .setOption(setOption)
                    .setTarget(setTarget)
                    .setType(MenuAction.RUNELITE)
                    .setDeprioritized(true)
                    .onClick(e -> onMapClick());
        }

// Check if any entry contains "Focus on" (indicates we're hovering over an icon)
        boolean hasFocusOn = false;
        for (MenuEntry entry : entries)
        {
            if (entry.getOption().contains("Focus on"))
            {
                hasFocusOn = true;
                break;
            }
        }

// Add reset entry only if:
// 1. We're hovering over a map icon (has "Focus on")
// 2. Location has been customized (not default)
        if (hasFocusOn && hasCustomLocation(currentSlayerTask.getName()))
        {
            String resetOption = "Reset";
            String resetTarget = "<col=ff9040>" + currentSlayerTask.getName() + "</col> location to default";

            // Prevent duplicates for Reset entry
            boolean hasResetEntry = false;
            for (MenuEntry entry : entries)
            {
                if (entry.getOption().equals(resetOption) && entry.getTarget().equals(resetTarget))
                {
                    hasResetEntry = true;
                    break;
                }
            }

            if (!hasResetEntry)
            {
                client.createMenuEntry(entries.length)
                        .setOption(resetOption)
                        .setTarget(resetTarget)
                        .setType(MenuAction.RUNELITE)
                        .setDeprioritized(true)
                        .onClick(e -> resetTaskLocation(currentSlayerTask.getName()));
            }
        }
    }

    private void onMenuOption(MenuEntry entry) {

    }

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked event) {
        if (!event.getMenuOption().equals(DEBUG_MENU_WORLD_POINT_ONE) && !event.getMenuOption().equals(DEBUG_MENU_WORLD_POINT_TWO)) {
            return;
        }

        WorldView worldView = client.getLocalPlayer().getWorldView();
        Tile selectedSceneTile = worldView.getSelectedSceneTile();

        if (selectedSceneTile == null) {
            return;
        }

        if (event.getMenuOption().equals(DEBUG_MENU_WORLD_POINT_ONE)) {
            this.debugWorldPointOne = selectedSceneTile.getWorldLocation();

            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "Turael Skipping", "First WorldPoint has been selected.", "Turael Skipping");
        } else if (event.getMenuOption().equals(DEBUG_MENU_WORLD_POINT_TWO)) {
            this.debugWorldPointTwo = selectedSceneTile.getWorldLocation();

            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "Turael Skipping", "Second WorldPoint has been selected.", "Turael Skipping");
        }
    }

    @Provides
    ConfigurableSlayerTaskOverlayConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(ConfigurableSlayerTaskOverlayConfig.class);
    }

    private void startTask(String taskName) {
        SlayerTask lookupSlayerTask = slayerTaskRegistry.getSlayerTaskByNpcName(taskName);

        if (lookupSlayerTask != null) {
            this.currentSlayerTask = lookupSlayerTask;
            this.taskStartTime = System.currentTimeMillis();

            updateWorldMapIcons();
            updateShortestPath();

            // Target NPC's visible to the player in case they are already at the location
            Player player = client.getLocalPlayer();

            // Player is null when you select a task from the debug menu whe not logged in
            if (player != null) {
                WorldView worldView = player.getWorldView();

                for (NPC npc : worldView.npcs()) {
                    for (int targetNpcId : currentSlayerTask.getNpcIds()) {
                        if (npc.getId() == targetNpcId) {
                            targets.add(npc);
                        }
                    }
                }
            }
        }
    }

    private boolean hasTaskTimedOut() {
        int timeoutSeconds = config.overlayTimeout();

        if (timeoutSeconds <= 0) {
            return false; // Timeout disabled
        }

        long elapsedMs = System.currentTimeMillis() - taskStartTime;
        long timeoutMs = timeoutSeconds * 1000L;

        return elapsedMs > timeoutMs;
    }

    private void updateWorldMapIcons() {
        // Remove all existing map icons first
        worldMapPointManager.removeIf(point -> point instanceof SlayerTaskWorldMapPoint);

        // Only add icons if config is enabled and there's an active task
        if (config.enableWorldMapIcon() && currentSlayerTask != null)
        {
            for (WorldPoint worldPoint : currentSlayerTask.getWorldMapLocations())
            {
                // Skip invalid/default locations
                if (worldPoint.getX() == 0 && worldPoint.getY() == 0)
                {
                    continue;
                }

                worldMapPointManager.add(new SlayerTaskWorldMapPoint(worldPoint));

                // Check if this location is inside a dungeon
                WorldPoint entrancePoint = DungeonLocationRegistry.getEntranceFor(worldPoint);
                if (entrancePoint != null)
                {
                    // Add icon at the dungeon entrance as well
                    worldMapPointManager.add(new SlayerTaskWorldMapPoint(entrancePoint));
                }
            }
        }
    }

    private void clearShortestPath() {
        Map<String, Object> data = new HashMap<>();
        eventBus.post(new PluginMessage("shortestpath", "clear", data));
    }

    private void updateShortestPath()
    {
        if (config.useShortestPath() && currentSlayerTask != null)
        {
            WorldPoint location = currentSlayerTask.getShortestPathWorldPoint();
            setShortestPath(location);
        }
        else
        {
            clearShortestPath();
        }
    }

    private void completeTask() {
        currentSlayerTask = null;
        this.taskStartTime = 0; // Reset timer

        targets.clear();

        worldMapPointManager.removeIf(SlayerTaskWorldMapPoint.class::isInstance);
    }

    private String getTaskName(String npcText) {
        Pattern[] patterns = {SLAYER_ASSIGN_MESSAGE, SLAYER_CURRENT_MESSAGE, SLAYER_CURRENT_CHAT_MESSAGE,
                KONAR_CHAT_PATTERN};

        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(npcText);

            if (matcher.find()) {
                return matcher.group("name");
            }
        }

        return null;
    }

    private void setShortestPath(WorldPoint target) {
        if (target == null) {
            return;
        }

        Map<String, Object> data = new HashMap<>();
        data.put("target", target);
        eventBus.post(new PluginMessage("shortestpath", "path", data));
    }

    private void onMapClick() {
        // Get the world point from the map click
        final WorldPoint worldPoint = calculateMapPoint();

        if (worldPoint != null) {
            handleLocationSelected(worldPoint);
        }
    }

    private WorldPoint calculateMapPoint() {
        Widget map = client.getWidget(ComponentID.WORLD_MAP_MAPVIEW);
        if (map == null) {
            return null;
        }

        RenderOverview renderOverview = client.getRenderOverview();
        if (renderOverview == null) {
            return null;
        }

        float zoom = renderOverview.getWorldMapZoom();
        net.runelite.api.Point mapPosition = renderOverview.getWorldMapPosition();

        // Convert to WorldPoint
        WorldPoint worldMapPosition = new WorldPoint(mapPosition.getX(), mapPosition.getY(), client.getPlane());

        // Get mouse position as AWT Point
        net.runelite.api.Point mouseCanvas = client.getMouseCanvasPosition();
        java.awt.Point mousePoint = new java.awt.Point(mouseCanvas.getX(), mouseCanvas.getY());

        // Calculate the world point from mouse position
        int widgetX = mousePoint.x - map.getCanvasLocation().getX();
        int widgetY = mousePoint.y - map.getCanvasLocation().getY();

        int dx = (int) ((widgetX - map.getWidth() / 2) / zoom);
        int dy = (int) ((widgetY - map.getHeight() / 2) / zoom);

        return new WorldPoint(
                worldMapPosition.getX() + dx,
                worldMapPosition.getY() - dy,
                worldMapPosition.getPlane()
        );
    }

    private void handleLocationSelected(WorldPoint location)
    {
        log.info("Selected world map location: WorldPoint({}, {}, {})", location.getX(), location.getY(), location.getPlane());
        if (currentSlayerTask == null)
        {
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
                    "No active slayer task. Start a task first!", "");
            return;
        }

        // Save the location
        saveTaskLocation(currentSlayerTask.getName(), location);

        // Rebuild tasks to load the new location
        slayerTaskRegistry.rebuildTasks();

        // Refresh current task reference
        this.currentSlayerTask = slayerTaskRegistry.getSlayerTaskByNpcName(currentSlayerTask.getName());

        // Update UI elements with new location
        updateWorldMapIcons();
        updateShortestPath();

        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
                "Saved location for " + currentSlayerTask.getName() + "!", "");
    }

    private void saveTaskLocation(String taskName, WorldPoint location)
    {

        // Parse existing saved locations
        String currentSaved = configManager.getConfiguration("configurable-slayer-task-overlay", "savedTaskLocations");

        Map<String, WorldPoint> savedLocations = parseSavedLocations(currentSaved);

        // Update with new location
        String key = taskName.toLowerCase();
        savedLocations.put(key, location);

        // Serialize back to config
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, WorldPoint> entry : savedLocations.entrySet())
        {
            if (sb.length() > 0) sb.append(";");
            WorldPoint wp = entry.getValue();
            sb.append(entry.getKey())
                    .append(":")
                    .append(wp.getX()).append(",")
                    .append(wp.getY()).append(",")
                    .append(wp.getPlane());
        }

        String serialized = sb.toString();

        configManager.setConfiguration("configurable-slayer-task-overlay", "savedTaskLocations", serialized);

        // Verify it was saved using configManager
        String afterSave = configManager.getConfiguration("configurable-slayer-task-overlay", "savedTaskLocations");
    }

    private Map<String, WorldPoint> parseSavedLocations(String savedLocationsString)
    {
        Map<String, WorldPoint> result = new HashMap<>();

        if (savedLocationsString == null || savedLocationsString.isEmpty())
        {
            return result;
        }

        String[] entries = savedLocationsString.split(";");
        for (String entry : entries)
        {
            String[] parts = entry.split(":");
            if (parts.length != 2) continue;

            String taskName = parts[0];
            String[] coords = parts[1].split(",");

            if (coords.length != 3) continue;

            try
            {
                int x = Integer.parseInt(coords[0]);
                int y = Integer.parseInt(coords[1]);
                int plane = Integer.parseInt(coords[2]);
                result.put(taskName, new WorldPoint(x, y, plane));
            }
            catch (NumberFormatException e)
            {
                log.warn("Failed to parse saved location for task: {}", taskName);
            }
        }

        return result;
    }

    private boolean hasCustomLocation(String taskName)
    {
        String currentSaved = configManager.getConfiguration("configurable-slayer-task-overlay", "savedTaskLocations");
        Map<String, WorldPoint> savedLocations = parseSavedLocations(currentSaved);

        return savedLocations.containsKey(taskName.toLowerCase());
    }

    private void resetTaskLocation(String taskName)
    {
        // Remove the saved location from config
        String currentSaved = configManager.getConfiguration("configurable-slayer-task-overlay", "savedTaskLocations");
        Map<String, WorldPoint> savedLocations = parseSavedLocations(currentSaved);

        savedLocations.remove(taskName.toLowerCase());

        // Serialize back without this task
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, WorldPoint> entry : savedLocations.entrySet())
        {
            if (sb.length() > 0) sb.append(";");
            WorldPoint wp = entry.getValue();
            sb.append(entry.getKey())
                    .append(":")
                    .append(wp.getX()).append(",")
                    .append(wp.getY()).append(",")
                    .append(wp.getPlane());
        }

        configManager.setConfiguration("configurable-slayer-task-overlay", "savedTaskLocations", sb.toString());

        // Rebuild tasks to load default location
        slayerTaskRegistry.rebuildTasks();

        // Refresh current task
        this.currentSlayerTask = slayerTaskRegistry.getSlayerTaskByNpcName(currentSlayerTask.getName());
        updateWorldMapIcons();
        updateShortestPath();

        client.addChatMessage(ChatMessageType.GAMEMESSAGE, "",
                "Reset location for " + taskName + " to default!", "");
    }
}