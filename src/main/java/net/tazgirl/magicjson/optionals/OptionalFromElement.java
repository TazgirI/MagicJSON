package net.tazgirl.magicjson.optionals;

import com.google.gson.JsonElement;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.optionals.minecraft_types.MobEffectHolderStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.DoubleStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.FloatStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.IntegerStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.LongStatementOptional;
import net.tazgirl.magicjson.optionals.tests.ResultTest;

import java.util.Objects;

public class OptionalFromElement
{
    public static IntegerStatementOptional INT(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new IntegerStatementOptional(OptionalValue.from(element.getAsInt()), 0);
        }
        catch (Exception ignored)
        {

        }

        return new IntegerStatementOptional(OptionalValue.from(element.getAsString(), ResultTest.INTEGER), 0);
    }

    public static FloatStatementOptional FLOAT(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new FloatStatementOptional(OptionalValue.from(element.getAsFloat()), 0f);
        }
        catch (Exception ignored)
        {

        }

        return new FloatStatementOptional(OptionalValue.from(element.getAsString(), ResultTest.FLOAT), 0f);
    }

    public static DoubleStatementOptional DOUBLE(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new DoubleStatementOptional(OptionalValue.from(element.getAsDouble()), 0.0);
        }
        catch (Exception ignored)
        {

        }

        return new DoubleStatementOptional(OptionalValue.from(element.getAsString(), ResultTest.DOUBLE), 0.0);
    }

    public static LongStatementOptional LONG(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new LongStatementOptional(OptionalValue.from(element.getAsLong()), 0L);
        }
        catch (Exception ignored)
        {

        }

        return new LongStatementOptional(OptionalValue.from(element.getAsString(), ResultTest.LONG), 0L);
    }

    public static StringStatementOptional STRING(JsonElement element)
    {
        try
        {
            return new StringStatementOptional(OptionalValue.from(element.getAsString()), "");
        }
        catch (Exception ignored)
        {

        }

        return null;
    }

    public static MobEffectHolderStatementOptional MOB_EFFECT_HOLDER(JsonElement element)
    {
        String elementString = null;

        try
        {
            elementString = element.getAsString();
        }
        catch (Exception ignored)
        {

        }

        if(elementString == null)
        {
            return null;
        }

        return new MobEffectHolderStatementOptional(elementString, MobEffects.NIGHT_VISION);
    }


    public static IStatementOptional<?> BOOL(JsonElement element)
    {
    }
}
