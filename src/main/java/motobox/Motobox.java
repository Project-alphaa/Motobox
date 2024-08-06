package motobox;

import motobox.block.MotoboxBlocks;
import motobox.entity.MotoboxEntities;
import motobox.particle.MotoboxParticles;
import motobox.recipe.MechanicTableRecipe;
import motobox.recipe.MechanicTableRecipeSerializer;
import motobox.resource.MotoboxData;
import motobox.screen.MechanicTableScreenHandler;
import motobox.screen.SingleSlotScreenHandler;
import motobox.sound.MotoboxSounds;
import motobox.util.AUtils;
import motobox.util.midnightcontrols.ControllerUtils;
import motobox.util.network.PayloadPackets;
import motobox.item.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

// TODO fix player not rotating when vehicle rotating
public class Motobox implements ModInitializer {
    public static final String MOD_ID = "motobox";

    public static final ItemGroup GROUP = FabricItemGroup.builder().icon(AUtils::createGroupIcon).entries((enabledFeatures, entries) -> {
        for (Item item : Registries.ITEM) {
            if (item instanceof GenericMotoboxItem) {
                entries.add(item);
            }
            if (item instanceof BlockItem blockItem && blockItem.getBlock() instanceof GenericMotoboxItem) {
                entries.add(item);
            }
            if (item instanceof VehicleComponentItem<?> vehicleComponentItem) {
                vehicleComponentItem.appendStacks(entries);
            }
        }
    }).displayName(Text.translatable("itemGroup.motobox.motobox")).build();
    public static final ItemGroup COURSE_ELEMENTS = FabricItemGroup.builder().icon(AUtils::createCourseElementsIcon).entries((enabledFeatures, entries) -> {
        for (Item item : Registries.ITEM) {
            if (item instanceof CourseElementItem) {
                entries.add(item);
            }
            if (item instanceof BlockItem blockItem) {
                if (blockItem.getBlock() instanceof CourseElementItem) {
                    entries.add(item);
                }
            }
        }
    }).displayName(Text.translatable("itemGroup.motobox.motobox_course_elements")).build();
    public static final ItemGroup PREFABS = FabricItemGroup.builder().icon(AUtils::createPrefabsIcon).entries((enabledFeatures, entries) -> {
        for (var prefab : VehicleItem.PREFABS) {
            entries.add(prefab.toStack());
        }
    }).displayName(Text.translatable("itemGroup.motobox.motobox_prefabs")).build();

    public static final TagKey<Block> SLOPES = TagKey.of(Registries.BLOCK.getKey(), id("slopes"));
    public static final TagKey<Block> STEEP_SLOPES = TagKey.of(Registries.BLOCK.getKey(), id("steep_slopes"));
    public static final TagKey<Block> NON_STEEP_SLOPES = TagKey.of(Registries.BLOCK.getKey(), id("non_steep_slopes"));
    public static final TagKey<Block> STICKY_SLOPES = TagKey.of(Registries.BLOCK.getKey(), id("sticky_slopes"));

    public static final ScreenHandlerType<MechanicTableScreenHandler> MECHANIC_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, Motobox.id("mechanic_table"), new ScreenHandlerType<>(MechanicTableScreenHandler::new, FeatureSet.empty()));
    public static final ScreenHandlerType<SingleSlotScreenHandler> SINGLE_SLOT_SCREEN =
            Registry.register(Registries.SCREEN_HANDLER, Motobox.id("single_slot"), new ScreenHandlerType<>(SingleSlotScreenHandler::new, FeatureSet.empty()));
    private static MinecraftServer server;

    public static MinecraftServer server() {
        return server;
    }

    @Override
    public void onInitialize() {
        MotoboxBlocks.init();
        MotoboxItems.init();
        MotoboxEntities.init();
        MotoboxParticles.init();
        MotoboxSounds.init();
        initOther();

        PayloadPackets.init();
        MotoboxData.setup();
        ControllerUtils.initMidnightControlsHandler();

        // Register Item Group
        Registry.register(Registries.ITEM_GROUP, Motobox.id("motobox"), GROUP);
        Registry.register(Registries.ITEM_GROUP, Motobox.id("course_elements"), COURSE_ELEMENTS);
        Registry.register(Registries.ITEM_GROUP, Motobox.id("prefabs"), PREFABS);

        ServerLifecycleEvents.SERVER_STARTING.register(server -> Motobox.server = server);

        ServerLifecycleEvents.SERVER_STOPPED.register(server -> Motobox.server = null);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register((entries) -> {
            entries.add(MotoboxBlocks.ALLOW);
        });
    }

    public static void initOther() {
        Registry.register(Registries.RECIPE_TYPE, MechanicTableRecipe.ID, MechanicTableRecipe.TYPE);
        Registry.register(Registries.RECIPE_SERIALIZER, MechanicTableRecipe.ID, MechanicTableRecipeSerializer.INSTANCE);
    }

    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }
}
