package funskydev.glovesmod.items;

import dev.emi.trinkets.api.TrinketItem;
import funskydev.glovesmod.Main;
import funskydev.glovesmod.util.GloveType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Rarity;

public class Glove extends TrinketItem {

    private GloveType gloveType;

    public Glove(GloveType gloveType, Rarity rarity) {
        super(new Settings().maxCount(1).maxDamageIfAbsent(gloveType.getMaterial().getDurability(EquipmentSlot.CHEST)).group(ItemGroup.TOOLS).rarity(rarity));
        this.gloveType = gloveType;
    }

    public GloveType getGloveType() {
        return this.gloveType;
    }

}
