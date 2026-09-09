package name.modid;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.ToolMaterial;

public class RainbowShovel extends ShovelItem {

    public RainbowShovel(
        ToolMaterial toolMaterial,
        float attackDamage,
        float attackSpeed,
        Item.Properties properties
    ) {
        super(toolMaterial, attackDamage, attackSpeed, properties);
    }
}