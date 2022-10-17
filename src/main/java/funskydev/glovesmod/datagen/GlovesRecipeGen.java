package funskydev.glovesmod.datagen;

import funskydev.glovesmod.Main;
import funskydev.glovesmod.init.ItemsInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.RecipeProvider;
import net.minecraft.data.server.recipe.CraftingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class GlovesRecipeGen extends FabricRecipeProvider {

    public GlovesRecipeGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {

        createGloveRecipe(ItemsInit.LEATHER_GLOVE, Items.LEATHER, exporter);

    }

    protected static void createGloveRecipe(ItemConvertible result, ItemConvertible material, Consumer<RecipeJsonProvider> exporter) {
        if(material.equals(Items.LEATHER))
            ShapedRecipeJsonBuilder.create(result, 1)
                    .input(Character.valueOf('L'), Items.LEATHER)
                    .input(Character.valueOf('S'), Items.STRING)
                    .pattern("LLL")
                    .pattern("LLL")
                    .pattern("LSL")
                    .criterion(RecipeProvider.hasItem(Items.LEATHER), RecipeProvider.conditionsFromItem(Items.LEATHER))
                    .criterion(RecipeProvider.hasItem(Items.STRING), RecipeProvider.conditionsFromItem(Items.STRING))
                    .offerTo(exporter);
        else
            ShapedRecipeJsonBuilder.create(result, 1)
                .input(Character.valueOf('#'), material)
                .input(Character.valueOf('L'), Items.LEATHER)
                .input(Character.valueOf('S'), Items.STRING)
                .pattern("###")
                .pattern("#L#")
                .pattern("LSL")
                .criterion(RecipeProvider.hasItem(material), RecipeProvider.conditionsFromItem(material))
                .criterion(RecipeProvider.hasItem(Items.LEATHER), RecipeProvider.conditionsFromItem(Items.LEATHER))
                .criterion(RecipeProvider.hasItem(Items.STRING), RecipeProvider.conditionsFromItem(Items.STRING))
                .offerTo(exporter);
    }

}
