package motobox.resource;

import motobox.Motobox;
import motobox.util.AUtils;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.api.RuntimeResourcePack;
import net.devtech.arrp.json.blockstate.JState;
import net.devtech.arrp.json.blockstate.JVariant;
import net.devtech.arrp.json.models.JModel;
import net.minecraft.util.math.Direction;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public enum MotoboxAssets {
    ;
    public static final RuntimeResourcePack PACK = RuntimeResourcePack.create("motobox:assets");
    private static final Set<Consumer<RuntimeResourcePack>> PROCESSORS = new HashSet<>();

    public static void setup() {
        addOffRoad("grass_off_road");
        addOffRoad("dirt_off_road");
        addOffRoad("sand_off_road");
        addOffRoad("snow_off_road");

        var dashPanel = JState.variant();
        for (Direction dir : AUtils.HORIZONTAL_DIRS) {
            dashPanel.put("left=false,right=false,facing=" + dir, JState.model(Motobox.id("block/dash_panel_single")).y((int) dir.asRotation() + 180));
            dashPanel.put("left=true,right=false,facing=" + dir, JState.model(Motobox.id("block/dash_panel_left")).y((int) dir.asRotation() + 180));
            dashPanel.put("left=false,right=true,facing=" + dir, JState.model(Motobox.id("block/dash_panel_right")).y((int) dir.asRotation() + 180));
            dashPanel.put("left=true,right=true,facing=" + dir, JState.model(Motobox.id("block/dash_panel_center")).y((int) dir.asRotation() + 180));
        }
        PACK.addBlockState(new JState().add(dashPanel), Motobox.id("dash_panel"));

        var slopedDashPanel = JState.variant();
        for (Direction dir : AUtils.HORIZONTAL_DIRS) {
            slopedDashPanel.put("half=bottom,left=false,right=false,facing=" + dir, JState.model(Motobox.id("block/sloped_dash_panel_single_bottom")).y((int) dir.asRotation()));
            slopedDashPanel.put("half=bottom,left=true,right=false,facing=" + dir, JState.model(Motobox.id("block/sloped_dash_panel_left_bottom")).y((int) dir.asRotation()));
            slopedDashPanel.put("half=bottom,left=false,right=true,facing=" + dir, JState.model(Motobox.id("block/sloped_dash_panel_right_bottom")).y((int) dir.asRotation()));
            slopedDashPanel.put("half=bottom,left=true,right=true,facing=" + dir, JState.model(Motobox.id("block/sloped_dash_panel_center_bottom")).y((int) dir.asRotation()));
            slopedDashPanel.put("half=top,left=false,right=false,facing=" + dir, JState.model(Motobox.id("block/sloped_dash_panel_single_top")).y((int) dir.asRotation()));
            slopedDashPanel.put("half=top,left=true,right=false,facing=" + dir, JState.model(Motobox.id("block/sloped_dash_panel_left_top")).y((int) dir.asRotation()));
            slopedDashPanel.put("half=top,left=false,right=true,facing=" + dir, JState.model(Motobox.id("block/sloped_dash_panel_right_top")).y((int) dir.asRotation()));
            slopedDashPanel.put("half=top,left=true,right=true,facing=" + dir, JState.model(Motobox.id("block/sloped_dash_panel_center_top")).y((int) dir.asRotation()));
        }
        PACK.addBlockState(new JState().add(slopedDashPanel), Motobox.id("sloped_dash_panel"));

        var steepDashPanel = JState.variant();
        for (Direction dir : AUtils.HORIZONTAL_DIRS) {
            steepDashPanel.put("left=false,right=false,facing=" + dir, JState.model(Motobox.id("block/steep_sloped_dash_panel_single")).y((int) dir.asRotation()));
            steepDashPanel.put("left=true,right=false,facing=" + dir, JState.model(Motobox.id("block/steep_sloped_dash_panel_left")).y((int) dir.asRotation()));
            steepDashPanel.put("left=false,right=true,facing=" + dir, JState.model(Motobox.id("block/steep_sloped_dash_panel_right")).y((int) dir.asRotation()));
            steepDashPanel.put("left=true,right=true,facing=" + dir, JState.model(Motobox.id("block/steep_sloped_dash_panel_center")).y((int) dir.asRotation()));
        }
        PACK.addBlockState(new JState().add(steepDashPanel), Motobox.id("steep_sloped_dash_panel"));

        PACK.addBlockState(new JState().add(JState.variant().put("", JState.model(Motobox.id("block/launch_gel")))), Motobox.id("launch_gel"));

        for (var p : PROCESSORS) {
            p.accept(PACK);
        }

        RRPCallback.AFTER_VANILLA.register(a -> a.add(PACK));
    }

    public static void addOffRoad(String name) {
        for (int i = 0; i < 3; i++) {
            PACK.addModel(new JModel().parent("motobox:block/template_off_road_" + i).textures(JModel.textures().var("off_road", "motobox:block/" + name)), Motobox.id("block/" + name + "_" + i));
        }
        PACK.addModel(new JModel().parent("motobox:block/" + name + "_0"), Motobox.id("item/" + name));
        PACK.addBlockState(new JState().add(new JVariant()
                .put("layers=1", JState.model("motobox:block/" + name + "_0"))
                .put("layers=2", JState.model("motobox:block/" + name + "_1"))
                .put("layers=3", JState.model("motobox:block/" + name + "_2"))
        ), Motobox.id(name));
    }

    public static void addSlope(String name, String texture) {
        {
            var path = "block/" + name;
            PACK.addModel(new JModel().parent("motobox:block/template_slope_bottom").textures(JModel.textures().var("slope", texture)), Motobox.id(path + "_bottom"));
            PACK.addModel(new JModel().parent("motobox:block/template_slope_top").textures(JModel.textures().var("slope", texture)), Motobox.id(path + "_top"));
            var variants = JState.variant();
            for (Direction dir : AUtils.HORIZONTAL_DIRS) {
                variants.put("half=bottom,facing=" + dir, JState.model(Motobox.id(path) + "_bottom").y((int) dir.asRotation()));
                variants.put("half=top,facing=" + dir, JState.model(Motobox.id(path) + "_top").y((int) dir.asRotation()));
            }
            PACK.addBlockState(new JState().add(variants), Motobox.id(name));
            PACK.addModel(new JModel().parent("motobox:" + path + "_bottom"), Motobox.id("item/" + name));
        }
        {
            name = "steep_" + name;
            var path = "block/" + name;
            PACK.addModel(new JModel().parent("motobox:block/template_steep_slope").textures(JModel.textures().var("slope", texture)), Motobox.id(path));
            var variants = JState.variant();
            for (Direction dir : AUtils.HORIZONTAL_DIRS) {
                variants.put("facing=" + dir, JState.model(Motobox.id(path)).y((int) dir.asRotation()));
            }
            PACK.addBlockState(new JState().add(variants), Motobox.id(name));
            PACK.addModel(new JModel().parent("motobox:" + path), Motobox.id("item/" + name));
        }
    }

    // Yes, I didn't want to do actual smart datagen so behold
    // I will more than likely replace this in the future
    public static void addMinecraftSlope(String name, String base) {
        base = switch (base) {
            case "snow_block" -> "snow";
            case "quartz_block" -> "quartz_block_side";
            case "smooth_sandstone" -> "sandstone_top";
            case "smooth_quartz" -> "quartz_block_top";
            case "smooth_red_sandstone" -> "red_sandstone_top";
            case "dried_kelp_block" -> "dried_kelp_side";
            case "ancient_debris" -> "ancient_debris_side";
            case "lodestone" -> "lodestone_top";
            default -> base;
        };
        if (base.startsWith("waxed_") && base.contains("copper")) {
            base = base.replaceFirst("waxed_", "");
        }
        var tex = "minecraft:block/"+base;
        addSlope(name, tex);
    }

    public static void addProcessor(Consumer<RuntimeResourcePack> processor) {
        PROCESSORS.add(processor);
    }
}
