package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.pndevonian.world.biome.devonian.BiomeDevonianForest;
import net.pndevonian.world.biome.devonian.BiomeDevonianLycopsidSpinney;
import net.pndevonian.world.biome.devonian.BiomeDevonianSwamp;

public class GenLayerSpikesDevonian extends GenLayer {

    public  Biome DEVONIAN_HILLS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hills"));
    public  int DEVONIAN_HILLS_ID =  Biome.getIdForBiome(DEVONIAN_HILLS);
    public  Biome DEVONIAN_SWAMP = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_swamp"));
    public  int DEVONIAN_SWAMP_ID =  Biome.getIdForBiome(DEVONIAN_SWAMP);
    public  Biome DEVONIAN_SPINNEY = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_lycopsid_spinney"));
    public  int DEVONIAN_SPINNEY_ID =  Biome.getIdForBiome(DEVONIAN_SPINNEY);
    public  Biome DEVONIAN_SPIKES = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_spikes"));
    public  int DEVONIAN_SPIKES_ID =  Biome.getIdForBiome(DEVONIAN_SPIKES);

    private final int ForestBiomes[] = new int[] {
        DEVONIAN_HILLS_ID,
        DEVONIAN_HILLS_ID,
        DEVONIAN_HILLS_ID,
        DEVONIAN_SPIKES_ID
    };

    private final int SwampBiomes[] = new int[] {
            DEVONIAN_SWAMP_ID,
            DEVONIAN_SWAMP_ID,
            DEVONIAN_SWAMP_ID,
            DEVONIAN_SWAMP_ID,
            DEVONIAN_SWAMP_ID,
            DEVONIAN_SPIKES_ID
    };

    private final int SpinneyBiomes[] = new int[] {
            DEVONIAN_SPINNEY_ID,
            DEVONIAN_SPINNEY_ID,
            DEVONIAN_SPINNEY_ID,
            DEVONIAN_SPINNEY_ID,
            DEVONIAN_SPINNEY_ID,
            DEVONIAN_SPIKES_ID
    };

    public GenLayerSpikesDevonian(long seed, GenLayer genlayer) {
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
                        output[i] = ForestBiomes[nextInt(ForestBiomes.length)];
                    else if (Biome.getBiome(center) == BiomeDevonianSwamp.biome)
                        output[i] = SwampBiomes[nextInt(SwampBiomes.length)];
                    else if (Biome.getBiome(center) == BiomeDevonianLycopsidSpinney.biome)
                        output[i] = SpinneyBiomes[nextInt(SpinneyBiomes.length)];
                    else output[i] = center;
                } else output[i] = center;
            }
        }
        return output;
    }

}