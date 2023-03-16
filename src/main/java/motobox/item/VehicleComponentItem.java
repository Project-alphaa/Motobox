package motobox.item;

import motobox.util.SimpleMapContentRegistry;
import motobox.vehicle.VehicleComponent;
import motobox.vehicle.VehicleWheel;
import motobox.vehicle.attachment.RearAttachmentType;
import motobox.vehicle.attachment.rear.TrailerRearAttachment;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.model.Model;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.function.ToFloatFunction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class VehicleComponentItem<T extends VehicleComponent<T>> extends Item {
    protected final String nbtKey;
    protected final String translationKey;
    protected final SimpleMapContentRegistry<T> registry;

    public VehicleComponentItem(Settings settings, String nbtKey, String translationKey, SimpleMapContentRegistry<T> registry) {
        super(settings);
        this.nbtKey = nbtKey;
        this.translationKey = translationKey;
        this.registry = registry;
    }

    public ItemStack createStack(T component) {
        if (component.isEmpty()) {
            return ItemStack.EMPTY;
        }

        var stack = new ItemStack(this);
        this.setComponent(stack, component.getId());
        return stack;
    }

    public void setComponent(ItemStack stack, Identifier component) {
        stack.getOrCreateNbt().putString(this.nbtKey, component.toString());
    }

    public T getComponent(ItemStack stack) {
        if (stack.hasNbt() && Objects.requireNonNull(stack.getNbt()).contains(this.nbtKey)) {
            return this.registry.getOrDefault(Identifier.tryParse(stack.getNbt().getString(this.nbtKey)));
        }
        return this.registry.getOrDefault(null);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        var component = this.getComponent(stack);
        var id = component.getId();
        var compKey = id.getNamespace()+"."+id.getPath();
        tooltip.add(Text.translatable(this.translationKey+"."+compKey).formatted(Formatting.BLUE));

        component.appendTexts(tooltip, component);
    }

    public void appendStacks(ItemGroup.Entries entries) {
        this.registry.forEach(component -> {
            if (component.isEnabled(registry.getEnabledFeatures())) {
                if (addToCreative(component)) entries.add(this.createStack(component));
            }
        });
    }

    @Environment(EnvType.CLIENT)
    protected boolean renders(T component) {
        return !component.isEmpty();
    }

    protected boolean addToCreative(T component) {
        return !component.isEmpty();
    }

    @Environment(EnvType.CLIENT)
    public void registerItemRenderer(Function<T, Model> modelProvider, Function<T, Identifier> textureProvider, ToFloatFunction<T> scaleProvider) {
        BuiltinItemRendererRegistry.INSTANCE.register(this, (stack, mode, matrices, vertexConsumers, light, overlay) -> {
            var component = this.getComponent(stack);
            if (this.renders(component)) {
                var model = modelProvider.apply(component);
                float scale = scaleProvider.apply(component);
                matrices.push();
                matrices.translate(0.5, 0, 0.5);
                matrices.scale(scale, -scale, -scale);

                if (component instanceof VehicleWheel wheel && wheel == VehicleWheel.STEEL_RIM) {
                    matrices.translate(0.01f, -0.32f, 0);
                    matrices.scale(0.2f, 0.2f, 0.2f);
                }

                if (component instanceof RearAttachmentType<?> attachment && attachment == RearAttachmentType.TRAILER) {
                    matrices.translate(0, -0.5F, -2);
                }

                model.render(matrices, vertexConsumers.getBuffer(model.getLayer(textureProvider.apply(component))), light, overlay, 1, 1, 1, 1);
                matrices.pop();
            }
        });
    }
}
