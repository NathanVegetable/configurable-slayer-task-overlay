package com.mathiaslj.configurableslayertaskoverlay;

import com.mathiaslj.configurableslayertaskoverlay.models.DungeonLocation;
import com.mathiaslj.configurableslayertaskoverlay.utils.WorldAreaUtils;
import net.runelite.api.coords.WorldPoint;

import java.util.ArrayList;
import java.util.List;

public class DungeonLocationRegistry {
    private static final List<DungeonLocation> ENTRANCES = new ArrayList<>();

    static {
        // Catacombs of Kourend
        ENTRANCES.add(new DungeonLocation(
                "Catacombs of Kourend",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1597, 10112, 0),
                        new WorldPoint(1737, 9974, 0)
                ),
                new WorldPoint(1640, 3673, 0) // Entrance location on surface
        ));

        // Stronghold Slayer Cave
        ENTRANCES.add(new DungeonLocation(
                "Stronghold Slayer Cave",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2379, 9838, 0),
                        new WorldPoint(2497, 9767, 0)
                ),
                new WorldPoint(2431, 3423, 0) // Entrance on surface
        ));

        // Stronghold Of Security
        ENTRANCES.add(new DungeonLocation(
                "Stronghold Of Security floor 1",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1855, 5246, 0),
                        new WorldPoint(1918, 5183, 0)
                ),
                new WorldPoint(3080, 3424, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Stronghold Of Security floor 2",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1886, 5152, 0),
                        new WorldPoint(1955, 5086, 0)
                ),
                new WorldPoint(3080, 3424, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Stronghold Of Security floor 3",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1921, 5056, 0),
                        new WorldPoint(1987, 4990, 0)
                ),
                new WorldPoint(3080, 3424, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Stronghold Of Security floor 4",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1951, 4960, 0),
                        new WorldPoint(2017, 4897, 0)
                ),
                new WorldPoint(3080, 3424, 0) // Entrance on surface
        ));


        // God wars dungeon
        ENTRANCES.add(new DungeonLocation(
                "God Wars Dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2789, 5381, 0),
                        new WorldPoint(2978, 5130, 0)
                ),
                new WorldPoint(2909, 3743, 0) // Entrance on surface
        ));

        // Fremennik slayer dungeon
        ENTRANCES.add(new DungeonLocation(
                "Fremennik Slayer Dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2670, 10072, 0),
                        new WorldPoint(2815, 9986, 0)
                ),
                new WorldPoint(2791, 3615, 0) // Entrance on surface
        ));
        // Fremennik slayer dungeon
        ENTRANCES.add(new DungeonLocation(
                "Fremennik Slayer Dungeon Kurask slayer task only area",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2688, 9959, 0),
                        new WorldPoint(2725, 9919, 0)
                ),
                new WorldPoint(2791, 3615, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Taverley Dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2809, 9857, 0),
                        new WorldPoint(2977, 9664, 0)
                ),
                new WorldPoint(2891, 3397, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Taverley Dungeon Black Dragons floor",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2882, 3387, 0), // Entrance on surface
                        new WorldPoint(2979, 9606, 0)
                ),
                new WorldPoint(2891, 3397, 0)
        ));
        ENTRANCES.add(new DungeonLocation(
                "Cerberus lair",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2652, 9912, 0),
                        new WorldPoint(2723, 9863, 0)
                ),
                new WorldPoint(2882, 3387, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Chasm of Fire Upper level",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1304, 10090, 0),
                        new WorldPoint(1321, 10072, 0)
                ),
                new WorldPoint(1435, 3672, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Chasm of Fire Mid level",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1292, 10000, 0),
                        new WorldPoint(1334, 9968, 0)
                ),
                new WorldPoint(1435, 3672, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Chasm of Fire Lower level",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1291, 9907, 0),
                        new WorldPoint(1328, 9870, 0)
                ),
                new WorldPoint(1435, 3672, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Mount Karuulm Hydra",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1292, 10282, 0),
                        new WorldPoint(1380, 10229, 0)
                ),
                new WorldPoint(1311, 3806, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Mount Karuulm Wyrms",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1250, 10204, 0),
                        new WorldPoint(1295, 10144, 0)
                ),
                new WorldPoint(1311, 3806, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Mount Karuulm Mid level",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1114, 10218, 0),
                        new WorldPoint(1204, 10129, 0)
                ),
                new WorldPoint(1311, 3806, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Mount Karuulm Upper level",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1117, 10287, 0),
                        new WorldPoint(1215, 10246, 0)
                ),
                new WorldPoint(1311, 3806, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Asgarnia dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2869, 9664, 0),
                        new WorldPoint(3164, 9524, 0)
                ),
                new WorldPoint(3013, 3150, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Dark beasts location",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1935, 4575, 0),
                        new WorldPoint(2014, 4635, 0)
                ),
                new WorldPoint(2543, 3322, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Mourner HQ",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2034, 4628, 0),
                        new WorldPoint(2045, 4651, 0)
                ),
                new WorldPoint(2543, 3322, 0) // Entrance on surface
        ));


        ENTRANCES.add(new DungeonLocation(
                "Braindeath Island",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2110, 5182, 0),
                        new WorldPoint(2178, 5053, 0)
                ),
                new WorldPoint(3680, 3536, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Fossil Island Wyvern Cave",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3582, 10304, 0),
                        new WorldPoint(3651, 10169, 0)
                ),
                new WorldPoint(3680, 3853, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Grimstone Dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2838, 10495, 0),
                        new WorldPoint(2938, 10433, 0)
                ),
                new WorldPoint(2910, 4067, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Gryphon dungeon North",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3074, 8891, 0),
                        new WorldPoint(3256, 8764, 0)
                ),
                new WorldPoint(3125, 2485, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Gryphon dungeon South",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3207, 8821, 0),
                        new WorldPoint(3255, 8776, 0)
                ),
                new WorldPoint(3232, 2412, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Gryphon dungeon Shellbane",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3166, 8886, 0),
                        new WorldPoint(3192, 8863, 0)
                ),
                new WorldPoint(3168, 2474, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Mos Le'Harmless cave",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3711, 9469, 0),
                        new WorldPoint(3844, 9342, 0)
                ),
                new WorldPoint(3749, 2968, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Kalphite cave",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3328, 9545, 0),
                        new WorldPoint(3407, 9471, 0)
                ),
                new WorldPoint(3321, 3124, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Kalphite lair",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3172, 9531, 0),
                        new WorldPoint(3232, 9474, 0)
                ),
                new WorldPoint(3229, 3105, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Kalphite queen chamber",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3174, 9449, 0),
                        new WorldPoint(3220, 9406, 0)
                ),
                new WorldPoint(3229, 3105, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Edgeville dungeon top",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3063, 9999, 0),
                        new WorldPoint(3138, 9920, 0)
                ),
                new WorldPoint(3094, 3472, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Edgeville dungeon middle",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3067, 9922, 0),
                        new WorldPoint(3314, 9846, 0)
                ),
                new WorldPoint(3094, 3472, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Obor's lair",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3077, 9853, 0),
                        new WorldPoint(3131, 9791, 0)
                ),
                new WorldPoint(3094, 3472, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Ancient Cavern Upper level",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1796, 5433, 0),
                        new WorldPoint(1852, 5343, 0)
                ),
                new WorldPoint(2512, 3513, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Ancient Cavern Lower level",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1732, 5373, 0),
                        new WorldPoint(1793, 5319, 0)
                ),
                new WorldPoint(2512, 3513, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Dorgesh-Kaan South Dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2708, 5221, 0),
                        new WorldPoint(2735, 5245, 0)
                ),
                new WorldPoint(3206, 3217, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Ynysdail cavern",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2247, 9915, 0),
                        new WorldPoint(2297, 9833, 0)
                ),
                new WorldPoint(2218, 3470, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Araxyte cave",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3622, 9862, 0),
                        new WorldPoint(3712, 9793, 0)
                ),
                new WorldPoint(3651, 3406, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Neypotzli antechamber",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1418, 9588, 0),
                        new WorldPoint(1460, 9546, 0)
                ),
                new WorldPoint(1436, 3127, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Neypotzli ancient prison",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1340, 9595, 0),
                        new WorldPoint(1393, 9534, 0)
                ),
                new WorldPoint(1436, 3127, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Neypotzli earthbound cavern",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1345, 9731, 0),
                        new WorldPoint(1405, 9664, 0)
                ),
                new WorldPoint(1436, 3127, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Neypotzli streambound cavern",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1476, 9722, 0),
                        new WorldPoint(1533, 9665, 0)
                ),
                new WorldPoint(1436, 3127, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Miscellania Dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2497, 10304, 0),
                        new WorldPoint(2627, 10239, 0)
                ),
                new WorldPoint(2510, 3853, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Zanaris",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2362, 4479, 0),
                        new WorldPoint(2506, 4347, 0)
                ),
                new WorldPoint(3203, 3176, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Digsite skeleton cave",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3360, 9768, 0),
                        new WorldPoint(3387, 9737, 0)
                ),
                new WorldPoint(3352, 3416, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Lumbridge dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3144, 9602, 0),
                        new WorldPoint(3265, 9538, 0)
                ),
                new WorldPoint(3173, 3170, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Smoke Devil Dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2343, 9469, 0),
                        new WorldPoint(2432, 9409, 0)
                ),
                new WorldPoint(2413, 3059, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Kraken Cove",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2241, 10042, 0),
                        new WorldPoint(2303, 9986, 0)
                ),
                new WorldPoint(2281, 3612, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Farming guild scorpions",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3028, 9791, 0),
                        new WorldPoint(3063, 9756, 0)
                ),
                new WorldPoint(3059, 3370, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Stalker Den South",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1354, 9766, 0),
                        new WorldPoint(1402, 9703, 0)
                ),
                new WorldPoint(1318, 3364, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Stalker Den North",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1301, 9720, 0),
                        new WorldPoint(1404, 9664, 0)
                ),
                new WorldPoint(1299, 3378, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Legend's Guild Dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2689, 9785, 0),
                        new WorldPoint(2735, 9728, 0)
                ),
                new WorldPoint(2725, 3379, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Sourhog cave",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3150, 9718, 0),
                        new WorldPoint(3184, 9667, 0)
                ),
                new WorldPoint(3142, 3347, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Iorwerth dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3153, 12477, 0),
                        new WorldPoint(3264, 12354, 0)
                ),
                new WorldPoint(3228, 6045, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Prifddinas",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(3207, 6132, 0),
                        new WorldPoint(3318, 6028, 0)
                ),
                new WorldPoint(2238, 3284, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Brimhaven dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2635, 9602, 0),
                        new WorldPoint(2751, 9413, 0)
                ),
                new WorldPoint(2751, 3152, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Forthos dungeon east",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1824, 9979, 0),
                        new WorldPoint(1854, 9887, 0)
                ),
                new WorldPoint(1706, 3574, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Forthos dungeon west",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1792, 9978, 0),
                        new WorldPoint(1828, 9916, 0)
                ),
                new WorldPoint(1674, 3564, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Myths' guild dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(1884, 9042, 0),
                        new WorldPoint(2072, 8962, 0)
                ),
                new WorldPoint(2453, 2849, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Jormungand's prison",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2400, 10461, 0),
                        new WorldPoint(2499, 10374, 0)
                ),
                new WorldPoint(2469, 4006, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Isle of Souls Dungeon",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2240, 9276, 0),
                        new WorldPoint(2298, 9223, 0)
                ),
                new WorldPoint(2317, 2919, 0) // Entrance on surface
        ));

        ENTRANCES.add(new DungeonLocation(
                "Mor Ul Rek",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2371, 5180, 0),
                        new WorldPoint(2554, 4989, 0)
                ),
                new WorldPoint(2317, 2919, 0) // Entrance on surface
        ));
        ENTRANCES.add(new DungeonLocation(
                "Karamja vulcano",
                WorldAreaUtils.fromCorners(
                        new WorldPoint(2827, 9583, 0),
                        new WorldPoint(2880, 9538, 0)
                ),
                new WorldPoint(2857, 3160, 0) // Entrance on surface
        ));

        // Add more dungeons as needed
    }

    public static WorldPoint getEntranceFor(WorldPoint location) {
        for (DungeonLocation entrance : ENTRANCES) {
            if (entrance.contains(location)) {
                return entrance.getEntrancePoint();
            }
        }
        return null;
    }

}
