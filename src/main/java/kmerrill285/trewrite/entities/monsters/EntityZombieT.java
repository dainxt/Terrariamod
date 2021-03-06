package kmerrill285.trewrite.entities.monsters;

import kmerrill285.trewrite.core.items.ItemStackT;
import kmerrill285.trewrite.core.sounds.SoundsT;
import kmerrill285.trewrite.entities.EntityCoin;
import kmerrill285.trewrite.entities.EntityHeart;
import kmerrill285.trewrite.entities.EntityItemT;
import kmerrill285.trewrite.entities.IHostile;
import kmerrill285.trewrite.items.ItemsT;
import kmerrill285.trewrite.items.modifiers.ItemModifier;
import kmerrill285.trewrite.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityZombieT extends ZombieEntity implements IHostile {
   public int money;

   public EntityZombieT(EntityType type, World worldIn) {
      super(type, worldIn);
   }

   public EntityZombieT(World worldIn) {
      super(worldIn);
   }
   
   public SoundEvent getHurtSound() {
	   return SoundsT.HIT1;
   }
   
   public SoundEvent getDeathSound() {
	   return SoundsT.KILLED2;
   }

   public SoundEvent getAmbientSound() {
	   return SoundsT.ZOMBIE1;
   }
   
   public void dropLoot(DamageSource source, boolean b) {
      if (Util.isChristmas() && this.rand.nextDouble() <= 0.0769D) {
         EntityItemT.spawnItem(this.getEntityWorld(), this.getPosition(), new ItemStackT(ItemsT.PRESENT, 1, (ItemModifier)null));
      }
            
      if (source.getImmediateSource() instanceof PlayerEntity) {
         PlayerEntity player = (PlayerEntity)source.getImmediateSource();
         if (player.getHealth() <= player.getMaxHealth() && this.rand.nextInt(12) == 0) {
            EntityHeart.spawnHeart(this.getEntityWorld(), this.getPosition());
         }
      }

      EntityCoin.spawnCoin(this.world, this.getPosition(), 0, this.money);
   }

   protected void registerAttributes() {
      super.registerAttributes();
      this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)(this.rand.nextInt(10) + 35));
      this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue((double)(this.rand.nextInt(3) + 4));
      this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(this.rand.nextDouble() * 0.1D + 0.5D);
      this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((double)(this.rand.nextInt(7) + 12));
      this.money = this.rand.nextInt(20) + 50;
   }

   protected void onDrowned() {
      this.func_213698_b(EntityType.DROWNED);
      this.world.playEvent((PlayerEntity)null, 1040, new BlockPos(this), 0);
   }
}
