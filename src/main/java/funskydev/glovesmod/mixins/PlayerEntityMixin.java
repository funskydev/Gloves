package funskydev.glovesmod.mixins;

import funskydev.glovesmod.Main;
import funskydev.glovesmod.util.GloveType;
import funskydev.glovesmod.util.GloveUtils;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "getBlockBreakingSpeed", at = @At("RETURN"), cancellable = true)
    private void setMiningMultiplier(BlockState state, CallbackInfoReturnable<Float> cir) {

        Optional<GloveType> gloveType = GloveUtils.getGloveType((PlayerEntity) (Object) this, true);

        if(gloveType.isPresent()) cir.setReturnValue(cir.getReturnValue() * gloveType.get().getMiningSpeedMultiplier());

    }

}
