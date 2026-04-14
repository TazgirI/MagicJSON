package net.tazgirl.magicjson;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.OptionalValue;
import net.tazgirl.magicjson.optionals.numbers.DoubleStatementOptional;
import net.tazgirl.magicjson.optionals.tests.ResultTest;

import java.util.function.Function;

public abstract class MagicJsonConfigValue<T, E extends IStatementOptional<T>>
{
    public MagicJsonConfigValue(ModConfigSpec.ConfigValue<?> configValue)
    {
        this.configValue = configValue;
        MagicJson.MOD_EVENT_BUS.addListener(ModConfigEvent.Loading.class, this::onConfigLoad);
        MagicJson.MOD_EVENT_BUS.addListener(ModConfigEvent.Reloading.class, this::onConfigReload);
    }

    private boolean dirty = true;

    private final ModConfigSpec.ConfigValue<?> configValue;
    private final ResultTest<T> test = getTest();
    private final Function<OptionalValue<T>, E> optionalBuilder = getBuilder();

    private E optional;

    protected abstract ResultTest<T> getTest();
    protected abstract Function<OptionalValue<T>, E> getBuilder();


    public T get()
    {
        if(dirty)
        {
            optional = optionalBuilder.apply(
                    OptionalValue.from(configValue.get(), test));
            dirty = false;
        }

        return optional.get();
    }


    public static class DoubleValue extends MagicJsonConfigValue<Double, DoubleStatementOptional>
    {
        public DoubleValue(ModConfigSpec.ConfigValue<?> config)
        {
            super(config);
        }

        @Override
        protected ResultTest<Double> getTest()
        {
            return ResultTest.DOUBLE;
        }

        @Override
        protected Function<OptionalValue<Double>, DoubleStatementOptional> getBuilder()
        {
            return DoubleStatementOptional::new;
        }

    }

    private void onConfigLoad(ModConfigEvent.Loading event)
    {
        setDirty();
    }

    private void onConfigReload(ModConfigEvent.Reloading event)
    {
        setDirty();
    }

    private void setDirty()
    {
        dirty = true;
    }
}
