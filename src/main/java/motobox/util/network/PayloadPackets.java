package motobox.util.network;

import motobox.Motobox;
import motobox.entity.VehicleEntity;
import motobox.vehicle.VehicleEngine;
import motobox.vehicle.VehicleFrame;
import motobox.vehicle.VehicleWheel;
import motobox.vehicle.attachment.FrontAttachmentType;
import motobox.vehicle.attachment.RearAttachmentType;
import motobox.vehicle.attachment.rear.BannerPostRearAttachment;
import io.netty.buffer.Unpooled;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public enum PayloadPackets {;

    @Environment(EnvType.CLIENT)
    public static void sendSyncVehicleInputPacket(VehicleEntity entity, boolean fwd, boolean back, boolean left, boolean right, boolean space) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBoolean(fwd);
        buf.writeBoolean(back);
        buf.writeBoolean(left);
        buf.writeBoolean(right);
        buf.writeBoolean(space);
        buf.writeInt(entity.getId());
        ClientPlayNetworking.send(Motobox.id("sync_vehicle_inputs"), buf);
    }

    @Environment(EnvType.CLIENT)
    public static void requestSyncVehicleComponentsPacket(VehicleEntity entity) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getId());
        ClientPlayNetworking.send(Motobox.id("request_sync_vehicle_components"), buf);
    }

    public static void sendSyncVehicleDataPacket(VehicleEntity entity, ServerPlayerEntity player) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getId());
        entity.writeSyncToClientData(buf);
        ServerPlayNetworking.send(player, Motobox.id("sync_vehicle_data"), buf);
    }

    public static void sendSyncVehicleComponentsPacket(VehicleEntity entity, ServerPlayerEntity player) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getId());
        buf.writeString(entity.getFrame().id().toString());
        buf.writeString(entity.getWheels().id().toString());
        buf.writeString(entity.getEngine().id().toString());
        ServerPlayNetworking.send(player, Motobox.id("sync_vehicle_components"), buf);
    }

    public static void sendSyncVehicleAttachmentsPacket(VehicleEntity entity, ServerPlayerEntity player) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeInt(entity.getId());
        buf.writeString(entity.getRearAttachmentType().id().toString());
        buf.writeString(entity.getFrontAttachmentType().id().toString());
        ServerPlayNetworking.send(player, Motobox.id("sync_vehicle_attachments"), buf);
    }

    public static void sendBannerPostAttachmentUpdatePacket(VehicleEntity entity, NbtCompound banner, ServerPlayerEntity player) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());

        var rearAtt = entity.getRearAttachment();
        if (rearAtt instanceof BannerPostRearAttachment) {
            buf.writeInt(entity.getId());
            buf.writeNbt(banner);
            ServerPlayNetworking.send(player, Motobox.id("update_banner_post"), buf);
        }
    }

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(Motobox.id("sync_vehicle_inputs"), (server, player, handler, buf, responseSender) -> {
            boolean fwd = buf.readBoolean();
            boolean back = buf.readBoolean();
            boolean left = buf.readBoolean();
            boolean right = buf.readBoolean();
            boolean space = buf.readBoolean();
            int entityId = buf.readInt();
            server.execute(() -> {
                if (player.getWorld().getEntityById(entityId) instanceof VehicleEntity vehicle) {
                    vehicle.setInputs(fwd, back, left, right, space);
                    vehicle.markDirty();
                }
            });
        });
        ServerPlayNetworking.registerGlobalReceiver(Motobox.id("request_sync_vehicle_components"), (server, player, handler, buf, responseSender) -> {
            int entityId = buf.readInt();
            server.execute(() -> {
                if (player.getWorld().getEntityById(entityId) instanceof VehicleEntity vehicle) {
                    sendSyncVehicleComponentsPacket(vehicle, player);
                    sendSyncVehicleAttachmentsPacket(vehicle, player);

                    var fAtt = vehicle.getFrontAttachment();
                    if (fAtt != null) fAtt.updatePacketRequested(player);

                    var rAtt = vehicle.getRearAttachment();
                    if (rAtt != null) rAtt.updatePacketRequested(player);
                }
            });
        });
    }

    @Environment(EnvType.CLIENT)
    public static void initClient() {
        ClientPlayNetworking.registerGlobalReceiver(Motobox.id("sync_vehicle_data"), (client, handler, buf, responseSender) -> {
            PacketByteBuf dup = PacketByteBufs.copy(buf);
            int entityId = dup.readInt();
            client.execute(() -> {
                if (client.player.getWorld().getEntityById(entityId) instanceof VehicleEntity vehicle) {
                    vehicle.readSyncToClientData(dup);
                }
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(Motobox.id("sync_vehicle_components"), (client, handler, buf, responseSender) -> {
            int entityId = buf.readInt();
            var frame = VehicleFrame.REGISTRY.getOrDefault(Identifier.tryParse(buf.readString()));
            var wheel = VehicleWheel.REGISTRY.getOrDefault(Identifier.tryParse(buf.readString()));
            var engine = VehicleEngine.REGISTRY.getOrDefault(Identifier.tryParse(buf.readString()));
            client.execute(() -> {
                if (client.player.getWorld().getEntityById(entityId) instanceof VehicleEntity vehicle) {
                    vehicle.setComponents(frame, wheel, engine);
                }
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(Motobox.id("sync_vehicle_attachments"), (client, handler, buf, responseSender) -> {
            int entityId = buf.readInt();
            var rearAtt = RearAttachmentType.REGISTRY.getOrDefault(Identifier.tryParse(buf.readString()));
            var frontAtt = FrontAttachmentType.REGISTRY.getOrDefault(Identifier.tryParse(buf.readString()));
            client.execute(() -> {
                if (client.player.getWorld().getEntityById(entityId) instanceof VehicleEntity vehicle) {
                    vehicle.setRearAttachment(rearAtt);
                    vehicle.setFrontAttachment(frontAtt);
                }
            });
        });
        ClientPlayNetworking.registerGlobalReceiver(Motobox.id("update_banner_post"), (client, handler, buf, responseSender) -> {
            int entityId = buf.readInt();
            var banner = buf.readNbt();
            client.execute(() -> {
                if (client.player.getWorld().getEntityById(entityId) instanceof VehicleEntity vehicle &&
                        vehicle.getRearAttachment() instanceof BannerPostRearAttachment bannerPost) {
                    bannerPost.setFromNbt(banner);
                }
            });
        });
    }
}
