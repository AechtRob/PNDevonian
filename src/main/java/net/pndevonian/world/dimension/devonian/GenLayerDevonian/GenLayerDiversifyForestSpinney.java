package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.pndevonian.world.biome.devonian.BiomeDevonianForest;

public class GenLayerDiversifyForestSpinney extends GenLayer {


    public Biome DEVONIAN_FOREST = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hills"));
    public int DEVONIAN_FOREST_ID =  Biome.getIdForBiome(DEVONIAN_FOREST);
    public Biome DEVONIAN_SPINNEY = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_lycopsid_spinney"));
    public int DEVONIAN_SPINNEY_ID =  Biome.getIdForBiome(DEVONIAN_SPINNEY);


    private final int Biomes[] = new int[] {
            DEVONIAN_FOREST_ID,
            DEVONIAN_FOREST_ID,
            DEVONIAN_SPINNEY_ID
    };

    public GenLayerDiversifyForestSpinney(long seed, GenLayer genlayer) {
        super(seed);
        this.parent = genlayer;
    }

    @Override
    public int[] getInts(int x, int z, int width, int height) {
        return diversify(x, z, width, height);
    }

    private int[] diversify(int x, int z, int width, int height) {
        int input[] = this.parent.getInts(x, z, width, height);
        int output[] = IntCache.getIntCache(width * height);
        EnumBiomeTypeDevonian type;
        for (int zOut = 0; zOut < height; zOut++) {
            for (int xOut = 0; xOut < width; xOut++) {
                int i = xOut + zOut * width;
                int center = input[i];
                initChunkSeed(xOut + x, zOut + z);
                if (nextInt(2) == 0) {
                    if (Biome.getBiome(center) == BiomeDevonianForest.biome)
                        output[i] = Biomes[nextInt(Biomes.length)];
                    else output[i] = center;
                } else output[i] = center;
            }
        }
        return output;
    }

}