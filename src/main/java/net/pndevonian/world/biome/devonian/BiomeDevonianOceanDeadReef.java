
package net.pndevonian.world.biome.devonian;

import net.lepidodendron.ElementsLepidodendronMod;
import net.lepidodendron.block.*;
import net.lepidodendron.util.EnumBiomeTypeDevonian;
import net.lepidodendron.world.biome.devonian.BiomeDevonian;
import net.lepidodendron.world.gen.*;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@ElementsLepidodendronMod.ModElement.Tag
public class BiomeDevonianOceanDeadReef extends ElementsLepidodendronMod.ModElement {
	@GameRegistry.ObjectHolder("lepidodendron:devonian_ocean_dead_reef")
	public static final BiomeGenCustom biome = null;
	public BiomeDevonianOceanDeadReef(ElementsLepidodendronMod instance) {
		super(instance, 1591);
	}

	@Override
	public void initElements() {
		elements.biomes.add(() -> new BiomeGenCustom());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.OCEAN);
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.WATER);
		BiomeDictionary.addTypes(biome, BiomeDictionary.Type.DEAD);
	}

	static class BiomeGenCustom extends BiomeDevonian {
		public BiomeGenCustom() {
			super(new BiomeProperties("Devonian Dead Reefs").setRainfall(0.5F).setBaseHeight(-0.80F).setHeightVariation(0.03F).setTemperature(1.5F));
			setRegistryName("lepidodendron:devonian_ocean_dead_reef");
			topBlock = Blocks.SAND.getStateFromMeta(0);
			this.fillerBlock = Blocks.SAND.getStateFromMeta(0);
			decorator.treesPerChunk = -999;
			decorator.flowersPerChunk = 0;
			decorator.grassPerChunk = 0;
			decorator.mushroomsPerChunk = 0;
			decorator.bigMushroomsPerChunk = 0;
			decorator.reedsPerChunk = 0;
			decorator.cactiPerChunk = 0;
			decorator.sandPatchesPerChunk = 0;
			decorator.gravelPatchesPerChunk = 0;
			decorator.clayPerChunk = 0;
			this.spawnableMonsterList.clear();
			this.spawnableCreatureList.clear();
			this.spawnableWaterCreatureList.clear();
			this.spawnableCaveCreatureList.clear();
		}

		protected static final WorldGenArchaeopterisTree ARCHAEOPTERIS_TREE = new WorldGenArchaeopterisTree(false);
		protected static final WorldGenReef REEF_GENERATOR = new WorldGenReef();
		protected static final WorldGenBlueHole BLUE_HOLE_GENERATOR = new WorldGenBlueHole();
		protected static final WorldGenSingleStaticInWaterUpwards STATIC_GENERATOR = new WorldGenSingleStaticInWaterUpwards();
		protected static final WorldGenSingleStaticInWaterRotational STATIC_ROTATIONAL_GENERATOR = new WorldGenSingleStaticInWaterRotational();
		protected static final WorldGenSingleStaticInWaterSideways STATIC_SIDEWAYS_GENERATOR = new WorldGenSingleStaticInWaterSideways();
		protected static final WorldGenSingleAnemoneSea ANEMONE_GENERATOR = new WorldGenSingleAnemoneSea();
		protected static final WorldGenSingleSponge SPONGE_GENERATOR = new WorldGenSingleSponge();
		protected static final WorldGenSingleSpongeSideways SPONGE_SIDEWAYS_GENERATOR = new WorldGenSingleSpongeSideways();
		protected static final WorldGenSingleBlastoid BLASTOID_GENERATOR = new WorldGenSingleBlastoid();
		protected static final WorldGenSingleBlastoidSideways BLASTOID_SIDEWAYS_GENERATOR = new WorldGenSingleBlastoidSideways();
		protected static final WorldGenSingleFenestella FENESTELLA_GENERATOR = new WorldGenSingleFenestella();
		protected static final WorldGenSingleFenestellaSideways FENESTELLA_SIDEWAYS_GENERATOR = new WorldGenSingleFenestellaSideways();
		protected static final WorldGenSingleRugoseCoral RUGOSA_GENERATOR = new WorldGenSingleRugoseCoral();
		protected static final WorldGenSingleRugoseCoralSideways RUGOSA_SIDEWAYS_GENERATOR = new WorldGenSingleRugoseCoralSideways();
		protected static final WorldGenSingleTabulateCoral TABULATE_GENERATOR = new WorldGenSingleTabulateCoral();


		public WorldGenAbstractTree getRandomTreeFeature(Random rand)
		{
			return ARCHAEOPTERIS_TREE;
		}

		@Override
		public void decorate(World worldIn, Random rand, BlockPos pos)
		{

			if (net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.ROCK)) {
				int j = rand.nextInt(16) + 8;
				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				BlockPos pos1 = pos.add(j, l, k);
				if (
						(pos1.getY() < worldIn.getSeaLevel() - 6)
								&& (worldIn.getBlockState(pos1).getMaterial() == Material.WATER)
								&& (worldIn.getBlockState(pos1.up()).getMaterial() == Material.WATER)
								&& (worldIn.getBlockState(pos1.up(2)).getMaterial() == Material.WATER)
				) {
					BLUE_HOLE_GENERATOR.generate(worldIn, rand, pos1, 14);
				}

				for (int i = 0; i < 6; ++i) {
					int radius = 4;
					int jj;
					int kk;
					if (radius < 10) {
						jj = 16 + (int)Math.floor(rand.nextInt(16 - radius - 6)/2) - (int)Math.floor(rand.nextInt(16 - radius - 6)/2);
						kk = 16 + (int)Math.floor(rand.nextInt(16 - radius - 6)/2) - (int)Math.floor(rand.nextInt(16 - radius - 6)/2);
					}
					else {
						radius = 10;
						jj = 16;
						kk = 16;
					}
					int ll = rand.nextInt(worldIn.getHeight(pos.add(jj, 0, kk)).getY() + 32);
					BlockPos posReef = pos.add(jj, ll, kk);
					if (
							(posReef.getY() < worldIn.getSeaLevel())
					) {
						REEF_GENERATOR.generate(worldIn, rand, posReef, radius, BlockCoralBleached.block.getDefaultState());
					}
				}

				for (int i = 0; i < 16; ++i) {
					int radius = 2;
					int jj;
					int kk;
					if (radius < 10) {
						jj = 16 + (int)Math.floor(rand.nextInt(16 - radius - 6)/2) - (int)Math.floor(rand.nextInt(16 - radius - 6)/2);
						kk = 16 + (int)Math.floor(rand.nextInt(16 - radius - 6)/2) - (int)Math.floor(rand.nextInt(16 - radius - 6)/2);
					}
					else {
						radius = 10;
						jj = 16;
						kk = 16;
					}
					int ll = rand.nextInt(worldIn.getHeight(pos.add(jj, 0, kk)).getY() + 32);
					BlockPos posReef = pos.add(jj, ll, kk);
					if (
							(posReef.getY() < worldIn.getSeaLevel())
					) {
						REEF_GENERATOR.generate(worldIn, rand, pos1, radius, BlockCoralBleached.block.getDefaultState());
					}
				}

			}


			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 38; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					BLASTOID_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 38; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					BLASTOID_SIDEWAYS_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
			for (int i = 0; i < 2; ++i)
			{
				int j = rand.nextInt(16) + 8;
				int k = rand.nextInt(16) + 8;
				int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
				STATIC_GENERATOR.generate(BlockConulariidBrown.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 255, 0, 255);
			}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 2; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					STATIC_GENERATOR.generate(BlockConulariidMagenta.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 255, 0, 255);
				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 36; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					STATIC_GENERATOR.generate(BlockCrinoidPetalocrinus.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 255, 0, 255);
				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int i = 0; i < 6; ++i)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
					ANEMONE_GENERATOR.generate(worldIn, rand, pos.add(j, l, k));
				}







//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int i = 0; i < 36; ++i)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getHeight(pos.add(j, 0, k)).getY() + 32);
//					STATIC_GENERATOR.generate(BlockGreenLeafyAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 15, 0, 255);
//				}
//
//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 27; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_GENERATOR.generate(BlockRedAlgaeMat.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 30, 0, 255);
//				}
//
//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 17; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_GENERATOR.generate(BlockGreenAlgaeMat.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 15, 0, 255);
//				}
//
//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 27; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_GENERATOR.generate(BlockRedTuftedAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 30, 0, 255);
//				}
//
//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 27; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_SIDEWAYS_GENERATOR.generate(BlockRedTuftedAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 30, 0, 255);
//				}
//
//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 27; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_GENERATOR.generate(BlockGreenCodiumAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 15, 0, 255);
//				}
//
//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 27; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_SIDEWAYS_GENERATOR.generate(BlockGreenCodiumAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 15, 0, 255);
//				}
//
//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 27; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_GENERATOR.generate(BlockGreenCrustedAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 15, 0, 255);
//				}
//
//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 27; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_SIDEWAYS_GENERATOR.generate(BlockGreenCrustedAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 15, 0, 255);
//				}
//
//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 27; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_GENERATOR.generate(BlockGreenSproutingAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 15, 0, 255);
//				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int ii = 0; ii < 27; ++ii)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
					STATIC_GENERATOR.generate(BlockPiledAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 15, 0, 255);
				}

//			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
//				for (int ii = 0; ii < 27; ++ii)
//				{
//					int j = rand.nextInt(16) + 8;
//					int k = rand.nextInt(16) + 8;
//					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
//					STATIC_GENERATOR.generate(BlockRedLeafyAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 30, 0, 255);
//				}

			if(net.minecraftforge.event.terraingen.TerrainGen.decorate(worldIn, rand, new net.minecraft.util.math.ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.GRASS))
				for (int ii = 0; ii < 27; ++ii)
				{
					int j = rand.nextInt(16) + 8;
					int k = rand.nextInt(16) + 8;
					int l = rand.nextInt(worldIn.getSeaLevel() + 25);
					STATIC_GENERATOR.generate(BlockStalkedAlgae.block.getDefaultState(), worldIn, rand, pos.add(j, l, k), 1, 15, 0, 255);
				}

			super.decorate(worldIn, rand, pos);
		}

		@Override
		public EnumBiomeTypeDevonian getBiomeType() {
			return EnumBiomeTypeDevonian.Ocean;
		}

	}
}
