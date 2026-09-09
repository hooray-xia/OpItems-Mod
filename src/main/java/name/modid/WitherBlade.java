package name.modid;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.hurtingprojectile.WitherSkull;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class WitherBlade extends Item {
    public WitherBlade(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 1));
        target.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        level.playSound(
            null,
            player.getX(),
            player.getY(),
            player.getZ(),
            SoundEvents.WITHER_SHOOT,
            SoundSource.HOSTILE,
            1.0F,
            1.0F
        );
        if (level instanceof ServerLevel serverLevel) {
            Vec3 look = player.getLookAngle();
            WitherSkull skull = new WitherSkull(serverLevel, player, look);
            skull.setPos(player.getX(), player.getEyeY(), player.getZ());
            skull.shoot(look.x, look.y, look.z, 1.0F, 1.0F);
            Projectile.spawnProjectile(skull, serverLevel, itemStack);
        }
        player.getCooldowns().addCooldown(itemStack, 40);
        player.awardStat(Stats.ITEM_USED.get(this));
        return InteractionResult.SUCCESS;
    }
}
