package kmerrill285.trewrite.world.biome;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.world.biome.features.TerrariaFeatures;
import kmerrill285.trewrite.world.biome.features.TerrariaOreFeatureConfig;
import net.minecraft.util.math.BlockPos;
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

public class DryForestBiome extends Biome {
   protected DryForestBiome() {
      super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlocksT.GRASS_BLOCK.getDefaultState(), BlocksT.DIRT_BLOCK.getDefaultState(), BlocksT.STONE_BLOCK.getDefaultState()))).precipitation(RainType.RAIN).category(Category.EXTREME_HILLS).depth(0.1F).scale(0.5F).temperature(0.2F).downfall(0.3F).waterColor(4159204).waterFogColor(329011).parent((String)null));
      this.addCarver(Carving.AIR, Biome.createCarver(TerrariaFeatures.CAVE, new ProbabilityConfig(0.14285715F)));
      this.setRegistryName("trewrite:dry_forest");
      this.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(TerrariaFeatures.FOREST_TREES, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(70)));
      this.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(TerrariaFeatures.ORES, new TerrariaOreFeatureConfig(BlocksT.STONE_BLOCK.getDefaultState(), BlocksT.IRON_ORE.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
      this.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(TerrariaFeatures.ORES, new TerrariaOreFeatureConfig(BlocksT.DIRT_BLOCK.getDefaultState(), BlocksT.IRON_ORE.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
      this.addFeature(Decoration.UNDERGROUND_ORES, Biome.createDecoratedFeature(TerrariaFeatures.ORES, new TerrariaOreFeatureConfig(BlocksT.GRASS_BLOCK.getDefaultState(), BlocksT.IRON_ORE.getDefaultState(), 17), Placement.COUNT_RANGE, new CountRangeConfig(20, 0, 0, 128)));
      this.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(TerrariaFeatures.LIVINGWOOD_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(48)));
      this.addFeature(Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(TerrariaFeatures.FOREST_SHRUBS, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(12)));
   }

   public float getTemperature(BlockPos pos) {
      return super.getTemperature(pos);
   }
}
