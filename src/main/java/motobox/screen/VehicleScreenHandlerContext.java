package motobox.screen;

import motobox.entity.VehicleEntity;
import motobox.vehicle.attachment.rear.BlockRearAttachment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.function.BiFunction;

public class VehicleScreenHandlerContext implements ScreenHandlerContext {
    private final World world;
    private final VehicleEntity vehicle;

    public VehicleScreenHandlerContext(VehicleEntity vehicle) {
        this.world = vehicle.getWorld();
        this.vehicle = vehicle;
    }

    @Override
    public <T> Optional<T> get(BiFunction<World, BlockPos, T> getter) {
        return Optional.of(getter.apply(world, vehicle.getBlockPos()));
    }

    public BlockState getAttachmentBlockState() {
        return vehicle.getRearAttachment() instanceof BlockRearAttachment att ? att.block : Blocks.AIR.getDefaultState();
    }
}
