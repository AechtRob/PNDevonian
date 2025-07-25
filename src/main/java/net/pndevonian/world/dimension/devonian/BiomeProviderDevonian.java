package net.pndevonian.world.dimension.devonian;

import com.google.common.collect.Lists;
import net.lepidodendron.world.biome.devonian.BiomeDevonian;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.storage.WorldInfo;
import net.pndevonian.world.biome.devonian.BiomeDevonianOcean;
import net.pndevonian.world.dimension.devonian.GenLayerDevonian.GenLayerDevonian;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class BiomeProviderDevonian extends BiomeProvider {
    public static List<Biome> allowedBiomes = Lists.newArrayList(
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_spikes")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hills_pools")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_lycopsid_spinney")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_vale")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_forest")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hypersaline_sinkhole_transition")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hypersaline_sinkhole")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_forest")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_lycopsid_spinney")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_hills")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_mountains_early")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_springs")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_gilboa")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef_transition")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_savanna")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_coastal")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_floodplain")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_aztec_shallow")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_brackish")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_beach")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_aztec")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_swamp")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_gilboa")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_swamp")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_gilboa_lake")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_deep")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_lagoon_helper")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_savanna")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_meadow")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_deep_rocky")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_aztec")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_meadow")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_floodplain")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_lagoon")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_creek_brackish")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_dead_reef")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_vale")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_vale_edge")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_reef2")),
            Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean_algae"))
            );
    public GenLayer genBiomes;
    /** A GenLayer containing the indices into BiomeGenBase.biomeList[] */
    public GenLayer biomeIndexLayer;
    /** The biome list. */
    public final BiomeCache biomeCache;
    public final List<Biome> biomesToSpawnIn;

    protected BiomeProviderDevonian() {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = Lists.newArrayList(allowedBiomes);
    }

    public BiomeProviderDevonian(long seed, WorldType worldType, String options) {
        this();
        GenLayer[] agenlayer = GenLayerDevonian.initializeAllBiomeGenerators(seed, worldType, options);
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0];
        this.biomeIndexLayer = agenlayer[1];
    }

    public BiomeProviderDevonian(long seed, WorldInfo info) {
        this(seed, info.getTerrainType(), info.getGeneratorOptions());
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return this.biomesToSpawnIn;
    }

    @Override
    public Biome getBiome(BlockPos pos, Biome defaultBiome) {
        return this.biomeCache.getBiome(pos.getX(), pos.getZ(), BiomeDevonianOcean.biome);
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        try
        {
            for (int i = 0; i < width * height; ++i)
            {
                biomes[i] = Biome.getBiome(aint[i], Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean")));
            }

            return biomes;
        }
        catch (Throwable throwable)
        {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
            crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(biomes.length));
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(z));
            crashreportcategory.addCrashSection("w", Integer.valueOf(width));
            crashreportcategory.addCrashSection("h", Integer.valueOf(height));
            throw new ReportedException(crashreport);
        }
    }

    @Override
    public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new Biome[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0)
        {
            Biome[] abiome = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiome, 0, listToReuse, 0, width * length);
            return listToReuse;
        }
        else
        {
            int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

            for (int i = 0; i < width * length; ++i)
            {
                listToReuse[i] = Biome.getBiome(aint[i], Biome.REGISTRY.getObject(new ResourceLocation("lepidodendron:devonian_ocean")));
            }

            return listToReuse;
        }
    }

    @Override
    public boolean areBiomesViable(int x, int z, int radius, List<Biome> allowed) {
        IntCache.resetIntCache();
        int i = x - radius >> 2;
        int j = z - radius >> 2;
        int k = x + radius >> 2;
        int l = z + radius >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.genBiomes.getInts(i, j, i1, j1);
        try {
            for (int k1 = 0; k1 < i1 * j1; ++k1) {
                Biome biome = Biome.getBiome(aint[k1]);

                if (!allowed.contains(biome)) {
                    return false;
                }
                if (!(biome instanceof BiomeDevonian)) {
                    return false;
                }
            }

            return true;
        }
        catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(z));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(radius));
            crashreportcategory.addCrashSection("allowed", allowed);
            throw new ReportedException(crashreport);
        }
    }

    @Override
    @Nullable
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random) {
        IntCache.resetIntCache();
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.genBiomes.getInts(i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;
        for (int l1 = 0; l1 < i1 * j1; ++l1) {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            Biome biome = Biome.getBiome(aint[l1]);
            if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0)) {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }
        return blockpos;
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }
}