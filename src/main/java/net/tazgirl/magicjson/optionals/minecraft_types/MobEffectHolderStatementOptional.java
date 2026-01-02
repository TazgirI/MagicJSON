package net.tazgirl.magicjson.optionals.minecraft_types;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.optionals.IStatementOptional;
import net.tazgirl.magicjson.optionals.StringStatementOptional;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MobEffectHolderStatementOptional implements IStatementOptional<Holder<MobEffect>>, Holder<MobEffect>
{
    public Object value;
    public Holder<MobEffect> defaultValue;

    public MobEffectHolderStatementOptional(Object value, @NotNull Holder<MobEffect> defaultValue)
    {
        this.value = value;
        if(value instanceof Holder<?> holder)
        {
            this.value = holder.value();
        }
        this.defaultValue = defaultValue;
    }

    public static MobEffectHolderStatementOptional from(MobEffect value)
    {
        return new MobEffectHolderStatementOptional(value, MobEffects.DAMAGE_BOOST);
    }

    @Override
    public Holder<MobEffect> get()
    {
        if(value instanceof MobEffect mobEffect)
        {
            return Holder.direct(mobEffect);
        }

        if(value instanceof String string)
        {
            Object result = PrivateCore.runStatement(string);

            if(result instanceof Holder<?> holder && holder.value() instanceof MobEffect)
            {
                return (Holder<MobEffect>) holder;
            }

            ResourceKey<MobEffect> resourceKey = ResourceKey.create(BuiltInRegistries.MOB_EFFECT.key(), ResourceLocation.parse(string));

            if(BuiltInRegistries.MOB_EFFECT.containsKey(resourceKey))
            {
                return BuiltInRegistries.MOB_EFFECT.getHolderOrThrow(resourceKey);
            }
        }

        return defaultValue;
    }

    @Override
    public Object getRaw()
    {
        return value;
    }


    @Override
    public String toString()
    {
        return get().toString();
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
