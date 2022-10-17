package funskydev.glovesmod.client.render;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import funskydev.glovesmod.client.ClientMain;
import funskydev.glovesmod.items.Glove;
import funskydev.glovesmod.util.GloveType;
import net.minecraft.client.model.*;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class GloveFeatureRenderer<T extends PlayerEntity, M extends PlayerEntityModel<T>> extends FeatureRenderer<T, M> {

    protected final ModelPart glove;
    protected final ModelPart slimGlove;

    public GloveFeatureRenderer(FeatureRendererContext<T, M> featureRendererContext, EntityModelLoader loader) {
        super(featureRendererContext);
        ModelPart gloveRoot = loader.getModelPart(ClientMain.GLOVE_LAYER);
        this.glove = gloveRoot.getChild("glove");
        this.slimGlove = gloveRoot.getChild("slim_glove");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation = new Dilation(0.3F);
        modelPartData.addChild("glove", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -3.0F, -2.0F, 4.0F, 3.0F, 4.0F, dilation), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        modelPartData.addChild("slim_glove", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, -2.0F, 3.0F, 3.0F, 4.0F, dilation), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        return TexturedModelData.of(modelData, 16, 16);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {

        Optional<GloveType> gloveType = getGloveType(entity, true);

        if(gloveType.isPresent()) {
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) entity;

            boolean slim = isSlim(player);
            ModelPart gloveModel = slim ? slimGlove : glove;
            Identifier gloveTexture = slim ? gloveType.get().getTextures().getRight() : gloveType.get().getTextures().getRight();

            renderGlove(matrixStack, vertexConsumers, light, player, (PlayerEntityModel)this.getContextModel(), player.getMainArm().equals(Arm.RIGHT) ? Arm.RIGHT : Arm.LEFT, gloveModel, gloveTexture);
        }

    }

    protected static boolean isSlim(AbstractClientPlayerEntity player) {
        return player.getModel().equals("slim");
    }

    protected static Predicate<ItemStack> getGlovePredicate() {

        return stack -> stack.getItem() instanceof Glove;

    }

    protected static Optional<GloveType> getGloveType(PlayerEntity entity, boolean mainHand) {

        if(entity instanceof AbstractClientPlayerEntity player) {

            Optional<TrinketComponent> trinketComponent = TrinketsApi.getTrinketComponent(player);
            if(!trinketComponent.isPresent()) return Optional.empty();

            List<Pair<SlotReference, ItemStack>> glovesList = trinketComponent.get().getEquipped(getGlovePredicate());
            if(glovesList.isEmpty()) return Optional.empty();

            String hand = mainHand ? "hand" : "offhand";

            for(Pair<SlotReference, ItemStack> glove : glovesList) {

                if(glove.getLeft().inventory().getSlotType().getGroup().equals(hand)) {
                    if(glove.getRight().getItem() instanceof Glove gloveItem) return Optional.of(gloveItem.getGloveType());
                }

            }

        }

        return Optional.empty();

    }

    protected static void renderGlove(MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, PlayerEntityModel playerModel, Arm arm, ModelPart gloveModel, Identifier gloveTexture) {

        if(arm.equals(Arm.RIGHT)) {

            ModelPart targetModelPart = playerModel.rightArm;

            matrixStack.push();

            targetModelPart.rotate(matrixStack);
            matrixStack.translate(-0.0625D, -0.875D, 0D);
            matrixStack.scale(-1f, 1f, 1f);

            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumers, RenderLayer.getArmorCutoutNoCull(gloveTexture), false, false);
            gloveModel.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

            matrixStack.pop();

        } else {

            ModelPart targetModelPart = playerModel.leftArm;

            matrixStack.push();

            targetModelPart.rotate(matrixStack);
            matrixStack.translate(0.0625D, -0.875D, 0D);

            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCull(gloveTexture));
            gloveModel.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV);

            matrixStack.pop();

        }

    }

}
