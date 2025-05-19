package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.pndevonian.world.biome.devonian.BiomeDevonianForest;

public class GenLayerDiversifyForestPools extends GenLayer {


    public Biome DEVONIAN_FOREST = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hills"));
    public int DEVONIAN_FOREST_ID =  Biome.getIdForBiome(DEVONIAN_FOREST);
    public Biome DEVONIAN_POOLS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hills_pools"));
    public int DEVONIAN_POOLS_ID =  Biome.getIdForBiome(DEVONIAN_POOLS);

    private final int HillsBiomes[] = new int[] {
            DEVONIAN_POOLS_ID,
            DEVONIAN_FOREST_ID,
            DEVONIAN_FOREST_ID,
            DEVONIAN_FOREST_ID
    };

    public GenLayerDiversifyForestPools(long seed, GenLayer genLayer)
    {
        super(seed);
        this.parent = genLayer;
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
                int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];

                if (k == DEVONIAN_FOREST_ID)
                {
                    int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                    boolean flag = (
                            (l1 == DEVONIAN_FOREST_ID)
                                    && (k2 == DEVONIAN_FOREST_ID)
                                    && (j3 == DEVONIAN_FOREST_ID)
                                    && (i4 == DEVONIAN_FOREST_ID)
                    );
                    if (flag)
                    {
                        aint1[j + i * areaWidth] = HillsBiomes[nextInt(HillsBiomes.length)];
                    }
                    else {
                        aint1[j + i * areaWidth] = k;
                    }
                }
                else {
                    aint1[j + i * areaWidth] = k;
                }
            }
        }

        return aint1;
    }

}
