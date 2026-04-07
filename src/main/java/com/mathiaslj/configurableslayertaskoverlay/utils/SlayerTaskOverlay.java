/*
 * Copyright (c) 2025, wesley-221
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
package com.mathiaslj.configurableslayertaskoverlay.utils;

import com.mathiaslj.configurableslayertaskoverlay.ConfigurableSlayerTaskOverlayConfig;
import com.mathiaslj.configurableslayertaskoverlay.ConfigurableSlayerTaskOverlayPlugin;
import com.mathiaslj.configurableslayertaskoverlay.models.NpcLocation;
import com.mathiaslj.configurableslayertaskoverlay.models.SlayerTask;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.LineComponent;

import javax.inject.Inject;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Color;

@Slf4j
public class SlayerTaskOverlay extends OverlayPanel {
    private final Client client;
    private final ConfigurableSlayerTaskOverlayPlugin plugin;
    private final ConfigurableSlayerTaskOverlayConfig config;
    private final ConfigManager configManager;

    @Inject
    public SlayerTaskOverlay(
            Client client,
            ConfigurableSlayerTaskOverlayPlugin ConfigurableSlayerTaskOverlayPlugin,
            ConfigurableSlayerTaskOverlayConfig config,
            ConfigManager configManager) {
        this.client = client;
        this.plugin = ConfigurableSlayerTaskOverlayPlugin;
        this.config = config;
        this.configManager = configManager;

        setPosition(OverlayPosition.ABOVE_CHATBOX_RIGHT);
        setPreferredSize(new Dimension(216, 40));
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (!config.enableInformationBox()) {
            return null;
        }

        SlayerTask task = plugin.getCurrentSlayerTask();

        if (task == null) {
            return null;
        }

        // Check if overlay is disabled for this specific task
        if (isOverlayDisabledForTask(task.getName())) {
            return null;
        }

        boolean shouldHide = plugin.isTaskOverlayDismissed();

        if (!shouldHide) {
            for (NpcLocation npcLocation : task.getLocations()) {
                panelComponent.getChildren().add(LineComponent.builder().left(npcLocation.getName()).leftColor(Color.YELLOW).build());

                for (String teleport : npcLocation.getTeleports()) {
                    panelComponent.getChildren().add(LineComponent.builder().left("- " + teleport).build());
                }
            }

            if (task.getInformation() != null) {
                panelComponent.getChildren().add(LineComponent.builder().left(" ").build());
                panelComponent.getChildren().add(LineComponent.builder().left(task.getInformation()).build());
            }
        }

        return super.render(graphics);
    }

    private boolean isOverlayDisabledForTask(String taskName) {
        String methodName = taskNameToDisableConfigKey(taskName);

        String disableConfig = configManager.getConfiguration("configurable-slayer-task-overlay", methodName);
        if (disableConfig == null) {
            return false; // Default to enabled if config not found
        }

        return(Boolean.parseBoolean(disableConfig));
    }

    private String taskNameToDisableConfigKey(String taskName) {
        // Convert "Aberrant spectres" -> "aberrantSpectresDisableBox"
        String[] words = taskName.toLowerCase().split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (i == 0) {
                result.append(word);
            } else {
                result.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    result.append(word.substring(1));
                }
            }
        }

        result.append("DisableBox");
        return result.toString();
    }
}
