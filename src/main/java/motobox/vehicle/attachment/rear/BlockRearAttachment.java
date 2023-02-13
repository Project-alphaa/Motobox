package motobox.vehicle.attachment.rear;

import motobox.block.MechanicTableBlock;
import motobox.block.MotoboxBlocks;
import motobox.entity.VehicleEntity;
import motobox.screen.MechanicTableScreenHandler;
import motobox.vehicle.attachment.RearAttachmentType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class BlockRearAttachment extends RearAttachment {
    public static final Text TITLE_CRAFTING = Text.translatable("container.crafting");
    public static final Text TITLE_LOOM = Text.translatable("container.loom");
    public static final Text TITLE_CARTOGRAPHY = Text.translatable("container.cartography_table");
    public static final Text TITLE_SMITHING = Text.translatable("container.upgrade");
    public static final Text TITLE_GRINDSTONE = Text.translatable("container.grindstone_title");
    public static final Text TITLE_STONECUTTER = Text.translatable("container.stonecutter");

    public final BlockState block;
    private final @Nullable BiFunction<ScreenHandlerContext, BlockRearAttachment, NamedScreenHandlerFactory> screenProvider;

    public BlockRearAttachment(RearAttachmentType<?> type, VehicleEntity entity, BlockState block, @Nullable BiFunction<ScreenHandlerContext, BlockRearAttachment, NamedScreenHandlerFactory> screenProvider) {
        super(type, entity);
        this.block = block;
        this.screenProvider = screenProvider;
    }

    @Override
    public boolean hasMenu() {
        return this.screenProvider != null;
    }

    @Override
    public @Nullable NamedScreenHandlerFactory createMenu(ScreenHandlerContext ctx) {
        return this.screenProvider != null ? this.screenProvider.apply(ctx, this) : null;
    }

    public static BlockRearAttachment craftingTable(RearAttachmentType<?> type, VehicleEntity entity) {
        return new BlockRearAttachment(type, entity,
                Blocks.CRAFTING_TABLE.getDefaultState(),
                (ctx, att) -> new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                        new CraftingScreenHandler(syncId, inventory, ctx), TITLE_CRAFTING)
        );
    }

    public static BlockRearAttachment loom(RearAttachmentType<?> type, VehicleEntity entity) {
        return new BlockRearAttachment(type, entity,
                Blocks.LOOM.getDefaultState(),
                (ctx, att) -> new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                        new LoomScreenHandler(syncId, inventory, ctx), TITLE_LOOM)
        );
    }

    public static BlockRearAttachment cartographyTable(RearAttachmentType<?> type, VehicleEntity entity) {
        return new BlockRearAttachment(type, entity,
                Blocks.CARTOGRAPHY_TABLE.getDefaultState(),
                (ctx, att) -> new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                        new CartographyTableScreenHandler(syncId, inventory, ctx), TITLE_CARTOGRAPHY)
        );
    }

    public static BlockRearAttachment smithingTable(RearAttachmentType<?> type, VehicleEntity entity) {
        return new BlockRearAttachment(type, entity,
                Blocks.SMITHING_TABLE.getDefaultState(),
                (ctx, att) -> new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                        new SmithingScreenHandler(syncId, inventory, ctx), TITLE_SMITHING)
        );
    }

    public static BlockRearAttachment grindstone(RearAttachmentType<?> type, VehicleEntity entity) {
        return new BlockRearAttachment(type, entity,
                Blocks.GRINDSTONE.getDefaultState(),
                (ctx, att) -> new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                        new GrindstoneScreenHandler(syncId, inventory, ctx), TITLE_GRINDSTONE)
        );
    }

    public static BlockRearAttachment stonecutter(RearAttachmentType<?> type, VehicleEntity entity) {
        return new BlockRearAttachment(type, entity,
                Blocks.STONECUTTER.getDefaultState(),
                (ctx, att) -> new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                        new StonecutterScreenHandler(syncId, inventory, ctx), TITLE_STONECUTTER)
        );
    }

    public static RearAttachment trailer(RearAttachmentType<?> type, VehicleEntity entity) {
        return new RearAttachment(type, entity) {
            @Override
            public boolean hasMenu() {
                return true;
            }

            @Override
            public @Nullable NamedScreenHandlerFactory createMenu(ScreenHandlerContext ctx) {
                return super.createMenu(ctx);
            }
        };
    }

    public static BlockRearAttachment mechanicTable(RearAttachmentType<?> type, VehicleEntity entity) {
        return new BlockRearAttachment(type, entity,
                MotoboxBlocks.MECHANIC_TABLE.getDefaultState(),
                (ctx, att) -> new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                        new MechanicTableScreenHandler(syncId, inventory, ctx), MechanicTableBlock.UI_TITLE)
        );
    }
}
