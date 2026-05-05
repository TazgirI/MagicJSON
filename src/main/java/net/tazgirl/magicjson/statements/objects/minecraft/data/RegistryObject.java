package net.tazgirl.magicjson.statements.objects.minecraft.data;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.optionals.OptionalFrom;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class RegistryObject extends Base
{
    ResourceKey<? extends Registry<?>> resourceKey;
    Base location;

    public RegistryObject(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object resolve()
    {
        Object object = location.resolve();
        ResourceLocation resourceLocation;

        if(object instanceof ResourceLocation)
        {
            resourceLocation = (ResourceLocation) object;
        }
        else if(object instanceof String)
        {
            resourceLocation = ResourceLocation.parse((String) object);
        }
        else
        {
            return null;
        }

        Optional<Registry<Object>> optional = Constants.server.registryAccess().registry(resourceKey);

        if(optional.isPresent())
        {
            return optional.get().get(resourceLocation);
        }

        return null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        location = base;

        return true;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        ResourceKey<? extends Registry<?>> key = RegistersForProcessing.stringToResourceKey.get(string);

        if(key != null)
        {
            resourceKey = key;
            return true;
        }

        return false;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Registry";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + resourceKey + ", " + location + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {
        if(location == oldBase)
        {
            location = newBase;
        }
    }
}
