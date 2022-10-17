package funskydev.glovesmod.util;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import funskydev.glovesmod.items.Glove;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class GloveUtils {

    public static Predicate<ItemStack> getGlovePredicate() {
        return stack -> stack.getItem() instanceof Glove;
    }

    public static List<Pair<SlotReference, ItemStack>> getGlovesList(TrinketComponent trinketComponent) {
        return trinketComponent.getEquipped(getGlovePredicate());
    }

    public static List<Pair<SlotReference, ItemStack>> getGlovesList(PlayerEntity player) {
        Optional<TrinketComponent> trinketComponent = TrinketsApi.getTrinketComponent(player);
        if(!trinketComponent.isPresent()) return new ArrayList<>();
        return getGlovesList(trinketComponent.get());
    }

    public static Optional<Pair<SlotReference, ItemStack>> getGlovePair(PlayerEntity player, boolean mainHand) {

        List<Pair<SlotReference, ItemStack>> glovesList = getGlovesList(player);
        if(glovesList.isEmpty()) return Optional.empty();

        String hand = mainHand ? "hand" : "offhand";

        for(Pair<SlotReference, ItemStack> glove : glovesList) {

            if(glove.getLeft().inventory().getSlotType().getGroup().equals(hand) && glove.getRight().getItem() instanceof Glove gloveItem) return Optional.of(glove);

        }

        return Optional.empty();

    }

    public static Optional<GloveType> getGloveType(PlayerEntity player, boolean mainHand) {

        Optional<Pair<SlotReference, ItemStack>> glovePair = getGlovePair(player, mainHand);

        if(glovePair.isPresent()) return Optional.of(((Glove) glovePair.get().getRight().getItem()).getGloveType());

        return Optional.empty();

    }

    public static Optional<ItemStack> getGloveItemStack(PlayerEntity player, boolean mainHand) {

        Optional<Pair<SlotReference, ItemStack>> glovePair = getGlovePair(player, mainHand);

        if(glovePair.isPresent()) return Optional.of(glovePair.get().getRight());

        return Optional.empty();

    }

}
