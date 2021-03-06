package kmerrill285.trewrite.entities.projectiles;

import kmerrill285.trewrite.blocks.BlocksT;
import kmerrill285.trewrite.blocks.pots.Pot;
import kmerrill285.trewrite.entities.EntitiesT;
import kmerrill285.trewrite.items.ItemT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public abstract class Projectile extends MobEntity {
   public ItemT weapon;
   public int piercing;
   public double damage;
   public double knockback;
   public boolean hostile;
   public boolean breakOnGround;
   public boolean breakOnImpact;
   public boolean despawnDueToAge;
   public boolean dead;
   public Entity owner;
   public int maxAge = 100;
   public boolean hitGround = false;

   public Projectile(World worldIn, EntityType type) {
      super(type, worldIn);
   }

   public Projectile(World worldIn, double x, double y, double z) {
      super(EntitiesT.MAGIC_PROJECTILE, worldIn);
      this.setPosition(x, y, z);
   }

   public Projectile(EntityType p_i50172_1_, World p_i50172_2_) {
      super(p_i50172_1_, p_i50172_2_);
   }

   public Projectile(World world) {
      super(EntitiesT.MAGIC_PROJECTILE, world);
   }

   public abstract void init();

   public abstract void doAIStuff();

   public void setDamage(double damage) {
      this.damage = damage;
   }

   public void setKnockback(double knockback) {
      this.knockback = knockback;
   }

   public boolean isInvulnerable() {
      return true;
   }

   public void collideWithEntity(Entity e) {
   }

   protected void collideWithNearbyEntities() {
   }

   public AxisAlignedBB getCollisionBoundingBox() {
      return null;
   }

   public boolean hasNoGravity() {
      return true;
   }

   public void tick() {
      super.tick();
      this.doAIStuff();
      if (this.owner == null) {
         this.remove();
      } else if (this.owner instanceof LivingEntity && ((LivingEntity)this.owner).getHealth() <= 0.0F) {
         this.remove();
      } else if (this.dead) {
         this.remove();
      } else {
         if (this.world.getBlockState(this.getPosition()).getBlock() instanceof Pot) {
            this.world.setBlockState(this.getPosition(), BlocksT.AIR_BLOCK.getDefaultState());
         }

         float f = MathHelper.sqrt(func_213296_b(this.getMotion()));
         this.rotationYaw = (float)(MathHelper.atan2(this.getMotion().x, this.getMotion().z) * 57.2957763671875D);
         this.rotationPitch = (float)(MathHelper.atan2(this.getMotion().y, (double)f) * 57.2957763671875D);
         this.prevRotationYaw = this.rotationYaw;
         this.prevRotationPitch = this.rotationPitch;
         if (this.despawnDueToAge && this.ticksExisted > this.maxAge) {
            this.remove();
         } else {
            if (!this.world.isRemote()) {
               float rd = 1000.0F;
               Vec3d vec3d = this.getPositionVec();
               Vec3d vec3d1 = this.getMotion();
               Vec3d vec3d2 = vec3d.add(vec3d1.x, vec3d1.y, vec3d1.z);
               AxisAlignedBB bb = this.getBoundingBox().expand(vec3d1.scale((double)rd)).grow(1.0D, 1.0D, 1.0D);
               EntityRayTraceResult result = ProjectileHelper.func_221269_a(this.world, this, vec3d, vec3d2, bb, (p_215312_0_) -> {
                  return !p_215312_0_.isSpectator() && p_215312_0_.canBeCollidedWith();
               }, (double)rd);
               if (result != null) {
                  this.onImpact(result);
               }
            }

         }
      }
   }

   public boolean attackEntityFrom(DamageSource source, float amount) {
      return false;
   }

   protected void onImpact(RayTraceResult result) {
      if (result.getType() == Type.ENTITY && this.weapon != null) {
         Entity entity = ((EntityRayTraceResult)result).getEntity();
         if (entity instanceof LivingEntity && !(entity instanceof Projectile)) {
            if (!this.hostile && entity instanceof PlayerEntity) {
               return;
            }

            if (this.hostile && !(entity instanceof PlayerEntity)) {
               return;
            }

            entity.attackEntityFrom(DamageSource.GENERIC, (float)this.damage);
            ((LivingEntity)entity).knockBack(entity, (float)this.knockback, (double)((float)this.getMotion().x % 2.0F), (double)((float)this.getMotion().z % 2.0F));
            --this.piercing;
            if (this.piercing < 0) {
               if (!this.breakOnImpact) {
                  return;
               }

               this.remove();
            }
         }
      }

      if (this.breakOnGround && result.getType() == Type.BLOCK) {
         this.remove();
      }

   }

   public void createProjectile(EntityType projectile, BlockPos pos, World world, Entity shooter) {
      Projectile p = (Projectile)projectile.create(world, (CompoundNBT)null, (ITextComponent)null, (PlayerEntity)null, pos, SpawnReason.EVENT, false, false);
      p.init();
      p.owner = shooter;
      world.addEntity(p);
   }

   public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
      Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy, this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy, this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy).scale((double)velocity);
      this.setMotion(vec3d);
      float f = MathHelper.sqrt(func_213296_b(vec3d));
      this.rotationYaw = (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D);
      this.rotationPitch = (float)(MathHelper.atan2(vec3d.y, (double)f) * 57.2957763671875D);
      this.prevRotationYaw = this.rotationYaw;
      this.prevRotationPitch = this.rotationPitch;
   }

   public void shoot(Entity entityThrower, float rotationPitchIn, float rotationYawIn, float pitchOffset, float velocity, float inaccuracy) {
      float f = -MathHelper.sin(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
      float f1 = -MathHelper.sin((rotationPitchIn + pitchOffset) * 0.017453292F);
      float f2 = MathHelper.cos(rotationYawIn * 0.017453292F) * MathHelper.cos(rotationPitchIn * 0.017453292F);
      this.shoot((double)f, (double)f1, (double)f2, velocity, inaccuracy);
      Vec3d vec3d = entityThrower.getMotion();
      this.setMotion(this.getMotion().add(vec3d.x, entityThrower.onGround ? 0.0D : vec3d.y, vec3d.z));
   }
}
