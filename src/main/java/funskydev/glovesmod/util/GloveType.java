package funskydev.glovesmod.util;

import funskydev.glovesmod.Main;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public enum GloveType {

    LEATHER("leather");

    private Pair<Identifier, Identifier> textures;

    GloveType(String name) {

        String path = "textures/glove/" + name + "_glove";
        String slimPath = path + "_slim.png";
        path += ".png";
        this.textures = new Pair<>(new Identifier(Main.MODID, path), new Identifier(Main.MODID, slimPath));

    }

    public Pair<Identifier, Identifier> getTextures() {

        return this.textures;

    }

}
