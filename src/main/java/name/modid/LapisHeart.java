package name.modid;

import net.minecraft.resources.Identifier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class LapisHeart extends Item {

    public static final String MODIFIER_PREFIX = "lapis_heart_";

    public LapisHeart(Properties properties) {
        super(properties);
    }

    public static int getHeartCount(Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth == null) return 0;

        int count = 0;
        while (maxHealth.getModifier(
                Identifier.fromNamespaceAndPath("opitems", MODIFIER_PREFIX + count)
        ) != null) {
            count++;
        }
        return count;
    }

    public static boolean hasHeartBonus(Player player) {
        return getHeartCount(player) > 0;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        AttributeInstance maxHealth = player.getAttribute(Attributes.MAX_HEALTH);

        if (maxHealth == null) {
            return InteractionResult.FAIL;
        }

        int count = getHeartCount(player);
        Identifier modifierId = Identifier.fromNamespaceAndPath(
                "opitems", MODIFIER_PREFIX + count
        );

        maxHealth.addPermanentModifier(
                new AttributeModifier(
                        modifierId,
                        2.0,
                        AttributeModifier.Operation.ADD_VALUE
                )
        );

        player.setHealth(
                Math.min(player.getHealth() + 2.0F, player.getMaxHealth())
        );

        if (!player.getAbilities().instabuild) {
            player.getItemInHand(hand).shrink(1);
        }

        return InteractionResult.SUCCESS;
    }
}
