package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianEstuary1 extends GenLayer
{

    public Biome DEVONIAN_OCEAN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_deep"));
    public int DEVONIAN_OCEAN_ID = Biome.getIdForBiome(DEVONIAN_OCEAN);
    public Biome DEVONIAN_OCEAN_SHORE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean"));
    public int DEVONIAN_OCEAN_SHORE_ID = Biome.getIdForBiome(DEVONIAN_OCEAN_SHORE);
    public Biome DEVONIAN_BRACKISH = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_brackish"));
    public int DEVONIAN_BRACKISH_ID = Biome.getIdForBiome(DEVONIAN_BRACKISH);
    public Biome DEVONIAN_REEF = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef"));
    public int DEVONIAN_REEF_ID = Biome.getIdForBiome(DEVONIAN_REEF);
    public Biome DEVONIAN_DEAD_REEF = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_dead_reef"));
    public int DEVONIAN_DEAD_REEF_ID = Biome.getIdForBiome(DEVONIAN_DEAD_REEF);
    public Biome DEVONIAN_REEF_EDGE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef_transition"));
    public int DEVONIAN_REEF_EDGE_ID =  Biome.getIdForBiome(DEVONIAN_REEF_EDGE);
    public Biome DEVONIAN_OCEAN_ROCKY = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_deep_rocky"));
    public int DEVONIAN_OCEAN_ROCKY_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN_ROCKY);

    public Biome DEVONIAN_WET_MEADOW = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_meadow"));
    public int DEVONIAN_WET_MEADOW_ID =  Biome.getIdForBiome(DEVONIAN_WET_MEADOW);
    public Biome DEVONIAN_WATTIEZA = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_gilboa"));
    public int DEVONIAN_WATTIEZA_ID =  Biome.getIdForBiome(DEVONIAN_WATTIEZA);
    public Biome DEVONIAN_SAVANNA = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_savanna"));
    public int DEVONIAN_SAVANNA_ID =  Biome.getIdForBiome(DEVONIAN_SAVANNA);
    public Biome DEVONIAN_MOUNTAINS_EARLY = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains_early"));
    public int DEVONIAN_MOUNTAINS_EARLY_ID =  Biome.getIdForBiome(DEVONIAN_MOUNTAINS_EARLY);
    public Biome DEVONIAN_SPRINGS = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_springs"));
    public int DEVONIAN_SPRINGS_ID =  Biome.getIdForBiome(DEVONIAN_SPRINGS);

    public Biome DEVONIAN_ESTUARY = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_lagoon"));
    public int DEVONIAN_ESTUARY_ID =  Biome.getIdForBiome(DEVONIAN_ESTUARY);


    public GenLayerDevonianEstuary1(long seed, GenLayer genLayer)
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

                //if (k != SILURIAN_HILLS_ID
                //        && k != SILURIAN_ICE_EDGE_ID
                //        && k != SILURIAN_ICE_ID
                //        && k != SILURIAN_ICE_SPIKES_ID
                //        && k != SILURIAN_HILLS_ID
                //        && k != SILURIAN_HILLS_EDGE_ID
                //        && k != SILURIAN_HILLS_CENTRE_ID
                //)
                {
                    if ((!isOcean(k)) && isEarly(k))
                    {
                        int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                        int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                        int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                        int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                        if (!isOcean(l1) && !isOcean(k2) && !isOcean(j3) && !isOcean(i4))
                        {
                            aint1[j + i * areaWidth] = k;
                        }
                        else
                        {
                            if (nextInt(3) == 0) {
                                aint1[j + i * areaWidth] = DEVONIAN_ESTUARY_ID;
                            }
                            else {
                                aint1[j + i * areaWidth] = k;
                            }
                        }
                    }
                    else
                    {
                        aint1[j + i * areaWidth] = k;
                    }
                }
                //else
                //{
                //    aint1[j + i * areaWidth] = k;
                //}
            }
        }

        return aint1;
    }

    private boolean isOcean(int biomeID) {
        if (biomeID == DEVONIAN_OCEAN_ID || biomeID == DEVONIAN_OCEAN_SHORE_ID
                || biomeID == DEVONIAN_BRACKISH_ID
                || biomeID == DEVONIAN_REEF_ID
                || biomeID == DEVONIAN_DEAD_REEF_ID
                || biomeID == DEVONIAN_REEF_EDGE_ID
                || biomeID == DEVONIAN_OCEAN_ROCKY_ID) {
            return true;
        }
        return false;
    }



    public boolean isEarly(int biomeID) {
        if (biomeID == DEVONIAN_WATTIEZA_ID || biomeID == DEVONIAN_WET_MEADOW_ID
                || biomeID == DEVONIAN_MOUNTAINS_EARLY_ID || biomeID == DEVONIAN_SAVANNA_ID
                || biomeID == DEVONIAN_SPRINGS_ID) {
            return true;
        }
        return false;
    }

}
