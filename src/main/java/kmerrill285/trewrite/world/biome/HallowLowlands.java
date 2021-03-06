package kmerrill285.trewrite.world.biome;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.world.biome.features.TerrariaFeatures;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Builder;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.GenerationStage.Carving;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class HallowLowlands extends Biome {
	   protected HallowLowlands() {
		      super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, new SurfaceBuilderConfig(BlocksT.PRE_HALLOW_GRASS.getDefaultState(), BlocksT.DIRT_BLOCK.getDefaultState(), BlocksT.PEARLSTONE.getDefaultState()))).precipitation(RainType.RAIN).category(Category.EXTREME_HILLS).depth(0.125F).scale(0.05F).temperature(0.2F).downfall(0.3F).waterColor(4159204).waterFogColor(329011).parent((String)null));
		      this.addCarver(Carving.AIR, Biome.createCarver(TerrariaFeatures.CAVE, new ProbabilityConfig(0.14285715F)));
		      this.setRegistryName("trewrite:hallow_lowlands");
		      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(TerrariaFeatures.RUINED_HOUSE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
		      this.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(TerrariaFeatures.SKY_ISLAND, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
	   }
}