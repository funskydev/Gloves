package funskydev.glovesmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class GlovesDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

        fabricDataGenerator.addProvider(GlovesLangGen::new);
        fabricDataGenerator.addProvider(GlovesModelGen::new);

    }

}
