package funskydev.glovesmod.items;

import dev.emi.trinkets.api.TrinketItem;
import funskydev.glovesmod.Main;
import funskydev.glovesmod.util.GloveType;
import net.minecraft.util.Rarity;

public class Glove extends TrinketItem {

    private GloveType gloveType;

    public Glove(GloveType gloveType, Rarity rarity) {
        super(new Settings().group(Main.ITEM_GROUP).rarity(rarity));
        this.gloveType = gloveType;
    }

    public GloveType getGloveType() {
        return this.gloveType;
    }

}
