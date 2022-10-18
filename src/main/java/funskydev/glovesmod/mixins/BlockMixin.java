package funskydev.glovesmod.mixins;

import funskydev.glovesmod.Main;
import funskydev.glovesmod.util.GloveType;
import funskydev.glovesmod.util.GloveUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "afterBreak", at = @At("TAIL"))
    private void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack, CallbackInfo info) {

        Optional<ItemStack> gloveStack = GloveUtils.getGloveItemStack(player, true);
        if(gloveStack.isPresent()) {
            gloveStack.get().damage(1, player, (p) -> {
                //TODO play sound bundle
            });
        }

    }

}
