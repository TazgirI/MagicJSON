package net.tazgirl.magicjson.optionals.tests;

import com.mojang.datafixers.types.Func;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Function;

public record ResultTest<T>(Class<T> type, Function<Object, Boolean> advancedTest)
{
    public static final ResultTest<Integer> INTEGER = new ResultTest<>(Integer.class);
    public static final ResultTest<Double> DOUBLE = new ResultTest<>(Double.class);
    public static final ResultTest<Float> FLOAT = new ResultTest<>(Float.class);
    public static final ResultTest<Long> LONG = new ResultTest<>(Long.class);
    public static final ResultTest<Boolean> BOOLEAN = new ResultTest<>(Boolean.class);
    public static final ResultTest<String> STRING = new ResultTest<>(String.class);
    public static final ResultTest<Holder<MobEffect>> MOB_EFFECT = new ResultTest<>(object -> object instanceof Holder<?> holder && holder.value() instanceof MobEffect);

    public ResultTest
    {
        if(type == null && advancedTest == null)
        {
            throw new IllegalArgumentException("ResultTest has a null type and a null advancedTest, at least one must not be null");
        }
    }

    public ResultTest(@NotNull Function<Object, Boolean> advancedTest)
    {
        this(null, advancedTest);
    }

    public ResultTest(@NotNull Class<T> type)
    {
        this(type, null);
    }

    public boolean test(Object input)
    {
        if(advancedTest == null)
        {
            return type.isInstance(input);
        }

        return advancedTest.apply(input);
    }

}
