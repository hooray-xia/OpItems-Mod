package name.modid;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DragonBlade extends Item {
	public DragonBlade(Item.Properties properties) {
		super(properties);
	}

	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack itemStack = player.getItemInHand(hand);
		level.playSound(
			null,
			player.getX(),
			player.getY(),
			player.getZ(),
			SoundEvents.ENDER_DRAGON_SHOOT,
			SoundSource.HOSTILE,
			1.0F,
			1.0F
		);
		if (level instanceof ServerLevel serverLevel) {
			Vec3 look = player.getLookAngle();
			DragonFireball fireball = new DragonFireball(serverLevel, player, look);
			fireball.setPos(player.getX(), player.getEyeY(), player.getZ());
			fireball.shoot(look.x, look.y, look.z, 1.5F, 1.0F);
			Projectile.spawnProjectile(fireball, serverLevel, itemStack);
		}
		player.getCooldowns().addCooldown(itemStack, 20);
		player.awardStat(Stats.ITEM_USED.get(this));
		return InteractionResult.SUCCESS;
	}

	@Override
public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
    if (entity instanceof LivingEntity livingEntity) {
        // Checks if held in main hand or off-hand
        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) {
            if (livingEntity.tickCount % 20 == 0) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0, false, false));
            }
        }
    }
    super.inventoryTick(stack, level, entity, slot);
}
}
