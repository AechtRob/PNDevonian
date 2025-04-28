package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerDevonianNearSeaBiomes extends GenLayer
{

    public Biome DEVONIAN_OCEAN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_deep"));
    public int DEVONIAN_OCEAN_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN);
    public Biome DEVONIAN_OCEAN_SHORE = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean"));
    public int DEVONIAN_OCEAN_SHORE_ID =  Biome.getIdForBiome(DEVONIAN_OCEAN_SHORE);

    public Biome DEVONIAN_FLOODPLAIN = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_floodplain"));
    public int DEVONIAN_FLOODPLAIN_ID =  Biome.getIdForBiome(DEVONIAN_FLOODPLAIN);
    public Biome DEVONIAN_BRACKISH = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_brackish"));
    public int DEVONIAN_BRACKISH_ID =  Biome.getIdForBiome(DEVONIAN_BRACKISH);
    public Biome DEVONIAN_SWAMP = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_swamp"));
    public int DEVONIAN_SWAMP_ID =  Biome.getIdForBiome(DEVONIAN_SWAMP);
    public Biome DEVONIAN_GLADES = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_lycopsid_spinney"));
    public int DEVONIAN_GLADES_ID =  Biome.getIdForBiome(DEVONIAN_GLADES);

    public Biome DEVONIAN_WET_MEADOW = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_meadow"));
    public int DEVONIAN_WET_MEADOW_ID =  Biome.getIdForBiome(DEVONIAN_WET_MEADOW);
    public Biome DEVONIAN_WATTIEZA = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_gilboa"));
    public int DEVONIAN_WATTIEZA_ID =  Biome.getIdForBiome(DEVONIAN_WATTIEZA);
    public Biome DEVONIAN_SAVANNA = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_savanna"));
    public int DEVONIAN_SAVANNA_ID =  Biome.getIdForBiome(DEVONIAN_SAVANNA);
    public Biome DEVONIAN_MOUNTAINS_EARLY = Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains_early"));
    public int DEVONIAN_MOUNTAINS_EARLY_ID =  Biome.getIdForBiome(DEVONIAN_MOUNTAINS_EARLY);

    private final int ShoreBiomes[] = new int[] {
            DEVONIAN_FLOODPLAIN_ID,
            DEVONIAN_BRACKISH_ID,
            DEVONIAN_SWAMP_ID,
            DEVONIAN_GLADES_ID
    };

    private final int ShoreBiomesEarly[] = new int[] {
            DEVONIAN_WATTIEZA_ID,
            DEVONIAN_WET_MEADOW_ID
    };

    public GenLayerDevonianNearSeaBiomes(long seed, GenLayer genLayer)
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

                if (!isOcean(k))
                {
                    int l1 = aint[j + 1 + (i + 1 - 1) * (areaWidth + 2)];
                    int k2 = aint[j + 1 + 1 + (i + 1) * (areaWidth + 2)];
                    int j3 = aint[j + 1 - 1 + (i + 1) * (areaWidth + 2)];
                    int i4 = aint[j + 1 + (i + 1 + 1) * (areaWidth + 2)];
                    boolean flag = (isOcean(l1) || isOcean(k2) || isOcean(j3) || isOcean(i4));
                    if (flag && nextInt(4) == 0) {
                        if (isEarly(k)) {
                            aint1[j + i * areaWidth] = ShoreBiomesEarly[nextInt(ShoreBiomesEarly.length)];
                        } else {
                            aint1[j + i * areaWidth] = ShoreBiomes[nextInt(ShoreBiomes.length)];
                        }
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

    public boolean isOcean(int biomeID) {
        if (biomeID == DEVONIAN_OCEAN_SHORE_ID || biomeID == DEVONIAN_OCEAN_ID) {
            return true;
        }
        return false;
    }

    public boolean isEarly(int biomeID) {
        if (biomeID == DEVONIAN_WATTIEZA_ID || biomeID == DEVONIAN_WET_MEADOW_ID
            || biomeID == DEVONIAN_MOUNTAINS_EARLY_ID || biomeID == DEVONIAN_SAVANNA_ID) {
            return true;
        }
        return false;
    }
    
}
