package kmerrill285.trewrite.world.biome;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.world.biome.features.TerrariaFeatures;
import kmerrill285.trewrite.world.biome.features.TerrariaOreFeatureConfig;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Builder;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BorealHillsBiome extends Biome {
   protected BorealHillsBiome() {
      super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlocksT.SNOW.getDefaultState(), BlocksT.SNOW.getDefaultState(), BlocksT.STONE_BLOCK.getDefaultState()))).precipitation(RainType.SNOW).category(Category.EXTREME_HILLS).depth(0.1F).scale(0.3F).temperature(0.2F).downfall(0.8F).waterColor(4159204).waterFogColor(329011).parent((String)null));
      this.addCarver(Carving.AIR, Biome.createCarver(TerrariaFeatures.CAVE, new ProbabilityConfig(0.14285715F)));
      this.setRegistryName("trewrite:boreal_hills");
      this.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(TerrariaFeatures.TREES, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(70)));
      this.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(TerrariaFeatures.ORES, new TerrariaOreFeatureConfig(BlocksT.STONE_BLOCK.getDefaultState(), BlocksT.IRON_ORE.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
      this.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(TerrariaFeatures.ORES, new TerrariaOreFeatureConfig(BlocksT.DIRT_BLOCK.getDefaultState(), BlocksT.IRON_ORE.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
      this.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(TerrariaFeatures.ORES, new TerrariaOreFeatureConfig(BlocksT.GRASS_BLOCK.getDefaultState(), BlocksT.IRON_ORE.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
      this.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(TerrariaFeatures.ORES, new TerrariaOreFeatureConfig(BlocksT.SNOW.getDefaultState(), BlocksT.ICE.getDefaultState(), 25), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
      this.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(TerrariaFeatures.EVERGREEN_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(70)));
   }

   public float getTemperature(BlockPos pos) {
      return super.getTemperature(pos);
   }

   public boolean doesSnowGenerate(IWorldReader worldIn, BlockPos pos) {
      return true;
   }
}
