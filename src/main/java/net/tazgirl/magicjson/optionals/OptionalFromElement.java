package net.tazgirl.magicjson.optionals;

import com.google.gson.JsonElement;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.minecraft_types.MobEffectHolderStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.DoubleStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.FloatStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.IntegerStatementOptional;
import net.tazgirl.magicjson.optionals.numbers.LongStatementOptional;

import java.util.Objects;

public class OptionalFromElement
{
//    public static <T> Object get(JsonElement element, Class<T> checkClass)
//    {
//        if(checkClass == Integer.class)
//        {
//            return INT(element, 0);
//        }
//        if(checkClass == Float.class)
//        {
//            return FLOAT(element, 0f);
//        }
//        if(checkClass == Double.class)
//        {
//            return DOUBLE(element, 0.0);
//        }
//        if(checkClass == Long.class)
//        {
//            return LONG(element, 0L);
//        }
//        if(checkClass == String.class || checkClass == CharSequence.class)
//        {
//            return STRING(element, "");
//        }
//        if(checkClass == MobEffect.class)
//        {
//            return MOB_EFFECT_HOLDER(element, MobEffects.DAMAGE_BOOST);
//        }
//
//
//        return null;
//    }


    public static IntegerStatementOptional INT(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new IntegerStatementOptional(element.getAsInt(), 0);
        }
        catch (Exception ignored)
        {

        }

        return new IntegerStatementOptional(element.getAsString(), 0);
    }

    public static FloatStatementOptional FLOAT(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new FloatStatementOptional(element.getAsFloat(), 0f);
        }
        catch (Exception ignored)
        {

        }

        return new FloatStatementOptional(element.getAsString(), 0f);
    }

    public static DoubleStatementOptional DOUBLE(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new DoubleStatementOptional(element.getAsDouble(), 0.0);
        }
        catch (Exception ignored)
        {

        }

        return new DoubleStatementOptional(element.getAsString(), 0.0);
    }

    public static LongStatementOptional LONG(JsonElement element)
    {
        if(!element.isJsonPrimitive())
        {
            return null;
        }

        try
        {
            return new LongStatementOptional(element.getAsLong(), 0L);
        }
        catch (Exception ignored)
        {

        }

        return new LongStatementOptional(element.getAsString(), 0L);
    }

    public static StringStatementOptional STRING(JsonElement element)
    {
        try
        {
            return new StringStatementOptional(element.getAsString(), "");
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

        if(PrivateCore.hasStatement(elementString))
        {
            return new MobEffectHolderStatementOptional(elementString, MobEffects.DAMAGE_BOOST);
        }

        if(BuiltInRegistries.MOB_EFFECT.get(ResourceLocation.parse(elementString)) instanceof MobEffect mobEffect)
        {
            return new MobEffectHolderStatementOptional(Holder.direct(mobEffect), MobEffects.DAMAGE_BOOST);
        }

        return null;
    }


}
