package net.tazgirl.magicjson.optionals;

import com.google.gson.JsonElement;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.optionals.minecraft_types.MobEffectHolderStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.DoubleStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.FloatStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.IntegerStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.LongStatementOptional;
import net.tazgirl.magicjson.optionals.tests.ResultTest;

import java.util.Optional;

public class OptionalFrom
{
    public static IntegerStatementOptional INT(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new IntegerStatementOptional(OptionalValue.from(element.getAsInt()));
        }
        catch (Exception ignored)
        {

        }

        return new IntegerStatementOptional(OptionalValue.from(element.getAsString(), ResultTest.INTEGER));
    }

    public static FloatStatementOptional FLOAT(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new FloatStatementOptional(OptionalValue.from(element.getAsFloat()));
        }
        catch (Exception ignored)
        {

        }

        return new FloatStatementOptional(OptionalValue.from(element.getAsString(), ResultTest.FLOAT));
    }

    public static DoubleStatementOptional DOUBLE(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new DoubleStatementOptional(OptionalValue.from(element.getAsDouble()));
        }
        catch (Exception ignored)
        {

        }

        return new DoubleStatementOptional(OptionalValue.from(element.getAsString(), ResultTest.DOUBLE));
    }

    public static LongStatementOptional LONG(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new LongStatementOptional(OptionalValue.from(element.getAsLong()));
        }
        catch (Exception ignored)
        {

        }

        return new LongStatementOptional(OptionalValue.from(element.getAsString(), ResultTest.LONG));
    }


    public static StringStatementOptional STRING(JsonElement element)
    {
        try
        {
            return StringStatementOptional.from(element.getAsString());
        }
        catch (Exception ignored)
        {

        }

        return null;
    }

    public static BooleanStatementOptional BOOL(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new BooleanStatementOptional(OptionalValue.from(element.getAsBoolean()));
        }
        catch (Exception ignored)
        {

        }

        return new BooleanStatementOptional(OptionalValue.from(element.getAsString(), ResultTest.BOOLEAN));
    }

    public static MobEffectHolderStatementOptional MOB_EFFECT_HOLDER(JsonElement element)
    {
        if (!element.isJsonPrimitive())
        {
            return null;
        }

        String elementString = element.getAsString();

        return MOB_EFFECT_HOLDER(elementString);
    }

    public static MobEffectHolderStatementOptional MOB_EFFECT_HOLDER(String string)
    {
        // No fuckign clue what's happening here but IntelliJ context actions says it's better than my version so...

        Optional<Holder<MobEffect>> holderOptional = Constants.server.registryAccess().registry(Registries.MOB_EFFECT).flatMap(registry -> registry.getHolder(ResourceLocation.parse(string)));
        return holderOptional.map(mobEffectHolder -> new MobEffectHolderStatementOptional(OptionalValue.from(mobEffectHolder))).orElseGet(() -> new MobEffectHolderStatementOptional(OptionalValue.from(string, ResultTest.MOB_EFFECT)));
    }
}
