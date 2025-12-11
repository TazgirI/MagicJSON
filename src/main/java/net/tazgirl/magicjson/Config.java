package net.tazgirl.magicjson;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue CLEAR_HOLDER_RELATIONS = BUILDER
            .comment("Whether to to clear the relationship maps in every holder once the server has started \nThis will save memory if a large amount of Statements are loaded but will prevent hotswapping behaviour once the server has started \nSome addons or usecases may require it to be disabled")
            .define("clearHolderRelations", true);



    public static final ModConfigSpec SPEC = BUILDER.build();


}
