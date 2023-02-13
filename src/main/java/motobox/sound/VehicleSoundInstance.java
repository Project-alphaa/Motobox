package motobox.sound;

import motobox.entity.VehicleEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.MovingSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.random.Random;

public abstract class VehicleSoundInstance extends MovingSoundInstance {
    private final MinecraftClient client;
    private final VehicleEntity vehicle;

    private double lastDistance;

    private int fade = 0;
    private boolean die = false;

    public VehicleSoundInstance(SoundEvent sound, MinecraftClient client, VehicleEntity vehicle) {
        super(sound, SoundCategory.AMBIENT, Random.create());
        this.client = client;
        this.vehicle = vehicle;
        this.repeat = true;
        this.repeatDelay = 0;
    }

    protected abstract boolean canPlay(VehicleEntity vehicle);

    protected abstract float getPitch(VehicleEntity vehicle);

    protected abstract float getVolume(VehicleEntity vehicle);

    @Override
    public void tick() {
        var player = this.client.player;
        if (vehicle.isRemoved() || player == null) {
            this.setDone();
            return;
        } else if (!this.canPlay(vehicle)) {
            this.die = true;
        }

        if (this.die) {
            if (this.fade > 0) this.fade--;
            else if (this.fade == 0) {
                this.setDone();
                return;
            }
        } else if (this.fade < 3) {
            this.fade++;
        }
        this.volume = this.getVolume(this.vehicle) * (float) fade / 3;

        this.x = this.vehicle.getX();
        this.y = this.vehicle.getY();
        this.z = this.vehicle.getZ();

        this.pitch = this.getPitch(this.vehicle);

        if (player.getVehicle() != this.vehicle) {
            double distance = this.vehicle.getPos().subtract(player.getPos()).length();
            this.pitch += (0.36 * Math.atan(lastDistance - distance));

            this.lastDistance = distance;
        } else {
            this.lastDistance = 0;
        }
    }

    public static class EngineSound extends VehicleSoundInstance {
        public EngineSound(MinecraftClient client, VehicleEntity vehicle) {
            super(vehicle.getEngine().sound(), client, vehicle);
        }

        @Override
        protected boolean canPlay(VehicleEntity vehicle) {
            return vehicle.engineRunning() || vehicle.getBoostTimer() > 0;
        }

        @Override
        protected float getPitch(VehicleEntity vehicle) {
            return (float) (Math.pow(4, (vehicle.getEffectiveSpeed() - 0.9)) + 0.32);
        }

        @Override
        protected float getVolume(VehicleEntity vehicle) {
            return 1;
        }
    }

    public static class SkiddingSound extends VehicleSoundInstance {
        public SkiddingSound(MinecraftClient client, VehicleEntity vehicle) {
            super(MotoboxSounds.SKID, client, vehicle);
        }

        @Override
        protected boolean canPlay(VehicleEntity vehicle) {
            return vehicle.isDrifting() || vehicle.burningOut();
        }

        @Override
        protected float getPitch(VehicleEntity vehicle) {
            return vehicle.burningOut() ? 0.75f :
                    1 + 0.056f * ((float) Math.min(vehicle.getTurboCharge(), VehicleEntity.LARGE_TURBO_TIME) / VehicleEntity.LARGE_TURBO_TIME);
        }

        @Override
        protected float getVolume(VehicleEntity vehicle) {
            return vehicle.vehicleOnGround() ? 1 : 0;
        }
    }
}
