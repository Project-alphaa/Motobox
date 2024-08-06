package motobox.item;

import motobox.block.SteepSlopeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.Properties;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SteepSlopeBlockItem extends BlockItem {
    private final Block base;

    public SteepSlopeBlockItem(Block base, Block block, Settings settings) {
        super(block, settings);
        this.base = base;
    }

    @Nullable
    @Override
    public ItemPlacementContext getPlacementContext(ItemPlacementContext context) {
        var hitPos = context.getHitPos();
        var pos = new BlockPos(MathHelper.floor(hitPos.x), MathHelper.floor(hitPos.y), MathHelper.floor(hitPos.z));
        var world = context.getWorld();
        if (world.getBlockState(pos).getBlock() instanceof SteepSlopeBlock) {
            var facing = world.getBlockState(pos).get(Properties.HORIZONTAL_FACING);
            var playerFacing = context.getPlayerLookDirection();
            var vOffset = playerFacing == facing ? Direction.DOWN : playerFacing == facing.getOpposite() ? Direction.UP : null;
            var place = pos.offset(playerFacing);
            if (vOffset != null) place = place.offset(vOffset);
            var pState = world.getBlockState(place);
            if (pState.isAir() || pState.isOf(Blocks.WATER)) {
                return new SlopePlacementContext(ItemPlacementContext.offset(context, place, Direction.UP), facing);
            }
        }
        return super.getPlacementContext(context);
    }

    @Override
    public String getTranslationKey() {
        return base != null ? "block.motobox.steep_slope" : super.getTranslationKey();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        if (base != null) tooltip.add(Text.translatable(base.getTranslationKey()).formatted(Formatting.BLUE));
    }
}
