package name.modid.mixin;

import name.modid.OpItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class BloodTotemMixin {

    @Inject(method = "checkTotemDeathProtection", at = @At("HEAD"), cancellable = true)
    private void injectBloodTotemCheck(DamageSource killingDamage, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (!(self instanceof Player player)) return;

        ItemStack totemStack = null;
        InteractionHand usedHand = null;

        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.is(OpItems.BLOOD_TOTEM)) {
                totemStack = stack;
                usedHand = hand;
                break;
            }
        }

        if (totemStack == null) return;

        cir.setReturnValue(true);

        totemStack.shrink(1);

        player.setHealth(1.0F);

        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2400, 4));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 46000, 8));
        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 6000, 4));
        player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 6000, 4));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000, 0));
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 12000, 0));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 12000, 2));
        player.addEffect(new MobEffectInstance(MobEffects.SPEED, 6000, 2));
        player.addEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 2));
        player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 3600, 9));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 12000, 0));

        Level level = player.level();

        if (level instanceof ServerLevel serverLevel) {
            BlockPos playerPos = player.blockPosition();
            for (int i = 0; i < 10; i++) {
                BlockPos spawnPos = playerPos.offset(
                        level.getRandom().nextInt(5) - 2, 0, level.getRandom().nextInt(5) - 2);
                EntityTypes.IRON_GOLEM.spawn(serverLevel, spawnPos, EntitySpawnReason.MOB_SUMMONED);
            }
            for (int i = 0; i < 5; i++) {
                BlockPos spawnPos = playerPos.offset(
                        level.getRandom().nextInt(5) - 2, 0, level.getRandom().nextInt(5) - 2);
                EntityTypes.CAT.spawn(serverLevel, spawnPos, EntitySpawnReason.MOB_SUMMONED);
            }
            for (int i = 0; i < 5; i++) {
                BlockPos spawnPos = playerPos.offset(
                        level.getRandom().nextInt(5) - 2, 0, level.getRandom().nextInt(5) - 2);
                EntityTypes.SNOW_GOLEM.spawn(serverLevel, spawnPos, EntitySpawnReason.MOB_SUMMONED);
            }
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 0.5F);

        spawnBloodParticles(level, player);
    }

    @Inject(method = "kill", at = @At("HEAD"), cancellable = true)
    private void onKill(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;

        if (!(self instanceof Player player)) return;

        ItemStack totemStack = null;
        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.is(OpItems.BLOOD_TOTEM)) {
                totemStack = stack;
                break;
            }
        }

        if (totemStack == null) return;

        ci.cancel();

        totemStack.shrink(1);

        player.setHealth(1.0F);

        player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 2400, 4));
        player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 46000, 8));
        player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 6000, 4));
        player.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 6000, 4));
        player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 12000, 0));
        player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 12000, 0));
        player.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 12000, 2));
        player.addEffect(new MobEffectInstance(MobEffects.SPEED, 6000, 2));
        player.addEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1, 2));
        player.addEffect(new MobEffectInstance(MobEffects.SATURATION, 3600, 9));
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 12000, 0));

        Level level = player.level();

        if (level instanceof ServerLevel serverLevel) {
            BlockPos playerPos = player.blockPosition();
            for (int i = 0; i < 10; i++) {
                BlockPos spawnPos = playerPos.offset(
                        level.getRandom().nextInt(5) - 2, 0, level.getRandom().nextInt(5) - 2);
                EntityTypes.IRON_GOLEM.spawn(serverLevel, spawnPos, EntitySpawnReason.MOB_SUMMONED);
            }
            for (int i = 0; i < 5; i++) {
                BlockPos spawnPos = playerPos.offset(
                        level.getRandom().nextInt(5) - 2, 0, level.getRandom().nextInt(5) - 2);
                EntityTypes.CAT.spawn(serverLevel, spawnPos, EntitySpawnReason.MOB_SUMMONED);
            }
            for (int i = 0; i < 5; i++) {
                BlockPos spawnPos = playerPos.offset(
                        level.getRandom().nextInt(5) - 2, 0, level.getRandom().nextInt(5) - 2);
                EntityTypes.SNOW_GOLEM.spawn(serverLevel, spawnPos, EntitySpawnReason.MOB_SUMMONED);
            }
        }

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0F, 0.5F);

        spawnBloodParticles(level, player);
    }

    private void spawnBloodParticles(Level level, Player player) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        Vec3 pos = player.position();
        for (int i = 0; i < 60; i++) {
            double offsetX = (level.getRandom().nextDouble() - 0.5) * 2.0;
            double offsetY = level.getRandom().nextDouble() * 2.5;
            double offsetZ = (level.getRandom().nextDouble() - 0.5) * 2.0;
            serverLevel.sendParticles(
                    ParticleTypes.DAMAGE_INDICATOR,
                    pos.x + offsetX,
                    pos.y + offsetY,
                    pos.z + offsetZ,
                    1, 0, 0, 0, 0
            );
        }
        for (int i = 0; i < 30; i++) {
            double offsetX = (level.getRandom().nextDouble() - 0.5) * 2.0;
            double offsetY = level.getRandom().nextDouble() * 2.5;
            double offsetZ = (level.getRandom().nextDouble() - 0.5) * 2.0;
            serverLevel.sendParticles(
                    ParticleTypes.HEART,
                    pos.x + offsetX,
                    pos.y + offsetY,
                    pos.z + offsetZ,
                    1, 0, 0, 0, 0
            );
        }
    }
}
