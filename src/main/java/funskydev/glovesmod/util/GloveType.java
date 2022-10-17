package funskydev.glovesmod.util;

import funskydev.glovesmod.Main;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;

public enum GloveType {

    LEATHER(ArmorMaterials.LEATHER, "leather");

    private ArmorMaterial material;
    private Pair<Identifier, Identifier> textures;

    GloveType(ArmorMaterial material, String name) {

        this.material = material;

        String path = "textures/glove/" + name + "_glove";
        String slimPath = path + "_slim.png";
        path += ".png";
        this.textures = new Pair<>(new Identifier(Main.MODID, path), new Identifier(Main.MODID, slimPath));

    }

    public ArmorMaterial getMaterial() {

        return this.material;

    }

    public Pair<Identifier, Identifier> getTextures() {

        return this.textures;

    }

}
