package funskydev.glovesmod.client.render;

import funskydev.glovesmod.util.GloveType;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class OffhandGloveFeatureRenderer<T extends PlayerEntity, M extends PlayerEntityModel<T>> extends GloveFeatureRenderer<T, M> {

    public OffhandGloveFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext, EntityModelLoader loader) {
        super(featureRendererContext, loader);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        Optional<GloveType> gloveType = getGloveType(entity, false);

        if(gloveType.isPresent()) {
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) entity;

            boolean slim = isSlim(player);
            ModelPart gloveModel = slim ? slimGlove : glove;
            Identifier gloveTexture = slim ? gloveType.get().getTextures().getRight() : gloveType.get().getTextures().getRight();

            renderGlove(matrixStack, vertexConsumers, light, player, (PlayerEntityModel)this.getContextModel(), player.getMainArm().equals(Arm.RIGHT) ? Arm.LEFT : Arm.RIGHT, gloveModel, gloveTexture);
        }

    }
}
