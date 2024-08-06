package motobox.block;

import motobox.item.CourseElementItem;
import motobox.item.SlopePlacementContext;
import motobox.util.AUtils;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class SteepSlopeBlock extends HorizontalFacingBlock implements Waterloggable, CourseElementItem {
    public static final VoxelShape NORTH_SHAPE;
    public static final VoxelShape SOUTH_SHAPE;
    public static final VoxelShape EAST_SHAPE;
    public static final VoxelShape WEST_SHAPE;

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public SteepSlopeBlock(Settings settings) {
        super(settings.nonOpaque());
        setDefaultState(getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return ctx instanceof SlopePlacementContext slopeCtx ?
                super.getPlacementState(ctx)
                        .with(FACING, slopeCtx.getSlopeFacing())
                        .with(WATERLOGGED, ctx.getWorld().getBlockState(ctx.getBlockPos()).isOf(Blocks.WATER))
                :
                super.getPlacementState(ctx)
                        .with(FACING, ctx.getPlayerLookDirection().getOpposite())
                        .with(WATERLOGGED, ctx.getWorld().getBlockState(ctx.getBlockPos()).isOf(Blocks.WATER));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case WEST -> WEST_SHAPE;
            case EAST -> EAST_SHAPE;
            default -> VoxelShapes.empty();
        };
    }

    static {
        var shapes = new ArrayList<VoxelShape>();
        for (var dir : AUtils.HORIZONTAL_DIRS) {
            double ox = switch (dir) {
                case WEST -> 0.5;
                case EAST -> -0.5;
                default -> 0;
            };
            double oz = switch (dir) {
                case NORTH -> 0.5;
                case SOUTH -> -0.5;
                default -> 0;
            };
            var shape = switch (dir) {
                case NORTH -> Block.createCuboidShape(0, -1.5, 0, 16, 0.5, 0.5);
                case SOUTH -> Block.createCuboidShape(0, -1.5, 15.5, 16, 0.5, 16);
                case EAST -> Block.createCuboidShape(15.5, -1.5, 0, 16, 0.5, 16);
                case WEST -> Block.createCuboidShape(0, -1.5, 0, 0.5, 0.5, 16);
                default -> VoxelShapes.empty();
            };
            var finalShape = shape;
            for (int i = 1; i < 32; i++) {
                finalShape = VoxelShapes.union(finalShape, shape.offset((ox * i) / 16, (0.5 * i) / 16, (oz * i) / 16));
            }
            shapes.add(finalShape);
        }
        NORTH_SHAPE = shapes.get(0);
        SOUTH_SHAPE = shapes.get(1);
        EAST_SHAPE = shapes.get(2);
        WEST_SHAPE = shapes.get(3);
    }
}
