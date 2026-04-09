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
package com.mathiaslj.configurableslayertaskoverlay;

import com.mathiaslj.configurableslayertaskoverlay.models.NpcLocation;
import com.mathiaslj.configurableslayertaskoverlay.models.SlayerTask;
import com.mathiaslj.configurableslayertaskoverlay.utils.WorldAreaUtils;
import net.runelite.api.World;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.gameval.NpcID;
import net.runelite.api.coords.WorldArea;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Supplier;
import java.util.Arrays;
import java.util.Collections;

public class SlayerTaskRegistry {

    private final ConfigurableSlayerTaskOverlayConfig config;
    private final Supplier<Map<String, WorldPoint>> savedLocationsSupplier;

    // Map is built at runtime in the constructor,not static
    private Map<String, SlayerTask> tasks = new HashMap<>();

    public SlayerTaskRegistry(ConfigurableSlayerTaskOverlayConfig config, Supplier<Map<String, WorldPoint>> savedLocationsSupplier) {
        this.config = config;
        this.savedLocationsSupplier = savedLocationsSupplier;
        rebuildTasks();
    }

    public void rebuildTasks() {
        Map<String, WorldPoint> savedLocations = savedLocationsSupplier.get();

        // WorldPoint GWD_location = new WorldPoint(2909, 3743, 0);
        // WorldPoint Stronghold_dungeon = new WorldPoint(2429, 3425, 0);
        // WorldPoint Stronghold_of_security = new WorldPoint(3082, 3420, 0);
        // WorldPoint Fremennik_slayer_dungeon = new WorldPoint(2790, 3613, 0);
        WorldPoint Alices_farm = new WorldPoint(3630, 3524, 0);
        // WorldPoint Taverley_dungeon = new WorldPoint(2882, 3387, 0);
        // WorldPoint Mos_le_harmless = new WorldPoint(3750, 2973, 0);
        // WorldPoint Edgeville_dungeon = new WorldPoint(3092, 3463, 0);
        // WorldPoint Karuulm_dungeon = new WorldPoint(1309, 3806, 0);
        // WorldPoint Asgarnian_dungeon = new WorldPoint(3009, 3149, 0);
        // WorldPoint Mourner_HQ = new WorldPoint(2543, 3322, 0);
        // WorldPoint Braindeath_island = new WorldPoint(3678, 3532, 0);
        WorldPoint Chasm_of_fire = new WorldPoint(1436, 3671, 0);
        // WorldPoint Dorgeshkaan = new WorldPoint(3201, 3209, 0);
        // WorldPoint Ancient_cavern = new WorldPoint(2512, 3509, 0);

        // Define all locations
        WorldPoint aberrantSpectresLocation = savedLocations.getOrDefault("aberrant spectres", new WorldPoint(2459, 9791, 0));
        WorldPoint abyssalDemonsLocation = savedLocations.getOrDefault("abyssal demons", new WorldPoint(1662, 10081, 0));
        WorldPoint ankousLocation = savedLocations.getOrDefault("ankous", new WorldPoint(2006, 4950, 0));
        WorldPoint aquanitesLocation = savedLocations.getOrDefault("aquanites", new WorldPoint(2262, 9846, 0));
        WorldPoint araxytesLocation = savedLocations.getOrDefault("araxytes", new WorldPoint(3679, 9838, 0));
        WorldPoint aviansieLocation = savedLocations.getOrDefault("aviansie", new WorldPoint(2847, 5269, 0));
        WorldPoint banditsLocation = savedLocations.getOrDefault("bandits", new WorldPoint(3032, 3681, 0));
        WorldPoint bansheesLocation = savedLocations.getOrDefault("banshees", new WorldPoint(3444, 3540, 0));
        WorldPoint basilisksLocation = savedLocations.getOrDefault("basilisks", new WorldPoint(2744, 10011, 0));
        WorldPoint batsLocation = savedLocations.getOrDefault("bats", new WorldPoint(3363, 3480, 0));
        WorldPoint bearsLocation = savedLocations.getOrDefault("bears", new WorldPoint(1246, 3479, 0));
        WorldPoint birdsLocation = savedLocations.getOrDefault("birds", Alices_farm);
        WorldPoint blackDemonsLocation = savedLocations.getOrDefault("black demons", new WorldPoint(2451, 3534, 0));
        WorldPoint blackDragonsLocation = savedLocations.getOrDefault("black dragons", new WorldPoint(2836, 9824, 0));
        WorldPoint blackKnightsLocation = savedLocations.getOrDefault("black knights", new WorldPoint(3020, 3507, 0));
        WorldPoint bloodveldsLocation = savedLocations.getOrDefault("bloodvelds", new WorldPoint(2452, 9818, 0));
        WorldPoint blueDragonsLocation = savedLocations.getOrDefault("blue dragons", new WorldPoint(2895, 9769, 0));
        WorldPoint brineRatsLocation = savedLocations.getOrDefault("brine rats", new WorldPoint(2746, 3729, 0));
        WorldPoint catableponLocation = savedLocations.getOrDefault("catablepon", new WorldPoint(1970, 5026, 0));
        WorldPoint caveBugsLocation = savedLocations.getOrDefault("cave bugs", new WorldPoint(2715, 5235, 0));
        WorldPoint caveCrawlersLocation = savedLocations.getOrDefault("cave crawlers", new WorldPoint(2789, 9995, 0));
        WorldPoint caveHorrorsLocation = savedLocations.getOrDefault("cave horrors", new WorldPoint(3814, 9446, 0));
        WorldPoint caveSlimesLocation = savedLocations.getOrDefault("cave slimes", new WorldPoint(2730, 5234, 0));
        WorldPoint caveKrakenLocation = savedLocations.getOrDefault("cave kraken", new WorldPoint(2277, 10002, 0));
        WorldPoint chaosDruidsLocation = savedLocations.getOrDefault("chaos druids", new WorldPoint(3113, 9928, 0));
        WorldPoint cockatriceLocation = savedLocations.getOrDefault("cockatrice", new WorldPoint(2793, 10033, 0));
        WorldPoint cowsLocation = savedLocations.getOrDefault("cows", new WorldPoint(2925, 3282, 0));
        WorldPoint crabsLocation = savedLocations.getOrDefault("crabs", new WorldPoint(3712, 3860, 0));
        WorldPoint crawlingHandsLocation = savedLocations.getOrDefault("crawling hands", new WorldPoint(3418, 3541, 0));
        WorldPoint crocodilesLocation = savedLocations.getOrDefault("crocodiles", new WorldPoint(3358, 2921, 0));
        WorldPoint custodianStalkersLocation = savedLocations.getOrDefault("custodian stalkers", new WorldPoint(1393, 9742, 0));
        WorldPoint dagannothsLocation = savedLocations.getOrDefault("dagannoths", new WorldPoint(2508, 3640, 0));
        WorldPoint darkBeastsLocation = savedLocations.getOrDefault("dark beasts", new WorldPoint(1993, 4654, 0));
        WorldPoint darkWarriorsLocation = savedLocations.getOrDefault("dark warriors", new WorldPoint(3022, 3625, 0));
        WorldPoint dogsLocation = savedLocations.getOrDefault("dogs", new WorldPoint(3343, 2787, 0));
        WorldPoint drakesLocation = savedLocations.getOrDefault("drakes", new WorldPoint(1163, 10200, 0));
        WorldPoint dustDevilsLocation = savedLocations.getOrDefault("dust devils", new WorldPoint(1709, 10013, 0));
        WorldPoint dwarvesLocation = savedLocations.getOrDefault("dwarves", new WorldPoint(3021, 3455, 0));
        WorldPoint earthWarriorsLocation = savedLocations.getOrDefault("earth warriors", new WorldPoint(3123, 9991, 0));
        WorldPoint elvesLocation = savedLocations.getOrDefault("elves", new WorldPoint(2040, 4636, 0));
        WorldPoint entsLocation = savedLocations.getOrDefault("ents", new WorldPoint(3195, 3643, 0));
        WorldPoint feverSpidersLocation = savedLocations.getOrDefault("fever spiders", new WorldPoint(2150, 5096, 0));
        WorldPoint fireGiantsLocation = savedLocations.getOrDefault("fire giants", new WorldPoint(1626, 10057, 0));
        WorldPoint fleshCrawlersLocation = savedLocations.getOrDefault("flesh crawlers", new WorldPoint(1947, 5093, 0));
        WorldPoint fossilIslandWyvernLocation = savedLocations.getOrDefault("fossil island wyvern", new WorldPoint(3612, 10251, 0));
        WorldPoint frostDragonsLocation = savedLocations.getOrDefault("frost dragons", new WorldPoint(2898, 10462, 0));
        WorldPoint gargoylesLocation = savedLocations.getOrDefault("gargoyles", new WorldPoint(3419, 3546, 0));
        WorldPoint ghostsLocation = savedLocations.getOrDefault("ghosts", new WorldPoint(1692, 10060, 0));
        WorldPoint ghoulsLocation = savedLocations.getOrDefault("ghouls", new WorldPoint(3436, 3461, 0));
        WorldPoint goblinsLocation = savedLocations.getOrDefault("goblins", new WorldPoint(3248, 3233, 0));
        WorldPoint greaterDemonsLocation = savedLocations.getOrDefault("greater demons", new WorldPoint(1303, 9982, 0));
        WorldPoint greenDragonsLocation = savedLocations.getOrDefault("green dragons", new WorldPoint(3119, 3688, 0));
        WorldPoint gryphonsLocation = savedLocations.getOrDefault("gryphons", new WorldPoint(3115, 8871, 0));
        WorldPoint harpieBugSwarmsLocation = savedLocations.getOrDefault("harpie bug swarms", new WorldPoint(1714, 2911, 0));
        WorldPoint hellhoundsLocation = savedLocations.getOrDefault("hellhounds", new WorldPoint(2413, 9785, 0));
        WorldPoint hillGiantsLocation = savedLocations.getOrDefault("hill giants", new WorldPoint(1664, 10067, 0));
        WorldPoint hobgoblinsLocation = savedLocations.getOrDefault("hobgoblins", new WorldPoint(1833, 3604, 0));
        WorldPoint hydrasLocation = savedLocations.getOrDefault("hydras", new WorldPoint(1312, 10239, 0));
        WorldPoint icefiendsLocation = savedLocations.getOrDefault("icefiends", new WorldPoint(3008, 3476, 0));
        WorldPoint iceGiantsLocation = savedLocations.getOrDefault("ice giants", new WorldPoint(2963, 9566, 0));
        WorldPoint iceWarriorsLocation = savedLocations.getOrDefault("ice warriors", new WorldPoint(3050, 9581, 0));
        WorldPoint infernalMagesLocation = savedLocations.getOrDefault("infernal mages", new WorldPoint(3437, 3564, 0));
        WorldPoint jelliesLocation = savedLocations.getOrDefault("jellies", new WorldPoint(1686, 9995, 0));
        WorldPoint jungleHorrorsLocation = savedLocations.getOrDefault("jungle horrors", new WorldPoint(3749, 2968, 0));
        WorldPoint kalphitesLocation = savedLocations.getOrDefault("kalphites", new WorldPoint(3388, 9502, 0));
        WorldPoint killerwattsLocation = savedLocations.getOrDefault("killerwatts", new WorldPoint(3110, 3355, 0));
        WorldPoint kurasksLocation = savedLocations.getOrDefault("kurasks", new WorldPoint(2709, 9937, 0));
        WorldPoint lavaDragonsLocation = savedLocations.getOrDefault("lava dragons", new WorldPoint(3204, 3817, 0));
        WorldPoint lesserDemonsLocation = savedLocations.getOrDefault("lesser demons", new WorldPoint(1309, 10084, 0));
        WorldPoint lesserNaguaLocation = savedLocations.getOrDefault("lesser nagua", new WorldPoint(1357, 9565, 0));
        WorldPoint lizardmenLocation = savedLocations.getOrDefault("lizardmen", new WorldPoint(1291, 3655, 0));
        WorldPoint lizardsLocation = savedLocations.getOrDefault("lizards", new WorldPoint(1181, 10142, 0));
        WorldPoint magicAxesLocation = savedLocations.getOrDefault("magic axes", new WorldPoint(2961, 9783, 0));
        WorldPoint mammothLocation = savedLocations.getOrDefault("mammoth", new WorldPoint(3168, 3589, 0));
        WorldPoint metalDragonsLocation = savedLocations.getOrDefault("metal dragons", new WorldPoint(1832, 5401, 0));
        WorldPoint minotaursLocation = savedLocations.getOrDefault("minotaurs", new WorldPoint(1877, 5216, 0));
        WorldPoint mogresLocation = savedLocations.getOrDefault("mogres", new WorldPoint(2998, 3113, 0));
        WorldPoint molanisksLocation = savedLocations.getOrDefault("molanisks", new WorldPoint(2730, 5234, 0));
        WorldPoint monkeysLocation = savedLocations.getOrDefault("monkeys", new WorldPoint(2787, 3000, 0));
        WorldPoint mossGiantsLocation = savedLocations.getOrDefault("moss giants", new WorldPoint(1688, 10048, 0));
        WorldPoint nechryaelLocation = savedLocations.getOrDefault("nechryael", new WorldPoint(1694, 10080, 0));
        WorldPoint ogresLocation = savedLocations.getOrDefault("ogres", new WorldPoint(2508, 3102, 0));
        WorldPoint otherworldlyBeingsLocation = savedLocations.getOrDefault("otherworldly beings", new WorldPoint(2384, 4424, 0));
        WorldPoint piratesLocation = savedLocations.getOrDefault("pirates", new WorldPoint(2995, 9576, 0));
        WorldPoint pyrefiendsLocation = savedLocations.getOrDefault("pyrefiends", new WorldPoint(2248, 2951, 0));
        WorldPoint ratsLocation = savedLocations.getOrDefault("rats", new WorldPoint(1343, 3486, 0));
        WorldPoint redDragonsLocation = savedLocations.getOrDefault("red dragons", new WorldPoint(2750, 3151, 0));
        WorldPoint revenantsLocation = savedLocations.getOrDefault("revenants", new WorldPoint(3070, 3740, 0));
        WorldPoint rockslugsLocation = savedLocations.getOrDefault("rockslugs", new WorldPoint(2800, 10017, 0));
        WorldPoint roguesLocation = savedLocations.getOrDefault("rogues", new WorldPoint(3286, 3932, 0));
        WorldPoint minionsOfScabarasLocation = savedLocations.getOrDefault("minions of scabaras", new WorldPoint(3317, 2797, 0));
        WorldPoint scorpionsLocation = savedLocations.getOrDefault("scorpions", new WorldPoint(3047, 9781, 0));
        WorldPoint seaSnakesLocation = savedLocations.getOrDefault("sea snakes", new WorldPoint(2600, 10272, 0));
        WorldPoint shadesLocation = savedLocations.getOrDefault("shades", new WorldPoint(1604, 10039, 0));
        WorldPoint shadowWarriorsLocation = savedLocations.getOrDefault("shadow warriors", new WorldPoint(2700, 9775, 0));
        WorldPoint skeletalWyvernsLocation = savedLocations.getOrDefault("skeletal wyverns", new WorldPoint(3063, 9548, 0));
        WorldPoint skeletonsLocation = savedLocations.getOrDefault("skeletons", new WorldPoint(3372, 9746, 0));
        WorldPoint smokeDevilsLocation = savedLocations.getOrDefault("smoke devils", new WorldPoint(2400, 9435, 0));
        WorldPoint sourhogsLocation = savedLocations.getOrDefault("sourhogs", new WorldPoint(3167, 9696, 0));
        WorldPoint spidersLocation = savedLocations.getOrDefault("spiders", new WorldPoint(3165, 3246, 0));
        WorldPoint spiritualCreaturesLocation = savedLocations.getOrDefault("spiritual creatures", new WorldPoint(2867, 5304, 0));
        WorldPoint suqahsLocation = savedLocations.getOrDefault("suqahs", new WorldPoint(2110, 3934, 0));
        WorldPoint terrorDogsLocation = savedLocations.getOrDefault("terror dogs", new WorldPoint(3445, 3231, 0));
        WorldPoint trollsLocation = savedLocations.getOrDefault("trolls", new WorldPoint(1238, 3520, 0));
        WorldPoint turothLocation = savedLocations.getOrDefault("turoth", new WorldPoint(2723, 10001, 0));
        WorldPoint tzHaarLocation = savedLocations.getOrDefault("tzhaar", new WorldPoint(2446, 5136, 0));
        WorldPoint vampyresLocation = savedLocations.getOrDefault("vampyres", new WorldPoint(3598, 3355, 0));
        WorldPoint wallBeastsLocation = savedLocations.getOrDefault("wall beasts", new WorldPoint(3163, 9551, 0));
        WorldPoint warpedCreaturesLocation = savedLocations.getOrDefault("warped creatures", new WorldPoint(2215, 3088, 0));
        WorldPoint waterfiendsLocation = savedLocations.getOrDefault("waterfiends", new WorldPoint(1740, 5348, 0));
        WorldPoint werewolvesLocation = savedLocations.getOrDefault("werewolves", new WorldPoint(2913, 5348, 0));
        WorldPoint wolvesLocation = savedLocations.getOrDefault("wolves", new WorldPoint(2836, 3496, 0));
        WorldPoint wyrmsLocation = savedLocations.getOrDefault("wyrms", new WorldPoint(1272, 10183, 0));
        WorldPoint zombiesLocation = savedLocations.getOrDefault("zombies", Alices_farm);
        WorldPoint zygomitesLocation = savedLocations.getOrDefault("zygomites", new WorldPoint(2417, 4465, 0));

        tasks = Map.ofEntries(
                Map.entry("aberrant spectres", new SlayerTask("Aberrant spectres", List.of(NpcID.SLAYER_ABBERANT_SPECTRE_1),
                        Collections.singletonList(aberrantSpectresLocation),
                        createNpcLocationsFromWorldPoint("Aberrant spectres", aberrantSpectresLocation, config.aberrantSpectresInfo().split("\n"))
                )),

                Map.entry("abyssal demons", new SlayerTask("Abyssal demons", List.of(NpcID.SLAYER_ABYSSAL),
                        Collections.singletonList(abyssalDemonsLocation),
                        createNpcLocationsFromWorldPoint("Abyssal demons", abyssalDemonsLocation, config.abyssalDemonsInfo().split("\n"))
                )),

                Map.entry("ankous", new SlayerTask("Ankous", List.of(NpcID.SOS_DEATH_ANKOU),
                        Collections.singletonList(ankousLocation),
                        createNpcLocationsFromWorldPoint("Ankous", ankousLocation, config.ankousInfo().split("\n"))
                )),

                Map.entry("aquanites", new SlayerTask("Aquanites", List.of(NpcID.SLAYER_AQUANITE),
                        Collections.singletonList(aquanitesLocation),
                        createNpcLocationsFromWorldPoint("Aquanites", aquanitesLocation, config.aquanitesInfo().split("\n"))
                )),

                Map.entry("araxytes", new SlayerTask("Araxytes", List.of(NpcID.ARAXYTE_BABY, NpcID.ARAXYTE_NORMAL, NpcID.ARAXYTE_LARGE),
                        Collections.singletonList(araxytesLocation),
                        createNpcLocationsFromWorldPoint("Araxytes", araxytesLocation, config.araxytesInfo().split("\n"))
                )),

                Map.entry("aviansie", new SlayerTask("Aviansie", List.of(NpcID.GODWARS_ARMADYL_MALE_ARMOR01_BLUE),
                        Collections.singletonList(aviansieLocation),
                        createNpcLocationsFromWorldPoint("Aviansie", aviansieLocation, config.aviansieInfo().split("\n"))
                )),

                Map.entry("bandits", new SlayerTask("Bandits", List.of(1),
                        Collections.singletonList(banditsLocation),
                        createNpcLocationsFromWorldPoint("Bandits", banditsLocation, config.banditsInfo().split("\n"))
                )),

                Map.entry("banshees", new SlayerTask("Banshees", List.of(1),
                        Collections.singletonList(bansheesLocation),
                        createNpcLocationsFromWorldPoint("Banshees", bansheesLocation, config.bansheesInfo().split("\n"))
                )),

                Map.entry("basilisks", new SlayerTask("Basilisks", List.of(1),
                        Collections.singletonList(basilisksLocation),
                        createNpcLocationsFromWorldPoint("Basilisks", basilisksLocation, config.basilisksInfo().split("\n"))
                )),

                Map.entry("bats", new SlayerTask("Bats", List.of(NpcID.BAT),
                        Collections.singletonList(batsLocation),
                        createNpcLocationsFromWorldPoint("Bats", batsLocation, config.batsInfo().split("\n"))
                )),

                Map.entry("bears", new SlayerTask("Bears", List.of(1),
                        Collections.singletonList(bearsLocation),
                        createNpcLocationsFromWorldPoint("Bears", bearsLocation, config.bearsInfo().split("\n"))
                )),

                Map.entry("birds", new SlayerTask("Birds", List.of(NpcID.CHICKEN, NpcID.ROOSTER, NpcID.SEAGULL, NpcID.TERRORBIRD),
                        Collections.singletonList(birdsLocation),
                        createNpcLocationsFromWorldPoint("Birds", birdsLocation, config.birdsInfo().split("\n"))
                )),

                Map.entry("black demons", new SlayerTask("Black demons", List.of(NpcID.BLACK_DEMON),
                        Collections.singletonList(blackDemonsLocation),
                        createNpcLocationsFromWorldPoint("Black demons", blackDemonsLocation, config.blackDemonsInfo().split("\n"))
                )),

                Map.entry("black dragons", new SlayerTask("Black dragons", List.of(NpcID.BLACK_DRAGON),
                        Collections.singletonList(blackDragonsLocation),
                        createNpcLocationsFromWorldPoint("Black dragons", blackDragonsLocation, config.blackDragonsInfo().split("\n"))
                )),

                Map.entry("black knights", new SlayerTask("Black knights", List.of(NpcID.BLACK_KNIGHT),
                        Collections.singletonList(blackKnightsLocation),
                        createNpcLocationsFromWorldPoint("Black knights", blackKnightsLocation, config.blackKnightsInfo().split("\n"))
                )),

                Map.entry("bloodvelds", new SlayerTask("Bloodvelds", List.of(1),
                        Collections.singletonList(bloodveldsLocation),
                        createNpcLocationsFromWorldPoint("Bloodvelds", bloodveldsLocation, config.bloodveldsInfo().split("\n"))
                )),

                Map.entry("blue dragons", new SlayerTask("Blue dragons", List.of(NpcID.BLUE_DRAGON, NpcID.VORKATH),
                        Collections.singletonList(blueDragonsLocation),
                        createNpcLocationsFromWorldPoint("Blue dragons", blueDragonsLocation, config.blueDragonsInfo().split("\n"))
                )),

                Map.entry("brine rats", new SlayerTask("Brine rats", List.of(1),
                        Collections.singletonList(brineRatsLocation),
                        createNpcLocationsFromWorldPoint("Brine rats", brineRatsLocation, config.brineRatsInfo().split("\n"))
                )),

                Map.entry("catablepon", new SlayerTask("Catablepon", List.of(1),
                        Collections.singletonList(catableponLocation),
                        createNpcLocationsFromWorldPoint("Catablepon", catableponLocation, config.catableponInfo().split("\n"))
                )),

                Map.entry("cave bugs", new SlayerTask("Cave bugs", List.of(1),
                        Collections.singletonList(caveBugsLocation),
                        createNpcLocationsFromWorldPoint("Cave bugs", caveBugsLocation, config.caveBugsInfo().split("\n"))
                )),

                Map.entry("cave crawlers", new SlayerTask("Cave crawlers", List.of(1),
                        Collections.singletonList(caveCrawlersLocation),
                        createNpcLocationsFromWorldPoint("Cave crawlers", caveCrawlersLocation, config.caveCrawlersInfo().split("\n"))
                )),

                Map.entry("cave horrors", new SlayerTask("Cave horrors", List.of(1),
                        Collections.singletonList(caveHorrorsLocation),
                        createNpcLocationsFromWorldPoint("Cave horrors", caveHorrorsLocation, config.caveHorrorsInfo().split("\n"))
                )),

                Map.entry("cave slimes", new SlayerTask("Cave slimes", List.of(1),
                        Collections.singletonList(caveSlimesLocation),
                        createNpcLocationsFromWorldPoint("Cave slimes", caveSlimesLocation, config.caveSlimesInfo().split("\n"))
                )),

                Map.entry("cave kraken", new SlayerTask("Cave kraken", List.of(1),
                        Collections.singletonList(caveKrakenLocation),
                        createNpcLocationsFromWorldPoint("Cave kraken", caveKrakenLocation, config.caveKrakenInfo().split("\n"))
                )),

                Map.entry("chaos druids", new SlayerTask("Chaos druids", List.of(NpcID.CHAOS_DRUID),
                        Collections.singletonList(chaosDruidsLocation),
                        createNpcLocationsFromWorldPoint("Chaos druids", chaosDruidsLocation, config.chaosDruidsInfo().split("\n"))
                )),

                Map.entry("cockatrice", new SlayerTask("cockatrice", List.of(1),
                        Collections.singletonList(cockatriceLocation),
                        createNpcLocationsFromWorldPoint("cockatrice", cockatriceLocation, config.cockatriceInfo().split("\n"))
                )),

                Map.entry("cows", new SlayerTask("Cows", List.of(NpcID.COW),
                        Collections.singletonList(cowsLocation),
                        createNpcLocationsFromWorldPoint("Cows", cowsLocation, config.cowsInfo().split("\n"))
                )),

                Map.entry("crabs", new SlayerTask("Crabs", List.of(1),
                        Collections.singletonList(crabsLocation),
                        createNpcLocationsFromWorldPoint("Crabs", crabsLocation, config.crabsInfo().split("\n"))
                )),

                Map.entry("crawling hands", new SlayerTask("Crawling hands", List.of(1),
                        Collections.singletonList(crawlingHandsLocation),
                        createNpcLocationsFromWorldPoint("Crawling hands", crawlingHandsLocation, config.crawlingHandsInfo().split("\n"))
                )),

                Map.entry("crocodiles", new SlayerTask("Crocodiles", List.of(1),
                        Collections.singletonList(crocodilesLocation),
                        createNpcLocationsFromWorldPoint("Crocodiles", crocodilesLocation, config.crocodilesInfo().split("\n"))
                )),

                Map.entry("custodian stalkers", new SlayerTask("Custodian stalkers", List.of(1),
                        Collections.singletonList(custodianStalkersLocation),
                        createNpcLocationsFromWorldPoint("Custodian stalkers", custodianStalkersLocation, config.custodianStalkersInfo().split("\n"))
                )),

                Map.entry("dagannoths", new SlayerTask("Dagannoths", List.of(1),
                        Collections.singletonList(dagannothsLocation),
                        createNpcLocationsFromWorldPoint("Dagannoths", dagannothsLocation, config.dagannothsInfo().split("\n"))
                )),

                Map.entry("dark beasts", new SlayerTask("Dark beasts", List.of(1),
                        Collections.singletonList(darkBeastsLocation),
                        createNpcLocationsFromWorldPoint("Dark beasts", darkBeastsLocation, config.darkBeastsInfo().split("\n"))
                )),

                Map.entry("dark warriors", new SlayerTask("Dark warriors", List.of(NpcID.DARK_WARRIOR),
                        Collections.singletonList(darkWarriorsLocation),
                        createNpcLocationsFromWorldPoint("Dark warriors", darkWarriorsLocation, config.darkWarriorsInfo().split("\n"))
                )),

                Map.entry("dogs", new SlayerTask("Dogs", List.of(1),
                        Collections.singletonList(dogsLocation),
                        createNpcLocationsFromWorldPoint("Dogs", dogsLocation, config.dogsInfo().split("\n"))
                )),

                Map.entry("drakes", new SlayerTask("Drakes", List.of(NpcID.DRAKE),
                        Collections.singletonList(drakesLocation),
                        createNpcLocationsFromWorldPoint("Drakes", drakesLocation, config.drakesInfo().split("\n"))
                )),

                Map.entry("dust devils", new SlayerTask("Dust devils", List.of(1),
                        Collections.singletonList(dustDevilsLocation),
                        createNpcLocationsFromWorldPoint("Dust devils", dustDevilsLocation, config.dustDevilsInfo().split("\n"))
                )),

                Map.entry("dwarves", new SlayerTask("Dwarves", List.of(1),
                        Collections.singletonList(dwarvesLocation),
                        createNpcLocationsFromWorldPoint("Dwarves", dwarvesLocation, config.dwarvesInfo().split("\n"))
                )),

                Map.entry("earth warriors", new SlayerTask("Earth warriors", List.of(1),
                        Collections.singletonList(earthWarriorsLocation),
                        createNpcLocationsFromWorldPoint("Earth warriors", earthWarriorsLocation, config.earthWarriorsInfo().split("\n"))
                )),

                Map.entry("elves", new SlayerTask("Elves", List.of(1),
                        Collections.singletonList(elvesLocation),
                        createNpcLocationsFromWorldPoint("Elves", elvesLocation, config.elvesInfo().split("\n"))
                )),

                Map.entry("ents", new SlayerTask("Ents", List.of(1),
                        Collections.singletonList(entsLocation),
                        createNpcLocationsFromWorldPoint("Ents", entsLocation, config.entsInfo().split("\n"))
                )),

                Map.entry("fever spiders", new SlayerTask("Fever spiders", List.of(1),
                        Collections.singletonList(feverSpidersLocation),
                        createNpcLocationsFromWorldPoint("Fever spiders", feverSpidersLocation, config.feverSpidersInfo().split("\n"))
                )),

                Map.entry("fire giants", new SlayerTask("Fire giants", List.of(1),
                        Collections.singletonList(fireGiantsLocation),
                        createNpcLocationsFromWorldPoint("Fire giants", fireGiantsLocation, config.fireGiantsInfo().split("\n"))
                )),

                Map.entry("flesh crawlers", new SlayerTask("Flesh crawlers", List.of(1),
                        Collections.singletonList(fleshCrawlersLocation),
                        createNpcLocationsFromWorldPoint("Flesh crawlers", fleshCrawlersLocation, config.fleshCrawlersInfo().split("\n"))
                )),

                Map.entry("fossil island wyvern", new SlayerTask("Fossil island wyvern", List.of(NpcID.ANCIENT_WYVERN),
                        Collections.singletonList(fossilIslandWyvernLocation),
                        createNpcLocationsFromWorldPoint("Fossil island wyvern", fossilIslandWyvernLocation, config.fossilIslandWyvernInfo().split("\n"))
                )),

                Map.entry("frost dragons", new SlayerTask("Frost dragons", List.of(NpcID.FROST_DRAGON),
                        Collections.singletonList(frostDragonsLocation),
                        createNpcLocationsFromWorldPoint("Frost dragons", frostDragonsLocation, config.frostDragonsInfo().split("\n"))
                )),

                Map.entry("gargoyles", new SlayerTask("Gargoyles",
                        List.of(NpcID.SLAYER_GARGOYLE_1, NpcID.SLAYER_CAVE_GARGOYLE, NpcID.SUPERIOR_GARGOYLE),
                        Collections.singletonList(gargoylesLocation),
                        createNpcLocationsFromWorldPoint("Gargoyles", gargoylesLocation, config.gargoylesInfo().split("\n"))
                )),

                Map.entry("ghosts", new SlayerTask("Ghosts", List.of(NpcID.GHOST),
                        Collections.singletonList(ghostsLocation),
                        createNpcLocationsFromWorldPoint("Ghosts", ghostsLocation, config.ghostsInfo().split("\n"))
                )),

                Map.entry("ghouls", new SlayerTask("Ghouls", List.of(NpcID.GHOUL),
                        Collections.singletonList(ghoulsLocation),
                        createNpcLocationsFromWorldPoint("Ghouls", ghoulsLocation, config.ghoulsInfo().split("\n"))
                )),

                Map.entry("goblins", new SlayerTask("Goblins", List.of(NpcID.GOBLIN, NpcID.CAVE_GOBLIN),
                        Collections.singletonList(goblinsLocation),
                        createNpcLocationsFromWorldPoint("Goblins", goblinsLocation, config.goblinsInfo().split("\n"))
                )),

                Map.entry("greater demons", new SlayerTask("Greater demons", List.of(NpcID.GREATER_DEMON),
                        Collections.singletonList(greaterDemonsLocation),
                        createNpcLocationsFromWorldPoint("Greater demons", greaterDemonsLocation, config.greaterDemonsInfo().split("\n")))),

                Map.entry("green dragons", new SlayerTask("Green dragons", List.of(NpcID.GREEN_DRAGON),
                        Collections.singletonList(greenDragonsLocation),
                        createNpcLocationsFromWorldPoint("Green dragons", greenDragonsLocation, config.greenDragonsInfo().split("\n"))
                )),

                Map.entry("gryphons", new SlayerTask("Gryphons", List.of(1),
                        Collections.singletonList(gryphonsLocation),
                        createNpcLocationsFromWorldPoint("Gryphons", gryphonsLocation, config.gryphonsInfo().split("\n"))
                )),

                Map.entry("harpie bug swarms", new SlayerTask("Harpie bug swarms", List.of(1),
                        Collections.singletonList(harpieBugSwarmsLocation),
                        createNpcLocationsFromWorldPoint("Harpie bug swarms", harpieBugSwarmsLocation, config.harpieBugSwarmsInfo().split("\n"))
                )),

                Map.entry("hellhounds", new SlayerTask("Hellhounds", List.of(NpcID.HELLHOUND, 1),
                        Collections.singletonList(hellhoundsLocation),
                        createNpcLocationsFromWorldPoint("Hellhounds", hellhoundsLocation, config.hellhoundsInfo().split("\n"))
                )),

                Map.entry("hill giants", new SlayerTask("Hill giants", List.of(1),
                        Collections.singletonList(hillGiantsLocation),
                        createNpcLocationsFromWorldPoint("Hill giants", hillGiantsLocation, config.hillGiantsInfo().split("\n"))
                )),

                Map.entry("hobgoblins", new SlayerTask("Hobgoblins", List.of(1),
                        Collections.singletonList(hobgoblinsLocation),
                        createNpcLocationsFromWorldPoint("Hobgoblins", hobgoblinsLocation, config.hobgoblinsInfo().split("\n"))
                )),

                Map.entry("hydras", new SlayerTask("Hydras", List.of(NpcID.HYDRA),
                        Collections.singletonList(hydrasLocation),
                        createNpcLocationsFromWorldPoint("Hydras", hydrasLocation, config.hydrasInfo().split("\n"))
                )),

                Map.entry("icefiends", new SlayerTask("Icefiends", List.of(1),
                        Collections.singletonList(icefiendsLocation),
                        createNpcLocationsFromWorldPoint("Icefiends", icefiendsLocation, config.icefiendsInfo().split("\n"))
                )),

                Map.entry("ice giants", new SlayerTask("Ice giants", List.of(1),
                        Collections.singletonList(iceGiantsLocation),
                        createNpcLocationsFromWorldPoint("Ice giants", iceGiantsLocation, config.iceGiantsInfo().split("\n"))
                )),

                Map.entry("ice warriors", new SlayerTask("Ice warriors", List.of(1),
                        Collections.singletonList(iceWarriorsLocation),
                        createNpcLocationsFromWorldPoint("Ice warriors", iceWarriorsLocation, config.iceWarriorsInfo().split("\n"))
                )),

                Map.entry("infernal mages", new SlayerTask("Infernal mages", List.of(1),
                        Collections.singletonList(infernalMagesLocation),
                        createNpcLocationsFromWorldPoint("Infernal mages", infernalMagesLocation, config.infernalMagesInfo().split("\n"))
                )),

                Map.entry("jellies", new SlayerTask("Jellies", List.of(1),
                        Collections.singletonList(jelliesLocation),
                        createNpcLocationsFromWorldPoint("Jellies", jelliesLocation, config.jelliesInfo().split("\n"))
                )),

                Map.entry("jungle horrors", new SlayerTask("Jungle horrors", List.of(1),
                        Collections.singletonList(jungleHorrorsLocation),
                        createNpcLocationsFromWorldPoint("Jungle horrors", jungleHorrorsLocation, config.jungleHorrorsInfo().split("\n"))
                )),

                Map.entry("kalphites", new SlayerTask("Kalphites", List.of(NpcID.KALPHITE_WORKER, NpcID.KALPHITE_SOLDIER, NpcID.KALPHITE_QUEEN),
                        Collections.singletonList(kalphitesLocation),
                        createNpcLocationsFromWorldPoint("Kalphites", kalphitesLocation, config.kalphitesInfo().split("\n"))
                )),

                Map.entry("killerwatts", new SlayerTask("Killerwatts", List.of(1),
                        Collections.singletonList(killerwattsLocation),
                        createNpcLocationsFromWorldPoint("Killerwatts", killerwattsLocation, config.killerwattsInfo().split("\n"))
                )),

                Map.entry("kurasks", new SlayerTask("Kurasks", List.of(1),
                        Collections.singletonList(kurasksLocation),
                        createNpcLocationsFromWorldPoint("Kurasks", kurasksLocation, config.kurasksInfo().split("\n"))
                )),

                Map.entry("lava dragons", new SlayerTask("Lava dragons", List.of(NpcID.LAVA_DRAGON),
                        Collections.singletonList(lavaDragonsLocation),
                        createNpcLocationsFromWorldPoint("Lava dragons", lavaDragonsLocation, config.lavaDragonsInfo().split("\n"))
                )),

                Map.entry("lesser demons", new SlayerTask("Lesser demons", List.of(NpcID.LESSER_DEMON),
                        Collections.singletonList(lesserDemonsLocation),
                        createNpcLocationsFromWorldPoint("Lesser demons", lesserDemonsLocation, config.lesserDemonsInfo().split("\n"))
                )),

                Map.entry("lesser nagua", new SlayerTask("Lesser nagua", List.of(1),
                        Collections.singletonList(lesserNaguaLocation),
                        createNpcLocationsFromWorldPoint("Lesser nagua", lesserNaguaLocation, config.lesserNaguaInfo().split("\n"))
                )),

                Map.entry("lizardmen", new SlayerTask("Lizardmen", List.of(1),
                        Collections.singletonList(lizardmenLocation),
                        createNpcLocationsFromWorldPoint("Lizardmen", lizardmenLocation, config.lizardmenInfo().split("\n"))
                )),

                Map.entry("lizards", new SlayerTask("Lizards", List.of(1),
                        Collections.singletonList(lizardsLocation),
                        createNpcLocationsFromWorldPoint("Lizards", lizardsLocation, config.lizardsInfo().split("\n"))
                )),

                Map.entry("magic axes", new SlayerTask("Magic axes", List.of(1),
                        Collections.singletonList(magicAxesLocation),
                        createNpcLocationsFromWorldPoint("Magic axes", magicAxesLocation, config.magicAxesInfo().split("\n"))
                )),

                Map.entry("mammoth", new SlayerTask("Mammoth", List.of(1),
                        Collections.singletonList(mammothLocation),
                        createNpcLocationsFromWorldPoint("Mammoth", mammothLocation, config.mammothInfo().split("\n"))
                )),

                Map.entry("metal dragons", new SlayerTask("Metal dragons", List.of(NpcID.BRONZE_DRAGON, NpcID.IRON_DRAGON, NpcID.STEEL_DRAGON, NpcID.ADAMANT_DRAGON, NpcID.RUNE_DRAGON),
                        Collections.singletonList(metalDragonsLocation),
                        createNpcLocationsFromWorldPoint("Metal dragons", metalDragonsLocation, config.metalDragonsInfo().split("\n"))
                )),

                Map.entry("minotaurs", new SlayerTask("Minotaurs", List.of(1),
                        Collections.singletonList(minotaursLocation),
                        createNpcLocationsFromWorldPoint("Minotaurs", minotaursLocation, config.minotaursInfo().split("\n"))
                )),

                Map.entry("mogres", new SlayerTask("Mogres", List.of(1),
                        Collections.singletonList(mogresLocation),
                        createNpcLocationsFromWorldPoint("Mogres", mogresLocation, config.mogresInfo().split("\n"))
                )),

                Map.entry("molanisks", new SlayerTask("Molanisks", List.of(NpcID.MOLANISK),
                        Collections.singletonList(molanisksLocation),
                        createNpcLocationsFromWorldPoint("Molanisks", molanisksLocation, config.molanisksInfo().split("\n"))
                )),

                Map.entry("monkeys", new SlayerTask("Monkeys", List.of(NpcID.MONKEY),
                        Collections.singletonList(monkeysLocation),
                        createNpcLocationsFromWorldPoint("Monkeys", monkeysLocation, config.monkeysInfo().split("\n"))
                )),

                Map.entry("moss giants", new SlayerTask("Moss giants", List.of(1),
                        Collections.singletonList(mossGiantsLocation),
                        createNpcLocationsFromWorldPoint("Moss giants", mossGiantsLocation, config.mossGiantsInfo().split("\n"))
                )),

                Map.entry("nechryael", new SlayerTask("Nechryael", List.of(1),
                        Collections.singletonList(nechryaelLocation),
                        createNpcLocationsFromWorldPoint("Nechryael", nechryaelLocation, config.nechryaelInfo().split("\n"))
                )),

                Map.entry("ogres", new SlayerTask("Ogres", List.of(NpcID.OGRE),
                        Collections.singletonList(ogresLocation),
                        createNpcLocationsFromWorldPoint("Ogres", ogresLocation, config.ogresInfo().split("\n"))
                )),

                Map.entry("otherworldly beings", new SlayerTask("Otherworldly beings", List.of(NpcID.OTHERWORLDLY_BEING),
                        Collections.singletonList(otherworldlyBeingsLocation),
                        createNpcLocationsFromWorldPoint("Otherworldly beings", otherworldlyBeingsLocation, config.otherworldlyBeingsInfo().split("\n"))
                )),

                Map.entry("pirates", new SlayerTask("Pirates", List.of(1),
                        Collections.singletonList(piratesLocation),
                        createNpcLocationsFromWorldPoint("Pirates", piratesLocation, config.piratesInfo().split("\n"))
                )),

                Map.entry("pyrefiends", new SlayerTask("Pyrefiends", List.of(1),
                        Collections.singletonList(pyrefiendsLocation),
                        createNpcLocationsFromWorldPoint("Pyrefiends", pyrefiendsLocation, config.pyrefiendsInfo().split("\n"))
                )),

                Map.entry("rats", new SlayerTask("Rats", List.of(NpcID.RAT),
                        Collections.singletonList(ratsLocation),
                        createNpcLocationsFromWorldPoint("Rats", ratsLocation, config.ratsInfo().split("\n"))
                )),

                Map.entry("red dragons", new SlayerTask("Red dragons", List.of(NpcID.RED_DRAGON),
                        Collections.singletonList(redDragonsLocation),
                        createNpcLocationsFromWorldPoint("Red dragons", redDragonsLocation, config.redDragonsInfo().split("\n"))
                )),

                Map.entry("revenants", new SlayerTask("Revenants", List.of(1),
                        Collections.singletonList(revenantsLocation),
                        createNpcLocationsFromWorldPoint("Revenants", revenantsLocation, config.revenantsInfo().split("\n"))
                )),

                Map.entry("rockslugs", new SlayerTask("Rockslugs", List.of(1),
                        Collections.singletonList(rockslugsLocation),
                        createNpcLocationsFromWorldPoint("Rockslugs", rockslugsLocation, config.rockslugsInfo().split("\n"))
                )),

                Map.entry("rogues", new SlayerTask("Rogues", List.of(NpcID.ROGUE),
                        Collections.singletonList(roguesLocation),
                        createNpcLocationsFromWorldPoint("Rogues", roguesLocation, config.roguesInfo().split("\n"))
                )),

                Map.entry("minions of scabaras", new SlayerTask("Minions of Scabaras", List.of(1),
                        Collections.singletonList(minionsOfScabarasLocation),
                        createNpcLocationsFromWorldPoint("Minions of Scabaras", minionsOfScabarasLocation, config.minionsOfScabarasInfo().split("\n"))
                )),

                Map.entry("scorpions", new SlayerTask("Scorpions", List.of(NpcID.SCORPION, NpcID.SCORPIA),
                        Collections.singletonList(scorpionsLocation),
                        createNpcLocationsFromWorldPoint("Scorpions", scorpionsLocation, config.scorpionsInfo().split("\n"))
                )),

                Map.entry("sea snakes", new SlayerTask("Sea snakes", List.of(1),
                        Collections.singletonList(seaSnakesLocation),
                        createNpcLocationsFromWorldPoint("Sea snakes", seaSnakesLocation, config.seaSnakesInfo().split("\n"))
                )),

                Map.entry("shades", new SlayerTask("Shades", List.of(1),
                        Collections.singletonList(shadesLocation),
                        createNpcLocationsFromWorldPoint("Shades", shadesLocation, config.shadesInfo().split("\n"))
                )),

                Map.entry("shadow warriors", new SlayerTask("Shadow warriors", List.of(NpcID.SHADOW_WARRIOR),
                        Collections.singletonList(shadowWarriorsLocation),
                        createNpcLocationsFromWorldPoint("Shadow warriors", shadowWarriorsLocation, config.shadowWarriorsInfo().split("\n"))
                )),

                Map.entry("skeletal wyverns", new SlayerTask("Skeletal wyverns", List.of(1),
                        Collections.singletonList(skeletalWyvernsLocation),
                        createNpcLocationsFromWorldPoint("Skeletal wyverns", skeletalWyvernsLocation, config.skeletalWyvernsInfo().split("\n"))
                )),

                Map.entry("skeletons", new SlayerTask("Skeletons", List.of(NpcID.VETION),
                        Collections.singletonList(skeletonsLocation),
                        createNpcLocationsFromWorldPoint("Skeletons", skeletonsLocation, config.skeletonsInfo().split("\n"))
                )),

                Map.entry("smoke devils", new SlayerTask("Smoke devils", List.of(NpcID.SMOKE_DEVIL),
                        Collections.singletonList(smokeDevilsLocation),
                        createNpcLocationsFromWorldPoint("Smoke devils", smokeDevilsLocation, config.smokeDevilsInfo().split("\n"))
                )),

                Map.entry("sourhogs", new SlayerTask("Sourhogs", List.of(NpcID.SOURHOG),
                        Collections.singletonList(sourhogsLocation),
                        createNpcLocationsFromWorldPoint("Sourhogs", sourhogsLocation, config.sourhogsInfo().split("\n"))
                )),

                Map.entry("spiders", new SlayerTask("Spiders", List.of(NpcID.SPIDER, NpcID.SARACHNIS, NpcID.VENENATIS),
                        Collections.singletonList(spidersLocation),
                        createNpcLocationsFromWorldPoint("Spiders", spidersLocation, config.spidersInfo().split("\n"))
                )),

                Map.entry("spiritual creatures", new SlayerTask("Spiritual creatures", List.of(1),
                        Collections.singletonList(spiritualCreaturesLocation),
                        createNpcLocationsFromWorldPoint("Spiritual creatures", spiritualCreaturesLocation, config.spiritualCreaturesInfo().split("\n"))
                )),

                Map.entry("suqahs", new SlayerTask("Suqahs", List.of(1),
                        Collections.singletonList(suqahsLocation),
                        createNpcLocationsFromWorldPoint("Suqahs", suqahsLocation, config.suqahsInfo().split("\n"))
                )),

                Map.entry("terror dogs", new SlayerTask("Terror dogs", List.of(1),
                        Collections.singletonList(terrorDogsLocation),
                        createNpcLocationsFromWorldPoint("Terror dogs", terrorDogsLocation, config.terrorDogsInfo().split("\n"))
                )),

                Map.entry("trolls", new SlayerTask("Trolls", List.of(1),
                        Collections.singletonList(trollsLocation),
                        createNpcLocationsFromWorldPoint("Trolls", trollsLocation, config.trollsInfo().split("\n"))
                )),

                Map.entry("turoth", new SlayerTask("Turoth", List.of(1),
                        Collections.singletonList(turothLocation),
                        createNpcLocationsFromWorldPoint("Turoth", turothLocation, config.turothInfo().split("\n"))
                )),

                Map.entry("tzhaar", new SlayerTask("TzHaar", List.of(1),
                        Collections.singletonList(tzHaarLocation),
                        createNpcLocationsFromWorldPoint("TzHaar", tzHaarLocation, config.tzHaarInfo().split("\n"))
                )),

                Map.entry("vampyres", new SlayerTask("Vampyres", List.of(1),
                        Collections.singletonList(vampyresLocation),
                        createNpcLocationsFromWorldPoint("Vampyres", vampyresLocation, config.vampyresInfo().split("\n"))
                )),

                Map.entry("wall beasts", new SlayerTask("Wall beasts", List.of(1),
                        Collections.singletonList(wallBeastsLocation),
                        createNpcLocationsFromWorldPoint("Wall beasts", wallBeastsLocation, config.wallBeastsInfo().split("\n"))
                )),

                Map.entry("warped creatures", new SlayerTask("Warped creatures", List.of(1),
                        Collections.singletonList(warpedCreaturesLocation),
                        createNpcLocationsFromWorldPoint("Warped creatures", warpedCreaturesLocation, config.warpedCreaturesInfo().split("\n"))
                )),

                Map.entry("waterfiends", new SlayerTask("Waterfiends", List.of(1),
                        Collections.singletonList(waterfiendsLocation),
                        createNpcLocationsFromWorldPoint("Waterfiends", waterfiendsLocation, config.waterfiendsInfo().split("\n"))
                )),

                Map.entry("werewolves", new SlayerTask("Werewolves", List.of(1),
                        Collections.singletonList(werewolvesLocation),
                        createNpcLocationsFromWorldPoint("Werewolves", werewolvesLocation, config.werewolvesInfo().split("\n"))
                )),

                Map.entry("wolves", new SlayerTask("Wolves", List.of(NpcID.WOLF),
                        Collections.singletonList(wolvesLocation),
                        createNpcLocationsFromWorldPoint("Wolves", wolvesLocation, config.wolvesInfo().split("\n"))
                )),

                Map.entry("wyrms", new SlayerTask("Wyrms", List.of(1),
                        Collections.singletonList(wyrmsLocation),
                        createNpcLocationsFromWorldPoint("Wyrms", wyrmsLocation, config.wyrmsInfo().split("\n"))
                )),

                Map.entry("zombies", new SlayerTask("Zombies", List.of(1),
                        Collections.singletonList(zombiesLocation),
                        createNpcLocationsFromWorldPoint("Zombies", zombiesLocation, config.zombiesInfo().split("\n"))
                )),

                Map.entry("zygomites", new SlayerTask("Zygomites", List.of(1),
                        Collections.singletonList(zygomitesLocation),
                        createNpcLocationsFromWorldPoint("Zygomites", zygomitesLocation, config.zygomitesInfo().split("\n"))
                ))
        );
    }

    public SlayerTask getSlayerTaskByNpcName(String npcName)
    {
        String tolower = npcName.toLowerCase();
        String pluralis = tolower + "s";

        SlayerTask task = tasks.get(tolower);
        if (task == null) {
            task = tasks.get(pluralis);
        }
        if (task == null) {
            for (Map.Entry<String, SlayerTask> entry : tasks.entrySet()) {
                if (entry.getKey().contains(tolower) || tolower.contains(entry.getKey())) {
                    task = entry.getValue();
                    break;
                }
            }
        }
        return task;
    }

    private List<NpcLocation> createNpcLocationsFromWorldPoint(String name, WorldPoint location, String[] info) {
        return createNpcLocationsFromWorldPoint(name, location, info, 20);
    }

    private List<NpcLocation> createNpcLocationsFromWorldPoint(String name, WorldPoint location, String[] info, int padding) {
        if (location.getX() == 0 && location.getY() == 0) {
            return List.of(
                    new NpcLocation(name, List.of(
                            WorldAreaUtils.fromCorners(
                                    new WorldPoint(0, 0, 0),
                                    new WorldPoint(0, 0, 0)
                            )
                    ), info)
            );
        }

        WorldArea area = WorldAreaUtils.fromCorners(
                new WorldPoint(location.getX() - padding, location.getY() - padding, location.getPlane()),
                new WorldPoint(location.getX() + padding, location.getY() + padding, location.getPlane())
        );

        return List.of(new NpcLocation(name, List.of(area), info));
    }

    /**
     * Creates a Goblin slayer task
     *
     * @param name the name of the task, for goblins in specific this should be "goblin" and "goblins"
     * @return the goblin slayer task
     */
    private static SlayerTask createGoblinTask(String name) {
        return new SlayerTask(name, List.of(
                NpcID.GOBLIN_UNARMED_MELEE_1,
                NpcID.GOBLIN_UNARMED_MELEE_2,
                NpcID.GOBLIN_UNARMED_MELEE_3,
                NpcID.GOBLIN_UNARMED_MELEE_5,
                NpcID.GOBLIN_UNARMED_MELEE_6,
                NpcID.GOBLIN_UNARMED_MELEE_7,
                NpcID.GOBLIN_UNARMED_MELEE_8
        ), List.of(
                new WorldPoint(3145, 3301, 0)
        ), List.of(
                new NpcLocation("South-East of Draynor Manor", List.of(
                        WorldAreaUtils.fromCorners(
                                new WorldPoint(3132, 3291, 0),
                                new WorldPoint(3156, 3312, 0)
                        )
                ), new String[]{"Draynor manor teleport", "Ornate Jewellery Box: Draynor Village (Option Q)", "Glory amulet: Draynor Village (Option 3)"})
        ), "South of the Sourhog Cave");
    }
}
