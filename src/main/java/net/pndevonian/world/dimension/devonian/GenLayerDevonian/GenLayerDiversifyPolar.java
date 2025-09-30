package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.pndevonian.world.biome.devonian.BiomeDevonianForest;
import net.pndevonian.world.biome.devonian.BiomeDevonianLycopsidSpinney;
import net.pndevonian.world.biome.devonian.BiomeDevonianOceanConulariid;
import net.pndevonian.world.biome.devonian.BiomeDevonianSwamp;

public class GenLayerDiversifyPolar extends GenLayer {

    public  Biome DEVONIAN_WATER = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_conulariid"));
    public  int DEVONIAN_WATER_ID =  Biome.getIdForBiome(DEVONIAN_WATER);
    public  Biome DEVONIAN_LAND = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_conulariid_land"));
    public  int DEVONIAN_LAND_ID =  Biome.getIdForBiome(DEVONIAN_LAND);

    private final int PolarBiomes[] = new int[] {
            DEVONIAN_WATER_ID,
            DEVONIAN_LAND_ID,
            DEVONIAN_LAND_ID,
            DEVONIAN_LAND_ID
    };

    public GenLayerDiversifyPolar(long seed, GenLayer genlayer) {
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
                    if (Biome.getBiome(center) == BiomeDevonianOceanConulariid.biome)
                        output[i] = PolarBiomes[nextInt(PolarBiomes.length)];
                    else output[i] = center;
                } else output[i] = center;
            }
        }
        return output;
    }

}