package funskydev.glovesmod.items;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import funskydev.glovesmod.util.GloveType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Rarity;

import java.util.UUID;

public class LeatherGlove extends Glove {

    public LeatherGlove() {
        super(GloveType.LEATHER, Rarity.COMMON);
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        modifiers.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(uuid, "glovesmod:attack_speed", 1.0D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        return modifiers;
    }

}
