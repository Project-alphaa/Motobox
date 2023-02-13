package motobox;

import motobox.block.MotoboxBlocks;
import motobox.block.entity.render.VehicleAssemblerBlockEntityRenderer;
import motobox.entity.MotoboxEntities;
import motobox.entity.VehicleEntity;
import motobox.entity.ufo.UfoInput;
import motobox.item.MotoboxItems;
import motobox.particle.MotoboxParticles;
import motobox.render.MotoboxModels;
import motobox.resource.MotoboxAssets;
import motobox.screen.MechanicTableScreen;
import motobox.screen.SingleSlotScreen;
import motobox.screen.VehicleHud;
import motobox.util.network.PayloadPackets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;

public class MotoboxClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MotoboxModels.init();
        MotoboxBlocks.initClient();
        MotoboxItems.initClient();
        MotoboxEntities.initClient();
        MotoboxParticles.initClient();
        PayloadPackets.initClient();

        MotoboxAssets.setup();

        HandledScreens.register(Motobox.MECHANIC_SCREEN, MechanicTableScreen::new);
        HandledScreens.register(Motobox.SINGLE_SLOT_SCREEN, SingleSlotScreen::new);

        HudRenderCallback.EVENT.register((matrices, delta) -> {
            var player = MinecraftClient.getInstance().player;
            if (player.getVehicle() instanceof VehicleEntity vehicle) {
                VehicleHud.render(matrices, player, vehicle, delta);
            }
        });

        BlockRenderLayerMap.INSTANCE.putBlock(MotoboxBlocks.VEHICLE_ASSEMBLER, RenderLayer.getCutout());

        BlockEntityRendererRegistry.register(MotoboxBlocks.VEHICLE_ASSEMBLER_BLOCK_ENTITY, VehicleAssemblerBlockEntityRenderer::new);

        UfoInput.registerKeybinds();
    }
}
