package kmerrill285.trewrite.events;

import java.util.HashMap;
import java.util.List;

import kmerrill285.trewrite.core.inventory.InventoryTerraria;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.entities.npc.EntityGuide;
import kmerrill285.trewrite.util.Util;
import kmerrill285.trewrite.world.WorldStateHolder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent.Load;
import net.minecraftforge.event.world.WorldEvent.Save;
import net.minecraftforge.event.world.WorldEvent.Unload;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class WorldEvents {
	public static HashMap<String, Entity> summoningTargets = new HashMap<String, Entity>();
	public static HashMap<String, List<Entity>> summons = new HashMap<String, List<Entity>>();
	public static HashMap<String, Double> summon_number = new HashMap<String, Double>();

	public static HashMap<String, Entity> pets = new HashMap<String, Entity>();
	public static HashMap<String, Entity> light_pets = new HashMap<String, Entity>();

	public static InventoryTerraria getOrLoadInventory(PlayerEntity player) {
		if (player.world.isRemote) return null;
		World w = player.world.getServer().getWorld(DimensionType.OVERWORLD);
		if (!(w instanceof ServerWorld)) {
			return null;
		}
		InventoryTerraria a = WorldStateHolder.get(player.world).inventories.get(player.getScoreboardName());
		if (a != null) return a;
		
		InventoryTerraria inventory = new InventoryTerraria();
		inventory.giveTools();
		WorldStateHolder.get(player.world).inventories.put(player.getScoreboardName(), inventory);
		WorldStateHolder.get(player.world).markDirty();
		return inventory;
	}
	
	@SubscribeEvent
	public static void worldSaveEvent(Save event) {		
		WorldStateHolder.get(event.getWorld()).markDirty();
	}
	
	@OnlyIn(value=Dist.CLIENT)
	@SubscribeEvent
	public static void worldUnloadEventClient(Unload event) {
		Util.refreshDimensionRenderer = true;
	}
	@SubscribeEvent
	
	public static void worldUnloadEvent(Unload event) {
		WorldStateHolder.get(event.getWorld()).markDirty();
	}
	
	@SubscribeEvent
	@OnlyIn(value=Dist.CLIENT)
	public static void worldLoadEvent(Load event) {
		
	}
	
	@SubscribeEvent
	public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
					
		 if (event.getEntity() instanceof PlayerEntity) {
			 if (WorldStateHolder.get(event.getWorld()).firstJoin == false) { 
				 EntityGuide eye = (EntityGuide)EntitiesT.GUIDE.create(event.getWorld(), (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, event.getEntity().getPosition(), SpawnReason.EVENT, false, false);
	             eye.setPosition(event.getEntity().posX + 12, event.getEntity().posY, event.getEntity().posZ);
	             event.getWorld().addEntity(eye);	 
	             WorldStateHolder.get(event.getWorld()).firstJoin = true;
			 }
		 }
	}
}
