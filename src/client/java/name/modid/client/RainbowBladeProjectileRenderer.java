/*package name.modid.client;

import com.mojang.blaze3d.vertex.PoseStack;
import name.modid.OpItems;
import name.modid.RainbowBladeProjectile;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class RainbowBladeProjectileRenderer
        extends EntityRenderer<RainbowBladeProjectile, RainbowBladeProjectileRenderer.State> {

    private static final ItemStack BLADE_STACK = new ItemStack(OpItems.RAINBOW_BLADE);

    private final ItemModelResolver itemModelResolver;

    public RainbowBladeProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.itemModelResolver = context.getItemModelResolver();
        this.shadowRadius = 0.15F;
    }

    @Override
    public State createRenderState() {
        return new State();
    }

    @Override
    public void extractRenderState(
            RainbowBladeProjectile entity,
            State state,
            float partialTick
    ) {
        super.extractRenderState(entity, state, partialTick);
        state.rotation = (entity.tickCount + partialTick) * 35.0F;
        state.itemStack = BLADE_STACK;

        this.itemModelResolver.updateForNonLiving(
                state.itemRenderState,
                state.itemStack,
                ItemDisplayContext.GROUND,
                entity
        );
    }

    @Override
    public void submit(
            State state,
            PoseStack poseStack,
            SubmitNodeCollector collector,
            CameraRenderState camera
    ) {
        poseStack.pushPose();
        poseStack.scale(1.5F, 1.5F, 1.5F);
        poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(state.rotation));
        poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(90.0F));

        state.itemRenderState.submit(
                poseStack,
                collector,
                state.lightCoords,
                0,
                state.outlineColor
        );

        poseStack.popPose();

        super.submit(state, poseStack, collector, camera);
    }

    public static class State extends EntityRenderState {
        public final ItemStackRenderState itemRenderState = new ItemStackRenderState();
        public ItemStack itemStack = ItemStack.EMPTY;
        public float rotation;
    }
}*/