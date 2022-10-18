package funskydev.glovesmod.items;

import dev.emi.trinkets.TrinketSlot;
import dev.emi.trinkets.api.*;
import funskydev.glovesmod.Main;
import funskydev.glovesmod.util.GloveType;
import funskydev.glovesmod.util.GloveUtils;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class Glove extends TrinketItem {

    private GloveType gloveType;

    public Glove(GloveType gloveType, Rarity rarity) {
        super(new Settings().maxCount(1).maxDamageIfAbsent(gloveType.getMaterial().getDurability(EquipmentSlot.CHEST)).group(ItemGroup.TOOLS).rarity(rarity));
        this.gloveType = gloveType;
    }

    public GloveType getGloveType() {
        return this.gloveType;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);
        if (equipGlove(user, stack)) return TypedActionResult.success(stack, world.isClient());
        return TypedActionResult.pass(stack);

    }

    public static boolean equipGlove(PlayerEntity user, ItemStack stack) {

        var optional = TrinketsApi.getTrinketComponent(user);
        if (optional.isPresent()) {

            TrinketComponent comp = optional.get();
            for (var group : comp.getInventory().values()) {
                for (TrinketInventory inv : group.values()) {
                    for (int i = 0; i < inv.size(); i++) {
                        if (inv.getStack(i).isEmpty()) {
                            SlotReference ref = new SlotReference(inv, i);
                            if (TrinketSlot.canInsert(stack, ref, user)) {
                                ItemStack newStack = stack.copy();

                                if(inv.getSlotType().getGroup().equals("offhand") && GloveUtils.getGlovePair(user, true).isEmpty()) comp.getInventory().get("hand").get("glove").setStack(0, newStack);
                                else inv.setStack(i, newStack);

                                SoundEvent soundEvent = stack.getEquipSound();
                                if (!stack.isEmpty() && soundEvent != null) {
                                    user.emitGameEvent(GameEvent.EQUIP);
                                    user.playSound(soundEvent, 1.0F, 1.0F);
                                }
                                stack.setCount(0);
                                return true;
                            }
                        }
                    }
                }
            }

        }

        return false;

    }

}
