package name.modid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnchantedDiamondApple extends Item {

    public EnchantedDiamondApple(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);

        // Make sure potion effects only apply on the server side
        if (!level.isClientSide()) {
            // Regeneration V (level 4) for 20 seconds (400 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5400, 3));

            // Resistance II (level 1) for 5 minutes (6000 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 6000, 2));

            // Fire Resistance I for 5 minutes (6000 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0));

            // MORE EFFECTS
            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 12400, 5));
            user.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 6000, 0));
            user.addEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 1400, 2));
            user.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 5000, 2));
            user.addEffect(new MobEffectInstance(MobEffects.SATURATION, 7000, 3));
            user.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 2000, 1));
            user.addEffect(new MobEffectInstance(MobEffects.HASTE, 2000, 1));
        }

        return result;
    }

    // Optional: Make the item glow like an Enchanted Golden Apple
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}