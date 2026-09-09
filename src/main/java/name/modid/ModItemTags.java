package name.modid;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    public static final TagKey<Item> REPAIRS_RAINBOW_TOOL = TagKey.create(
        Registries.ITEM,
        Identifier.fromNamespaceAndPath(OpItems.MOD_ID, "repairs_rainbow_tool")
    );
}