package motobox.block;

import motobox.item.CourseElementItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class TrafficConeBlock extends Block implements CourseElementItem {

    protected static final VoxelShape SHAPE = VoxelShapes.union(
            // Main
            Block.createCuboidShape(2, 1, 2, 14, 3, 14),
            Block.createCuboidShape(4, 3, 4, 12, 6, 12),
            Block.createCuboidShape(5, 6, 5, 11, 10, 11),
            Block.createCuboidShape(6, 10, 6, 10, 14, 10),
            Block.createCuboidShape(7, 10.001, 7, 9, 14, 9),
            // Feet
            Block.createCuboidShape(7.5, 0, 2.5, 8.5, 1, 5.5),
            Block.createCuboidShape(2.5, 0, 7.5, 5.5, 1, 8.5),
            Block.createCuboidShape(10.5, 0, 7.5, 13.5, 1, 8.5),
            Block.createCuboidShape(7.5, 0, 10.5, 8.5, 1, 13.5),
            // Corner Feet
            Block.createCuboidShape(7.5, 0, 12, 8.5, 1, 15),
            Block.createCuboidShape(1, 0, 7.5, 4, 1, 8.5),
            Block.createCuboidShape(7.5, 0, 1, 8.5, 1, 4),
            Block.createCuboidShape(7.5, 0, 1, 8.5, 1, 4)
    );

    public TrafficConeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
}
