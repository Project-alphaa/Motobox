package motobox.entity.model;

import motobox.Motobox;
import motobox.entity.ufo.UfoEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class UfoEntityModel extends GeoModel<UfoEntity> {

    @Override
    public Identifier getAnimationResource(UfoEntity animatable) {
        return Motobox.id("animations/entity/ufo.animation.json");
    }

    @Override
    public Identifier getModelResource(UfoEntity object) {
        return Motobox.id("geo/entity/ufo.geo.json");
    }

    @Override
    public Identifier getTextureResource(UfoEntity object) {
        return Motobox.id("textures/entity/ufo.png");
    }
}