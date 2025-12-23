package net.tazgirl.magicjson.statements.objects.memory;

import net.tazgirl.magicjson.helpers.EnumAliaseGetter;
import net.tazgirl.magicjson.helpers.UAtoEnum;
import net.tazgirl.magicjson.memory.Memory;
import net.tazgirl.magicjson.memory.WorldMemory;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;

public class MemoryObject extends Base
{
    Function function = Function.GET;
    Namespace namespace = Namespace.LOCAL;
    Space space = Space.RUNTIME;

    Base address;

    Base optionalValue;

    public MemoryObject(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        return switch (function)
        {
            case GET ->
            {
                Object addressResult = address.Resolve();
                if(addressResult instanceof String string)
                {
                    if(namespace == Namespace.LOCAL)
                    {
                        string = holder.getAddress().substring(0, string.indexOf(":") + 1) + string;
                    }

                    yield space.supplier.get().get(string);
                }
                yield null;
            }
            case PUT ->
            {
                Object addressResult = address.Resolve();
                if(addressResult instanceof String string)
                {
                    if(namespace == Namespace.LOCAL)
                    {
                        string = holder.getAddress().substring(0, string.indexOf(":") + 1) + string;
                    }

                    yield space.supplier.get().put(string, optionalValue.Resolve());
                }

                yield null;
            }
            case VALID ->
            {
                Object addressResult = address.Resolve();
                if(addressResult instanceof String)
                {
                    yield WorldMemory.isTypeAcceptable(optionalValue.Resolve());
                }

                yield false;
            }
        };
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        if(address == null)
        {
            address = base;
            return true;
        }
        if(optionalValue == null)
        {
            optionalValue = base;
            return true;
        }

        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        if(UAtoEnum.get(Function.class, string) instanceof Function func)
        {
            function = func;
            return true;
        }
        if(UAtoEnum.get(Namespace.class, string) instanceof Namespace name)
        {
            namespace = name;
            return true;
        }
        if(UAtoEnum.get(Space.class, string) instanceof Space spc)
        {
            space = spc;
            return true;
        }

        return false;
    }

    @Override
    public Base ImplicitChild()
    {
        return null;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Memory";
    }

    @Override
    public String toString()
    {
        return identifier + "." + function.toString().toLowerCase() + "( " + address + " " + optionalValue + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        if(address == oldBase)
        {
            address = newBase;
        }
        else if(optionalValue == oldBase)
        {
            optionalValue = newBase;
        }
    }

    enum Function
    {
        GET,
        PUT,
        VALID
    }

    enum Namespace implements EnumAliaseGetter<Namespace>
    {
        LOCAL("l","name","namespace", "private"),
        GLOBAL("g","public");

        final String[] aliasses;


        Namespace(String... aliasses)
        {
            this.aliasses = aliasses;
        }

        @Override
        public String[] aliasses()
        {
            return aliasses;
        }

        @Override
        public Class<Namespace> myClass()
        {
            return Namespace.class;
        }
    }

    enum Space implements EnumAliaseGetter<Space>
    {
        RUNTIME(Memory::getRuntimeMemory,"temp", "run", "r"),
        WORLD(Memory::getWorldMemoryMap,"perm", "w");

        final String[] aliasses;
        final Supplier<Map<String, Object>> supplier;

        Space(Supplier<Map<String, Object>> supplier, String... aliasses)
        {
            this.supplier = supplier;
            this.aliasses = aliasses;
        }

        @Override
        public String[] aliasses()
        {
            return aliasses;
        }

        @Override
        public Class<Space> myClass()
        {
            return Space.class;
        }
    }
}
