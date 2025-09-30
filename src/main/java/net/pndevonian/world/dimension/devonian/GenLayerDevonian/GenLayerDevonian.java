package net.pndevonian.world.dimension.devonian.GenLayerDevonian;

import net.lepidodendron.LepidodendronConfig;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.*;

public class GenLayerDevonian {

    protected GenLayer parent;

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, String options) {

        GenLayer biomes = new GenLayerDevonianBiomes(1L);
        biomes = new GenLayerFuzzyZoom(2000L, biomes);
        if (!LepidodendronConfig.doShrinkBiomes) {
            biomes = new GenLayerZoom(2001L, biomes);
        }

        //Surround mountains with either Savanna  or Forest
        biomes = new GenLayerDevonianBorderMountains(724L, biomes);
        biomes = new GenLayerDevonianEstuary1(1000L, biomes);
        biomes = new GenLayerZoom(1000L, biomes);

        //If mountains surround, add Vale
        biomes = new GenLayerDevonianVale(1015L, biomes);
        //Surround with the various coastal-adjacent biomes
        biomes = new GenLayerDevonianNearSeaBiomes(1400L, biomes);
        //Sort out shallow and deep Oceans
        biomes = new GenLayerDevonianDeepOcean(1109L, biomes);
        biomes = new GenLayerDevonianShallowOcean(1400L, biomes);
        biomes = new GenLayerDevonianEstuary2(1000L, biomes);
        biomes = new GenLayerFuzzyZoom(1009L, biomes);

        //If mountains surround, add Springs in Early Dev
        biomes = new GenLayerDevonianSprings(1215L, biomes);
        //Diversify Ocean biomes:
        biomes = new GenLayerDiversifyOcean(1431L, biomes);
        //biomes = new GenLayerZoom(1000L, biomes);
        biomes = new GenLayerDiversifyGilboaLake(862L, biomes);

        biomes = new GenLayerZoom(1000L, biomes);
        //Extend the reefs:
        biomes = new GenLayerDevonianReefExtend(1630L, biomes);
        biomes = new GenLayerDevonianValeEdge(7920L, biomes);
        biomes = new GenLayerDevonianEstuary2(1000L, biomes);
        biomes = new GenLayerFuzzyZoom(1009L, biomes);

        //Edge the reefs and some more diversification
        biomes = new GenLayerDevonianReefEdge(1130L, biomes);
        biomes = new GenLayerSpikesDevonian(950L, biomes);
        biomes = new GenLayerSinkholesDevonian(850L, biomes);
        biomes = new GenLayerDevonianEstuary2(1000L, biomes);
        biomes = new GenLayerDiversifyForestPools(3L, biomes);
        biomes = new GenLayerSmooth(703L, biomes);
        biomes = new GenLayerFuzzyZoom(1000L, biomes);

        biomes = new GenLayerDevonianBeach(1050L, biomes);
        biomes = new GenLayerDiversifyPolar(5550L, biomes);
        biomes = new GenLayerSmooth(705L, biomes);
        biomes = new GenLayerFuzzyZoom(1001L, biomes);

        biomes = new GenLayerSmooth(706L, biomes);
        biomes = new GenLayerFuzzyZoom(1002L, biomes);
        biomes = new GenLayerSinkholeTransition(1001L, biomes);
        biomes = new GenLayerZoom(1006L, biomes);

        //Build and superimpose creeks:
        GenLayer genlayercreek = new GenLayerRiverInit(100L, biomes);
        GenLayer genlayercreek2 = GenLayerZoom.magnify(1000L, genlayercreek, 1);
        GenLayer genlayercreek3 = GenLayerZoom.magnify(1000L, genlayercreek2, 2);
        GenLayer genlayercreek4 = GenLayerZoom.magnify(1000L, genlayercreek3, 2);
        GenLayer genlayercreek5 = new GenLayerRiver(1L, genlayercreek4);
        GenLayer genlayercreek6 = new GenLayerSmooth(1000L, genlayercreek5);
        GenLayer genlayercreekfinal = new GenLayerDevonianRiverMix(100L, biomes, genlayercreek6);

        //Additional streams in Aztec Flats:
        GenLayer genlayerstream = new GenLayerRiverInit(110L, biomes);
        GenLayer genlayerstream2 = GenLayerZoom.magnify(1100L, genlayerstream, 3);
        GenLayer genlayerstream4 = new GenLayerRiver(11L, genlayerstream2);
        GenLayer genlayerstream5 = new GenLayerSmooth(1100L, genlayerstream4);
        GenLayer genlayerstreamfinal = new GenLayerDevonianFloodplainRiverMixSpecial(1100L, genlayercreekfinal, genlayerstream5);

        GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, genlayerstreamfinal);

        genlayerstreamfinal.initWorldGenSeed(seed);
        genlayervoronoizoom.initWorldGenSeed(seed);
        biomes.initWorldGenSeed(seed);

        genlayervoronoizoom.initWorldGenSeed(seed);
        return (new GenLayer[] { genlayerstreamfinal, genlayervoronoizoom, genlayerstreamfinal });
    }

}