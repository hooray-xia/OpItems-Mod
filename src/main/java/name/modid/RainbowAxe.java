package name.modid;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ToolMaterial;

public class RainbowAxe extends AxeItem {

    public RainbowAxe(
        ToolMaterial toolMaterial,
        float attackDamage,
        float attackSpeed,
        Item.Properties properties
    ) {
        super(toolMaterial, attackDamage, attackSpeed, properties);
    }
}