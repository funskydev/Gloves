package funskydev.glovesmod.init;

import funskydev.glovesmod.Main;
import funskydev.glovesmod.items.LeatherGlove;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemsInit {

    public static final Item LEATHER_GLOVE = registerItem("leather_glove", new LeatherGlove());

    private static <T extends Item> T registerItem(String name, T item){
        return Registry.register(Registry.ITEM, new Identifier(Main.MODID, name), item);
    }

    public static void registerItems() {
        Main.LOGGER.debug("Registering Gloves items");
    }

}
