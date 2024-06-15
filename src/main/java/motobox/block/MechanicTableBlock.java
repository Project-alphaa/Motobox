package motobox.block;

import motobox.item.GenericMotoboxItem;
import motobox.screen.MechanicTableScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class MechanicTableBlock extends HorizontalFacingBlock implements GenericMotoboxItem {

    private static final VoxelShape SHAPE = VoxelShapes.union(
            Block.createCuboidShape(0, 14, 0, 16, 16, 16),
            Block.createCuboidShape(0, 16, 0, 16, 17, 2),
            Block.createCuboidShape(13.5, 13, 2.5, 15.5, 14, 13.5),
            Block.createCuboidShape(2.5, 13, 13.5, 13.5, 14, 15.5),
            Block.createCuboidShape(0.5, 2, 0.5, 2.5, 11, 2.5),
            Block.createCuboidShape(1, 6, 1, 15, 7, 15),
            Block.createCuboidShape(13.5, 2, 0.5, 15.5, 11, 2.5),
            Block.createCuboidShape(0.5, 2, 13.5, 2.5, 11, 15.5),
            Block.createCuboidShape(13.5, 2, 13.5, 15.5, 11, 15.5),
            Block.createCuboidShape(13.5, 11, 13.5, 15.5, 14, 15.5),
            Block.createCuboidShape(13.5, 11, 0.5, 15.5, 14, 2.5),
            Block.createCuboidShape(0.5, 11, 13.5, 2.5, 14, 15.5),
            Block.createCuboidShape(0.5, 11, 0.5, 2.5, 14, 2.5),
            Block.createCuboidShape(13.5, 0, 1, 15.5, 2, 2),
            Block.createCuboidShape(0.5, 0, 1, 2.5, 2, 2),
            Block.createCuboidShape(0.5, 0, 14, 2.5, 2, 15),
            Block.createCuboidShape(13.5, 0, 14, 15.5, 2, 15),
            Block.createCuboidShape(0.5, 13, 2.5, 2.5, 14, 13.5),
            Block.createCuboidShape(2.5, 13, 0.5, 13.5, 14, 2.5)
    );
    public static final Text UI_TITLE = Text.translatable("container.motobox.mechanic_table");

    public MechanicTableBlock(Settings settings) {
        super(settings);
        setDefaultState(stateManager.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext ctx) {
        Direction direction = state.get(FACING);
        return switch (direction) {
            case NORTH -> rotateShape(2);
            case SOUTH -> rotateShape(0);
            case EAST -> rotateShape(3);
            case WEST -> rotateShape(1);
            default -> VoxelShapes.fullCube();
        };
    }

    private static VoxelShape rotateShape(int rotationNumber) {
        VoxelShape[] shapes = new VoxelShape[]{SHAPE, VoxelShapes.empty()};

        for (int i = 0; i < rotationNumber; i++) {
            shapes[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> shapes[1] = VoxelShapes.union(shapes[1], VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            shapes[0] = shapes[1];
            shapes[1] = VoxelShapes.empty();
        }

        return shapes[0];
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient()) {
            return ActionResult.SUCCESS;
        }
        else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            return ActionResult.CONSUME;
        }
    }

    @Override
    public @Nullable NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) ->
                new MechanicTableScreenHandler(syncId, playerInventory, ScreenHandlerContext.create(world, pos)), UI_TITLE);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(FACING, ctx.getPlayerLookDirection().getOpposite());
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
