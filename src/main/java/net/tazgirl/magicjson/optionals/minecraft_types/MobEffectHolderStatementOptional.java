package net.tazgirl.magicjson.optionals.minecraft_types;

import com.mojang.datafixers.util.Either;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.OptionalValue;
import net.tazgirl.magicjson.optionals.StatementOptional;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MobEffectHolderStatementOptional extends StatementOptional<Holder<MobEffect>> implements Holder<MobEffect>
{
    public MobEffectHolderStatementOptional(OptionalValue<Holder<MobEffect>> optionalValue)
    {
        super(optionalValue);
    }

    public static MobEffectHolderStatementOptional from(Holder<MobEffect> value)
    {
        return new MobEffectHolderStatementOptional(OptionalValue.from(value));
    }

    @Override
    public Holder<MobEffect> get()
    {
        return getWithArgs();
    }

    @Override
    public Holder<MobEffect> getWithArgs(Object... args)
    {
        if(optionalValue.isPlain() || args == null || args.length == 0)
        {
            return optionalValue.get();
        }

        Map<String, Object> argMap = new HashMap<>();
        for(int i = 0; i < args.length; i++)
        {
            argMap.put("arg" + i, args[i]);
        }

        return getWithArgs(argMap);
    }

    @Override
    public Holder<MobEffect> getWithArgs(Map<String, Object> args)
    {
        if(optionalValue.isPlain())
        {
            return optionalValue.get();
        }

        String address = optionalValue.getAddress();

        Object result = MagicJson.runStatement(address, args);

        if(result instanceof Holder<?> holder && holder.value() instanceof MobEffect)
        {
            //noinspection unchecked
            return (Holder<MobEffect>) holder;
        }
        if(result instanceof String string)
        {
            return Constants.server.registryAccess().registry(Registries.MOB_EFFECT).get().getHolder(ResourceLocation.parse(string)).get();
        }

        return null;
    }

    @Override
    public OptionalValue<Holder<MobEffect>> getOptional()
    {
        return null;
    }

    @Override
    public Object getRaw()
    {
        return optionalValue;
    }

    @Override
    public @NotNull MobEffect value()
    {
        return get().value();
    }

    @Override
    public boolean isBound()
    {
        return get().isBound();
    }

    @Override
    public boolean is(@NotNull ResourceLocation resourceLocation)
    {
        return get().is(resourceLocation);
    }

    @Override
    public boolean is(@NotNull ResourceKey<MobEffect> resourceKey)
    {
        return get().is(resourceKey);
    }

    @Override
    public boolean is(@NotNull Predicate<ResourceKey<MobEffect>> predicate)
    {
        return get().is(predicate);
    }

    // What does this mean, I just want my polymorphism. Give me my polymorphism you smelly nerds.
    @Override
    public boolean is(@NotNull TagKey<MobEffect> tagKey)
    {
        return get().is(tagKey);
    }

    @Override
    public boolean is(@NotNull Holder<MobEffect> holder)
    {
        return get().is(holder);
    }

    @Override
    public @NotNull Stream<TagKey<MobEffect>> tags()
    {
        return get().tags();
    }

    @Override
    public @NotNull Either<ResourceKey<MobEffect>, MobEffect> unwrap()
    {
        return get().unwrap();
    }

    @Override
    public @NotNull Optional<ResourceKey<MobEffect>> unwrapKey()
    {
        return get().unwrapKey();
    }

    @Override
    public @NotNull Kind kind()
    {
        return get().kind();
    }

    @Override
    public boolean canSerializeIn(@NotNull HolderOwner<MobEffect> holderOwner)
    {
        return get().canSerializeIn(holderOwner);
    }
}
