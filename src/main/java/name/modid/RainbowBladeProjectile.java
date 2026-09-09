package name.modid;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RainbowBladeProjectile extends Projectile implements ItemSupplier {

    private static final float DAMAGE = 20.0F;
    private static final int MAX_LIFE = 200;

    public RainbowBladeProjectile(EntityType<? extends RainbowBladeProjectile> type, Level level) {
        super(type, level);
    }

    public RainbowBladeProjectile(Level level, LivingEntity owner) {
        this(ModEntities.RAINBOW_BLADE_PROJECTILE, level);
        setOwner(owner);
        Vec3 pos = owner.getEyePosition(1.0F);
        setPos(pos.x, pos.y - 0.2D, pos.z);
        setXRot(owner.getXRot());
        setYRot(owner.getYRot());
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        // No synced data needed.
    }

    @Override
    public ItemStack getItem() {
        return new ItemStack(OpItems.RAINBOW_BLADE);
    }

    @Override
    public void tick() {
        super.tick();

        if (tickCount > MAX_LIFE) {
            discard();
            return;
        }

        if (!level().isClientSide()) {
            Vec3 end = position().add(getDeltaMovement());
            HitResult hit = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);

            if (hit.getType() != HitResult.Type.MISS) {
                onHit(hit);
            }

            setPos(end);
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (level().isClientSide()) {
            return;
        }

        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity living && entity != getOwner()) {
            living.hurtServer(
                    (ServerLevel) level(),
                    damageSources().thrown(this, getOwner()),
                    DAMAGE
            );
        }

        discard();
    }

    @Override
    protected void onHitBlock(net.minecraft.world.phys.BlockHitResult result) {
        discard();
    }

    @Override
    public boolean isPickable() {
        return false;
    }
}
