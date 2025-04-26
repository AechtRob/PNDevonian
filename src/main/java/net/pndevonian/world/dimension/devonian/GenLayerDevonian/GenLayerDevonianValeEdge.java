package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianValeEdge extends GenLayer
{

    public Biome MOUNTAINS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains"));
    public int MOUNTAINS_ID = Biome.getIdForBiome(MOUNTAINS);
    public Biome DEVONIAN_VALE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_vale"));
    public int DEVONIAN_VALE_ID = Biome.getIdForBiome(DEVONIAN_VALE);
    public Biome DEVONIAN_VALE_EDGE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_vale_edge"));
    public int DEVONIAN_VALE_EDGE_ID =  Biome.getIdForBiome(DEVONIAN_VALE_EDGE);

    public GenLayerDevonianValeEdge(long seed, GenLayer genLayer)
    {
        super(seed);
        this.parent = genLayer;
    }

    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
       int[] aint = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
       int[] aint1 = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaHeight; ++i)
        {
            for (int j = 0; j < areaWidth; ++j)
            {
                this.initChunkSeed((long)(j + areaX), (long)(i + areaY));
               int k = aint[j + 1 + (i + 1) * (areaWidth + 2)];
                //Biome biome = Biome.getBiome(k)
                if (k == DEVONIAN_VALE_ID)
                {
                   int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                   int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                   int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                   int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                    if (l1 == MOUNTAINS_ID || k2 == MOUNTAINS_ID || j3 == MOUNTAINS_ID || i4 == MOUNTAINS_ID)
                    {
                        aint1[j + i * areaWidth] = DEVONIAN_VALE_EDGE_ID;
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = k;
                    }
                }
                else
                {
                    aint1[j + i * areaWidth] = k;
                }

            }
        }

        return aint1;
    }

}
