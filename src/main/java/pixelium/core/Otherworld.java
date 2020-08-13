package pixelium.core;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.PerlinNoiseGenerator;

import java.util.Random;

public class Otherworld extends ChunkGenerator {

    int currentHeight = 50;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);
        PerlinNoiseGenerator generator = new PerlinNoiseGenerator(world.getSeed());
        int absX = chunkX * 16, absZ = chunkZ * 16;

        for (int X = 0; X != 16; X++) for (int Z = 0; Z != 16; Z++) {

            double height1 = generator.noise((absX+X) / 8.0, (absZ+Z) / 8.0) + 1;
            double height2 = generator.noise((absX+X+567) / 48.0, (absZ+Z+53) / 48.0) + 1;
            double height3 = generator.noise((absX+X+12) / 256.0, (absZ+Z+34) / 256.0) + 1;
            double height4 = generator.noise((absX+X+12) / 720.0, (absZ+Z+34) / 720.0) + 1;
            double mountanity = generator.noise((absX+X) / 1200.0, (absZ+Z) / 1200.0) + 1;
            double hottity = generator.noise((absX+X + 3456) / 1400.0, (absZ+Z+1346) / 1400.0) + 1;
            double plantgrowity = generator.noise((absX+X + 34) / 256.0, (absZ+Z+46) / 256.0) + 1;



            currentHeight = 20 + (int)(
                height1 * (1 + 3 * mountanity) +
                height2 * (5 + 48 * mountanity) +
                height3 * (-32 * mountanity) +
                height4 * 60);

            int iscrystal = random.nextInt(1 + (int)Math.pow((generator.noise((absX+X) / 48.0, (absZ+Z) / 48.0) + 1) * 5, 2));
            double hottity30 = hottity * 15;
            for (int y = 0; y != 16; y++) biome.setBiome(X, y, Z, Biome.THE_END);

            if (iscrystal == 1) {
                int chance = random.nextInt(200);
                Material mat;
                if (hottity30 + random.nextInt(10) > 21) {
                    if (chance < 3) mat = Material.LIME_STAINED_GLASS;
                    else if (chance < 25) mat = Material.CYAN_STAINED_GLASS;
                    else if (chance < 70) mat = Material.YELLOW_STAINED_GLASS;
                    else mat = Material.RED_STAINED_GLASS;
                } else {
                    if (chance < 3) mat = Material.BLUE_ICE;
                    else if (chance < 25) mat = Material.BLUE_STAINED_GLASS;
                    else if (chance < 70) mat = Material.WHITE_STAINED_GLASS;
                    else mat = Material.BLACK_STAINED_GLASS;
                }
                for (int i = -3 - random.nextInt(3), crystalHeight = 2 + random.nextInt(5); i < crystalHeight; i++) {
                    chunk.setBlock(X, currentHeight + i, Z, mat);
                }
            } else for (int i = 0; i < 3; i++) {
                Material mat;
                double hottity2 = hottity * 20 + random.nextInt(2);
                if (hottity2 > 28) {
                    int chance = random.nextInt(3000);
                    if (chance < 1400) mat = Material.RED_SAND;
                    else if (chance < 2800) mat = Material.RED_SANDSTONE;
                    else if (chance < 2802) mat = Material.REDSTONE_BLOCK;
                    else if (chance < 2810) mat = Material.MAGMA_BLOCK;
                    else mat = Material.NETHERRACK;
                } else if (hottity2 > 14) {
                    int chance = random.nextInt(3000);
                    if (chance < 1400) mat = Material.ANDESITE;
                    else if (chance < 2800) mat = Material.DEAD_BRAIN_CORAL_BLOCK;
                    else if (chance < 2802) mat = Material.GRAY_CONCRETE_POWDER;
                    else mat = Material.DEAD_HORN_CORAL_BLOCK;
                } else {
                    int chance = random.nextInt(3000);
                    if (chance < 1400) mat = Material.PACKED_ICE;
                    else if (chance < 2800) mat = Material.STONE;
                    else if (chance < 2801) mat = Material.BLUE_ICE;
                    else if (chance < 2802) mat = Material.PRISMARINE_BRICKS;
                    else mat = Material.PURPLE_CONCRETE_POWDER;
                }
                int y = currentHeight - i;
                double noise3D = generator.noise((absX+X) / 20.0, (y) / 20.0, (absZ+Z) / 20.0);
                if (noise3D < 0.5) {
                    chunk.setBlock(X, y, Z, mat);
                }
            }
            for (int i = currentHeight-3; i > 5; i--) {
                double shouldbecayvity = (generator.noise((absX+X) / 96.0, i / 96.0, (absZ+Z +34) / 96.0) + 1 +
                        generator.noise((absX+X) / 48.0, i / 48.0, (absZ+Z) / 48.0) + 1 +
                        generator.noise((absX+X) / 7.0, i / 7.0, (absZ+Z) / 7.0) +
                        generator.noise((absX+X) / 16.0, i / 16.0, (absZ+Z) / 16.0) * mountanity / 2) * ((i - 5) / (double) (currentHeight - 3) / 4.0 + 0.75);
                if (shouldbecayvity < 2.0) {
                    Material mat;
                    int chance = random.nextInt(3200);
                    if (hottity30 + random.nextInt(10) > 21) {
                        if (chance < 1400) mat = Material.GRANITE;
                        else if (chance < 2800) mat = Material.COARSE_DIRT;
                        else if (chance < 2807) mat = Material.SLIME_BLOCK;
                        else if (chance < 2812 && i < 12 && shouldbecayvity > 1) mat = Material.MAGMA_BLOCK;
                        else mat = Material.GRANITE;
                    } else {
                        if (chance < 1400) mat = Material.ANDESITE;
                        else if (chance < 2800) mat = Material.STONE;
                        else if (chance < 2801) mat = Material.IRON_ORE;
                        else if (chance < 2802) mat = Material.EMERALD_ORE;
                        else if (chance < 2803) mat = Material.PRISMARINE;
                        else if (chance < 2805 && i < 12 && shouldbecayvity > 1) mat = Material.SEA_LANTERN;
                        else mat = Material.ANDESITE;
                    }
                    chunk.setBlock(X, i, Z, mat);
                } else {
                    double shouldbeweirdthingity = (generator.noise((absX+X + 356) / 24.0, i / 24.0, (absZ+Z +34) / 24.0) + 1 +
                            generator.noise((absX+X + 34) / 4.0, i / 4.0, (absZ+Z +3456) / 4.0) + 1) * ((i - 5) / (double) (currentHeight - 3) / 4 + 0.75);
                    if (shouldbeweirdthingity > 2.7) chunk.setBlock(X, i, Z, Material.GLOWSTONE);
                    else if (shouldbeweirdthingity > 2.5) chunk.setBlock(X, i, Z, Material.HONEY_BLOCK);
                    else if (shouldbeweirdthingity > 2.2) chunk.setBlock(X, i, Z, Material.YELLOW_STAINED_GLASS);
                    else {
                        double shouldbeweirdthingity2 = (generator.noise((absX+X + 3456) / 5.6, i / 5.6, (absZ+Z + 54) / 5.6) + 1) * (1 - (i - 5) / (double) (currentHeight - 3) / 2) * plantgrowity;
                        if (shouldbeweirdthingity2 > 1.2) chunk.setBlock(X, i, Z, Material.NETHER_WART_BLOCK);
                        else if (i < 60) chunk.setBlock(X, i, Z, Material.WATER);
                    }
                }
            }
            chunk.setBlock(X, 5, Z, Material.OBSIDIAN);
        }
        return chunk;
    }
}
