package funskydev.glovesmod.client;

import funskydev.glovesmod.Main;
import funskydev.glovesmod.client.render.GloveFeatureRenderer;
import funskydev.glovesmod.client.render.OffhandGloveFeatureRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ClientMain implements ClientModInitializer {

    public static final EntityModelLayer GLOVE_LAYER = new EntityModelLayer(new Identifier(Main.MODID, "glove"), "main");

    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(GLOVE_LAYER, GloveFeatureRenderer::getTexturedModelData);

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if(entityRenderer instanceof PlayerEntityRenderer playerEntityRenderer) {
                registrationHelper.register(new GloveFeatureRenderer<>(playerEntityRenderer, context.getModelLoader()));
                registrationHelper.register(new OffhandGloveFeatureRenderer<>(playerEntityRenderer, context.getModelLoader()));
            }
        });

    }

}
