package name.modid;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public class EchoPrism extends Block {

    public static final IntegerProperty ECHO_LEVEL =
            IntegerProperty.create("echo_level", 0, 3);

    public EchoPrism(Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(ECHO_LEVEL, 0)
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(ECHO_LEVEL);
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hit
    ) {

        int currentLevel = state.getValue(ECHO_LEVEL);
        int nextLevel = (currentLevel + 1) % 4;

        level.setBlock(
                pos,
                state.setValue(ECHO_LEVEL, nextLevel),
                Block.UPDATE_ALL
        );

        float pitch = 0.7f + (nextLevel * 0.25f);

        level.playSound(
                player,
                pos,
                SoundEvents.AMETHYST_BLOCK_RESONATE,
                SoundSource.BLOCKS,
                1.0f,
                pitch
        );

        level.playSound(
                player,
                pos,
                SoundEvents.SCULK_SHRIEKER_SHRIEK,
                SoundSource.BLOCKS,
                0.6f,
                pitch
        );

        if (level instanceof ServerLevel serverLevel) {
            spawnEchoParticles(serverLevel, pos, nextLevel);
            spawnEchoRing(serverLevel, pos, nextLevel);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void animateTick(
            BlockState state,
            Level level,
            BlockPos pos,
            RandomSource random
    ) {

        int echoLevel = state.getValue(ECHO_LEVEL);

        if (echoLevel == 0) {
            return;
        }

        int particleCount = 1 + echoLevel;

        for (int i = 0; i < particleCount; i++) {

            double x = pos.getX() + 0.5
                    + (random.nextDouble() - 0.5) * 0.8;

            double y = pos.getY() + random.nextDouble();

            double z = pos.getZ() + 0.5
                    + (random.nextDouble() - 0.5) * 0.8;

            double vx = (random.nextDouble() - 0.5) * 0.03;
            double vy = 0.01 + random.nextDouble() * 0.03;
            double vz = (random.nextDouble() - 0.5) * 0.03;

            level.addParticle(
                    new DustColorTransitionOptions(
                            0x6633E6,
                            0x33CCFF,
                            0.7f + (echoLevel * 0.15f)
                    ),
                    x,
                    y,
                    z,
                    vx,
                    vy,
                    vz
            );
        }

        if (random.nextInt(3) == 0) {

            double x = pos.getX() + 0.5
                    + (random.nextDouble() - 0.5) * 0.6;

            double y = pos.getY() + 0.5
                    + (random.nextDouble() - 0.5) * 0.6;

            double z = pos.getZ() + 0.5
                    + (random.nextDouble() - 0.5) * 0.6;

            level.addParticle(
                    ParticleTypes.END_ROD,
                    x,
                    y,
                    z,
                    0.0,
                    0.03,
                    0.0
            );
        }
    }

    private void spawnEchoParticles(
            ServerLevel level,
            BlockPos pos,
            int echoLevel
    ) {

        RandomSource random = level.getRandom();

        int count = 25 + (echoLevel * 20);

        for (int i = 0; i < count; i++) {

            double x = pos.getX() + 0.5
                    + (random.nextDouble() - 0.5) * 0.7;

            double y = pos.getY() + 0.5
                    + (random.nextDouble() - 0.5) * 0.7;

            double z = pos.getZ() + 0.5
                    + (random.nextDouble() - 0.5) * 0.7;

            double vx = (random.nextDouble() - 0.5) * 0.25;
            double vy = random.nextDouble() * 0.18;
            double vz = (random.nextDouble() - 0.5) * 0.25;

            level.sendParticles(
                    new DustColorTransitionOptions(
                            0x801AFF,
                            0x1ACCFF,
                            0.8f + (echoLevel * 0.25f)
                    ),
                    x,
                    y,
                    z,
                    1,
                    vx,
                    vy,
                    vz,
                    0.12
            );
        }

        level.sendParticles(
                ParticleTypes.END_ROD,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                10 + echoLevel * 8,
                0.5,
                0.5,
                0.5,
                0.08
        );
    }

    private void spawnEchoRing(
            ServerLevel level,
            BlockPos pos,
            int echoLevel
    ) {

        double centerX = pos.getX() + 0.5;
        double centerY = pos.getY() + 0.5;
        double centerZ = pos.getZ() + 0.5;

        int points = 24 + echoLevel * 12;
        double radius = 0.7 + echoLevel * 0.25;

        for (int i = 0; i < points; i++) {

            double angle = (Math.PI * 2 * i) / points;

            double x = centerX + Math.cos(angle) * radius;
            double z = centerZ + Math.sin(angle) * radius;

            level.sendParticles(
                    new DustColorTransitionOptions(
                            0xAA00FF,
                            0x00CCFF,
                            1.0f
                    ),
                    x,
                    centerY + 0.1,
                    z,
                    1,
                    0.0,
                    0.03,
                    0.0,
                    0.02
            );
        }
    }
}