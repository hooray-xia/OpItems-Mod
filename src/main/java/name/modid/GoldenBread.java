package name.modid;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class GoldenBread extends Item {

    public GoldenBread(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {
        ItemStack result = super.finishUsingItem(stack, level, user);

        if (!level.isClientSide()) {
            user.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 600, 1));
            user.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 1));
            user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0));
            user.addEffect(new MobEffectInstance(MobEffects.STRENGTH, 1200, 0));
        }

        return result;
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return false;
    }
}