package name.modid;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BloodTotemItem extends Item {

    public BloodTotemItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}
