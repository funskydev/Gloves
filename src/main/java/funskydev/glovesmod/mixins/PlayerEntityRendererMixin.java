package funskydev.glovesmod.mixins;

import funskydev.glovesmod.client.render.GloveFeatureRenderer;
import funskydev.glovesmod.util.GloveType;
import funskydev.glovesmod.util.GloveUtils;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererMixin {

    @Inject(method = "renderRightArm", at = @At("TAIL"))
    private void renderRightArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, CallbackInfo info) {

        Optional<GloveType> gloveType;

        if(player.getMainArm().equals(Arm.RIGHT)) gloveType = GloveUtils.getGloveType(player, true);
        else gloveType = GloveUtils.getGloveType(player, false);

        if(gloveType.isPresent()) {

            boolean slim = GloveFeatureRenderer.isSlim(player);
            ModelPart gloveModel = GloveFeatureRenderer.getGloveModel(slim);
            Identifier gloveTexture = slim ? gloveType.get().getTextures().getRight() : gloveType.get().getTextures().getRight();

            PlayerEntityRenderer playerEntityRenderer = (PlayerEntityRenderer) (Object) this;
            ModelPart targetModelPart = playerEntityRenderer.getModel().rightArm;

            matrices.push();

            targetModelPart.rotate(matrices);
            matrices.translate(slim ? 0 : -0.0625D, -0.875D, 0D);
            //matrices.scale(-1f, 1f, 1f);

            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(gloveTexture), false, false);
            gloveModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

            matrices.pop();

        }

    }

    @Inject(method = "renderLeftArm", at = @At("TAIL"))
    private void renderLeftArm(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, CallbackInfo info) {

        Optional<GloveType> gloveType;

        if(player.getMainArm().equals(Arm.LEFT)) gloveType = GloveUtils.getGloveType(player, true);
        else gloveType = GloveUtils.getGloveType(player, false);

        if(gloveType.isPresent()) {

            boolean slim = GloveFeatureRenderer.isSlim(player);
            ModelPart gloveModel = GloveFeatureRenderer.getGloveModel(slim);
            Identifier gloveTexture = slim ? gloveType.get().getTextures().getRight() : gloveType.get().getTextures().getRight();

            PlayerEntityRenderer playerEntityRenderer = (PlayerEntityRenderer) (Object) this;
            ModelPart targetModelPart = playerEntityRenderer.getModel().leftArm;

            matrices.push();

            targetModelPart.rotate(matrices);
            matrices.translate(slim ? 0 : 0.0625D, -0.875D, 0D);
            matrices.scale(-1f, 1f, 1f);

            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(gloveTexture), false, false);
            gloveModel.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

            matrices.pop();

        }

    }

}
