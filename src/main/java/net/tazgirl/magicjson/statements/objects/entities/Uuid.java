package net.tazgirl.magicjson.statements.objects.entities;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.helpers.UAtoEnum;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class Uuid extends Base
{

    Function function = Function.TOENTITY;

    Base uuidBase;

    public Uuid(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        switch (function)
        {
            case TOENTITY ->
            {
                if(!(uuidBase.Resolve() instanceof String string)){return null;}

                UUID uuid;

                try
                {
                    uuid = UUID.fromString(string);
                }
                catch (IllegalArgumentException ignored)
                {
                    return null;
                }

                for(ServerLevel level : Constants.server.getAllLevels())
                {
                    Entity entity = level.getEntity(uuid);

                    if(entity != null)
                    {
                        return entity;
                    }
                }

                return null;
            }
            case VALID ->
            {
                if(!(uuidBase.Resolve() instanceof String string)){return false;}

                try
                {
                    UUID.fromString(string);
                    return true;
                }
                catch (IllegalArgumentException e)
                {
                    return false;
                }

            }
            case FROM ->
            {
                if(!(uuidBase.Resolve() instanceof String string)){return null;}

                try
                {
                    return UUID.fromString(string);
                }
                catch (IllegalArgumentException ignored)
                {
                    return null;
                }
            }
            case FROMENTITY ->
            {
                if(!(uuidBase.Resolve() instanceof Entity entity)){return null;}

                return entity.getUUID();
            }
        }

        return null;
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        if(uuidBase == null)
        {
            uuidBase = base;
            return true;
        }

        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        function = UAtoEnum.get(Function.class, string);

        return function == null;
    }

    @Override
    public Base ImplicitChild()
    {
        return null;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Uuid";
    }

    @Override
    public String toString()
    {
        return identifier + "." + function.toString().toLowerCase() + "( " + uuidBase + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        if(uuidBase == oldBase)
        {
            uuidBase = newBase;
        }
    }

    enum Function
    {
        TOENTITY, // Accepts String, returns Entity
        VALID, // Accepts String, returns Boolean
        FROMENTITY, // Accepts Entity, returns UUID
        FROM // Accepts String, returns UUID
    }
}
