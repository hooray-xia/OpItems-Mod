package name.modid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EnchantedObsidianApple extends Item {

    public EnchantedObsidianApple(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);

        // Make sure potion effects only apply on the server side
        if (!level.isClientSide()) {
            // Regeneration V (level 4) for 20 seconds (400 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 8400, 3));

            // Resistance II (level 1) for 5 minutes (6000 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 9000, 3));

            // Fire Resistance I for 5 minutes (6000 ticks)
            user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 16000, 0));

            // MORE EFFECTS
            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 18099, 6));
            user.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 16000, 0));
            user.addEffect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 3400, 2));
            user.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 8000, 4));
            user.addEffect(new MobEffectInstance(MobEffects.JUMP_BOOST, 7000, 2));
        }

        return result;
    }

    // Optional: Make the item glow like an Enchanted Golden Apple
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}