package com.limingz.caerula_delight.client.renderer;

import com.limingz.caerula_delight.block.entity.SimpleFeastBlockEntity;
import com.limingz.caerula_delight.client.model.SimpleFeastModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SimpleFeastRenderer extends GeoBlockRenderer<SimpleFeastBlockEntity> {
    public SimpleFeastRenderer() {
        super(new SimpleFeastModel());
    }
}

