package name.modid;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.HoeItem;
public class RainbowHoe extends HoeItem {
    public RainbowHoe(ToolMaterial material, float damage, float speed, Item.Properties properties) {
        super(material, damage, speed, properties);
    }
}