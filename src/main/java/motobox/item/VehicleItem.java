package motobox.item;

import motobox.entity.MotoboxEntities;
import motobox.entity.VehicleEntity;
import motobox.vehicle.VehicleData;
import motobox.vehicle.VehiclePrefab;
import motobox.vehicle.VehicleStats;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleItem extends Item {
    public static final List<VehiclePrefab> PREFABS = new ArrayList<>();
    private static final VehicleData data = new VehicleData();
    private static final VehicleStats stats = new VehicleStats();

    public VehicleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            var stack = context.getStack();
            data.read(stack.getOrCreateSubNbt("Vehicle"));
            var e = new VehicleEntity(MotoboxEntities.VEHICLE_ENTITY, context.getWorld());
            var pos = context.getHitPos();
            e.refreshPositionAndAngles(pos.x, pos.y, pos.z, context.getSide().getOpposite().asRotation(), 0);
            e.setComponents(data.getFrame(), data.getWheel(), data.getEngine());
            context.getWorld().spawnEntity(e);
            stack.decrement(1);
            return ActionResult.PASS;
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        data.read(stack.getOrCreateSubNbt("Vehicle"));
        if (Screen.hasShiftDown()) {
            stats.from(data.getFrame(), data.getWheel(), data.getEngine());
            stats.appendTexts(tooltip, stats);
        } else {
            if (!data.isPrefab()) {
                tooltip.add(
                        Text.translatable("tooltip.motobox.frameLabel").formatted(Formatting.BLUE)
                                .append(Text.translatable(data.getFrame().getTranslationKey()).formatted(Formatting.DARK_GREEN))
                );
                tooltip.add(
                        Text.translatable("tooltip.motobox.wheelLabel").formatted(Formatting.BLUE)
                                .append(Text.translatable(data.getWheel().getTranslationKey()).formatted(Formatting.DARK_GREEN))
                );
                tooltip.add(
                        Text.translatable("tooltip.motobox.engineLabel").formatted(Formatting.BLUE)
                                .append(Text.translatable(data.getEngine().getTranslationKey()).formatted(Formatting.DARK_GREEN))
                );
            }
            tooltip.add(Text.translatable("tooltip.motobox.shiftForStats").formatted(Formatting.GOLD));
        }
    }

    public static void addPrefabs(VehiclePrefab... prefabs) {
        PREFABS.addAll(Arrays.asList(prefabs));
    }
}
