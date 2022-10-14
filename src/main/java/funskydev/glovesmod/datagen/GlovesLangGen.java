package funskydev.glovesmod.datagen;

import funskydev.glovesmod.init.ItemsInit;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class GlovesLangGen extends FabricLanguageProvider {

    protected GlovesLangGen(FabricDataGenerator dataGenerator) {
        super(dataGenerator, "en_us");
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {

        translationBuilder.add(ItemsInit.LEATHER_GLOVE, "Leather Glove");

    }

}