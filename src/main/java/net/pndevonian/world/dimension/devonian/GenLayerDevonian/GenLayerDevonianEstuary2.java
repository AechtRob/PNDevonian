package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianEstuary2 extends GenLayer
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
    public Biome DEVONIAN_REEF2 = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef2"));
    public int DEVONIAN_OCEAN_REEF2_ID = Biome.getIdForBiome(DEVONIAN_REEF2);
    public Biome DEVONIAN_OCEAN_ALGAE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_algae"));
    public int DEVONIAN_OCEAN_ALGAE_ID = Biome.getIdForBiome(DEVONIAN_OCEAN_ALGAE);
    public Biome DEVONIAN_CONULARIID = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_conulariid"));
    public int DEVONIAN_CONULARIID_ID =  Biome.getIdForBiome(DEVONIAN_CONULARIID);

    public Biome DEVONIAN_ESTUARY = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_lagoon"));
    public int DEVONIAN_ESTUARY_ID =  Biome.getIdForBiome(DEVONIAN_ESTUARY);
    public Biome DEVONIAN_ESTUARY_HELPER = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_lagoon_helper"));
    public int DEVONIAN_ESTUARY_HELPER_ID =  Biome.getIdForBiome(DEVONIAN_ESTUARY_HELPER);


    public GenLayerDevonianEstuary2(long seed, GenLayer genLayer)
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

                if (k == DEVONIAN_ESTUARY_HELPER_ID)
                {
                    int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];

                    if (!isOcean(l1) && !isOcean(k2) && !isOcean(j3) && !isOcean(i4))
                    {
                        aint1[j + i * areaWidth] = DEVONIAN_ESTUARY_ID;
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

    private boolean isOcean(int biomeID) {
        if (biomeID == DEVONIAN_OCEAN_ID || biomeID == DEVONIAN_OCEAN_SHORE_ID
                || biomeID == DEVONIAN_BRACKISH_ID
                || biomeID == DEVONIAN_REEF_ID
                || biomeID == DEVONIAN_DEAD_REEF_ID
                || biomeID == DEVONIAN_REEF_EDGE_ID
                || biomeID == DEVONIAN_OCEAN_ROCKY_ID
                || biomeID == DEVONIAN_OCEAN_ALGAE_ID
                || biomeID == DEVONIAN_OCEAN_REEF2_ID
                || biomeID == DEVONIAN_CONULARIID_ID
        ) {
            return true;
        }
        return false;
    }

}
