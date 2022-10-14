package funskydev.glovesmod;

import funskydev.glovesmod.init.ItemsInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main implements ModInitializer {

	public static final String MODID = "glovesmod";

	public static final Logger LOGGER = LoggerFactory.getLogger("glovesmod");

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MODID), () -> new ItemStack(ItemsInit.LEATHER_GLOVE));

	@Override
	public void onInitialize() {

		ItemsInit.registerItems();

	}
}
