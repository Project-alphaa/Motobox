package motobox.block;

import motobox.Motobox;
import motobox.block.entity.VehicleAssemblerBlockEntity;
import motobox.item.SlopeBlockItem;
import motobox.item.SteepSlopeBlockItem;
import motobox.item.TooltipBlockItem;
import motobox.resource.MotoboxAssets;
import motobox.resource.MotoboxData;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.function.Function;

@SuppressWarnings("unused")
public enum MotoboxBlocks {
    ;
    public static final Block MECHANIC_TABLE = register("mechanic_table", new MechanicTableBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK).nonOpaque()), MotoboxBlocks::simpleItem);

    public static final Block VEHICLE_ASSEMBLER = register("vehicle_assembler", new VehicleAssemblerBlock(FabricBlockSettings.copyOf(Blocks.ANVIL)), MotoboxBlocks::simpleItem);

    public static final Block LAUNCH_GEL = register("launch_gel", new LaunchGelBlock(FabricBlockSettings.copyOf(Blocks.GLOW_LICHEN).sounds(BlockSoundGroup.HONEY).noCollision()), MotoboxBlocks::simpleItem);

    public static final Block ALLOW = register("allow", new Block(FabricBlockSettings.copyOf(Blocks.BARRIER).sounds(BlockSoundGroup.METAL)),
            b -> new TooltipBlockItem(b, Text.translatable("tooltip.block.motobox.allow").formatted(Formatting.AQUA), new Item.Settings()));

    public static final Block GRASS_OFF_ROAD = register("grass_off_road", new OffRoadBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).noCollision(), new Color(0x406918)), MotoboxBlocks::simpleItem);
    public static final Block DIRT_OFF_ROAD = register("dirt_off_road", new OffRoadBlock(FabricBlockSettings.copyOf(Blocks.DIRT).noCollision(), new Color(0x594227)), MotoboxBlocks::simpleItem);
    public static final Block SAND_OFF_ROAD = register("sand_off_road", new OffRoadBlock(FabricBlockSettings.copyOf(Blocks.SAND).noCollision(), new Color(0xC2B185)), MotoboxBlocks::simpleItem);
    public static final Block SNOW_OFF_ROAD = register("snow_off_road", new OffRoadBlock(FabricBlockSettings.copyOf(Blocks.SNOW_BLOCK).noCollision(), new Color(0xD0E7ED)), MotoboxBlocks::simpleItem);

    public static final Block ASPHALT = register("asphalt", new AsphaltBlock(FabricBlockSettings.create().mapColor(MapColor.BLACK).requiresTool().strength(1.5f, 6.0f)), MotoboxBlocks::simpleItem);
    public static final Block TRAFFIC_CONE = register("traffic_cone", new TrafficConeBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(0.5f, 2.0f)), MotoboxBlocks::simpleItem);
    public static final Block ROADBLOCK = register("roadblock", new RoadblockBlock(FabricBlockSettings.copyOf(Blocks.GRAY_CONCRETE).requiresTool().strength(2.0f, 6.0f)), MotoboxBlocks::simpleItem);


    public static final Block DASH_PANEL = register("dash_panel", new DashPanelBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).luminance(1).emissiveLighting((state, world, pos) -> true).noCollision()), MotoboxBlocks::simpleItem);

    public static final Block SLOPED_DASH_PANEL = register("sloped_dash_panel", new SlopedDashPanelBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).luminance(1).emissiveLighting((state, world, pos) -> true)));
    public static final Block STEEP_SLOPED_DASH_PANEL = register("steep_sloped_dash_panel", new SteepSlopedDashPanelBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).luminance(1).emissiveLighting((state, world, pos) -> true)));

    public static final BlockEntityType<VehicleAssemblerBlockEntity> VEHICLE_ASSEMBLER_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE,
            Motobox.id("vehicle_assembler"), FabricBlockEntityTypeBuilder.create(VehicleAssemblerBlockEntity::new, VEHICLE_ASSEMBLER).build());

    public static void init() {
        Registry.register(Registries.ITEM, Motobox.id("sloped_dash_panel"), new SlopeBlockItem(null, SLOPED_DASH_PANEL, new Item.Settings()));
        Registry.register(Registries.ITEM, Motobox.id("steep_sloped_dash_panel"), new SteepSlopeBlockItem(null, STEEP_SLOPED_DASH_PANEL, new Item.Settings()));
        registerSlopes("minecraft");
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D), GRASS_OFF_ROAD);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> GrassColors.getColor(0.5D, 1.0D), GRASS_OFF_ROAD.asItem());

        BlockRenderLayerMap.INSTANCE.putBlock(LAUNCH_GEL, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(MECHANIC_TABLE, RenderLayer.getCutout());
    }

    public static Block register(String name, Block block) {
        return Registry.register(Registries.BLOCK, Motobox.id(name), block);
    }

    public static Block register(String name, Block block, Function<Block, BlockItem> item) {
        Registry.register(Registries.ITEM, Motobox.id(name), item.apply(block));
        return register(name, block);
    }

    private static void makeStairsSticky(Block candidate, Identifier id) {
        if (candidate instanceof StairsBlock) {
            MotoboxData.STICKY_SLOPE_TAG_CANDIDATES.add(id);
            MotoboxData.STICKY_SLOPE_TAG_CANDIDATES.add(id);
        }
    }

    public static void registerSlopes(String namespace) {
        MotoboxData.NON_STEEP_SLOPE_TAG_CANDIDATES.add(Motobox.id("sloped_dash_panel"));
        MotoboxData.STEEP_SLOPE_TAG_CANDIDATES.add(Motobox.id("steep_sloped_dash_panel"));
        for (var base : Registries.BLOCK) {
            if (base.getClass().equals(Block.class) && base != Blocks.REINFORCED_DEEPSLATE) {
                var id = Registries.BLOCK.getId(base);
                if (id.getNamespace().equals(namespace)) {
                    var path = id.getPath() + "_slope";
                    var steepPath = "steep_" + path;
                    var block = register(path, new SlopeBlock(FabricBlockSettings.copyOf(base)));
                    var normalId = Motobox.id(path);
                    var steepId = Motobox.id(steepPath);
                    Registry.register(Registries.ITEM, normalId, new SlopeBlockItem(base, block, new Item.Settings()));
                    block = register(steepPath, new SteepSlopeBlock(FabricBlockSettings.copyOf(base)));
                    Registry.register(Registries.ITEM, steepId, new SteepSlopeBlockItem(base, block, new Item.Settings()));
                    MotoboxAssets.addProcessor(pack -> MotoboxAssets.addMinecraftSlope(path, id.getPath()));
                    MotoboxData.NON_STEEP_SLOPE_TAG_CANDIDATES.add(normalId);
                    MotoboxData.STEEP_SLOPE_TAG_CANDIDATES.add(steepId);
                }
            }

            makeStairsSticky(base, Registries.BLOCK.getId(base));
        }

        RegistryEntryAddedCallback.event(Registries.BLOCK).register((raw, id, block) -> makeStairsSticky(block, id));
    }

    private static BlockItem simpleItem(Block block) {
        return new BlockItem(block, new Item.Settings());
    }
}
