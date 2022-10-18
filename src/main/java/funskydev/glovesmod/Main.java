package funskydev.glovesmod;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.SlotType;
import dev.emi.trinkets.api.TrinketsApi;
import funskydev.glovesmod.init.ItemsInit;
import funskydev.glovesmod.util.GloveUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
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

		TrinketsApi.registerTrinketPredicate(new Identifier(Main.MODID, "check_mainhand"), (itemStack, slotReference, entity) -> {

			if(entity instanceof ClientPlayerEntity player) {

				SlotType slot = slotReference.inventory().getSlotType();
				if(GloveUtils.getGloveType(player, true).isPresent()) return TriState.TRUE;

			}

			return TriState.DEFAULT;
		});

	}
}
