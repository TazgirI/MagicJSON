package net.tazgirl.magicjson.statements.objects;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.helpers.EnumAliaseGetter;
import org.jetbrains.annotations.NotNull;

public class Execute extends Base
{
    Base address;

    boolean nopass = false;

    public Execute(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        if(address.Resolve() instanceof String string && PrivateCore.hasStatement(string))
        {
            if(!nopass)
            {
                return PrivateCore.runStatement(string, holder.args);
            }

            return PrivateCore.runStatement(string);
        }

        return null;
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        if(address == null)
        {
            address = base;
            return true;
        }

        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        if(string.equals(".!pass") || string.equals(".nopass"))
        {
            nopass = true;
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
        return "Execute";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + address + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        if(address == oldBase)
        {
            address = newBase;
        }
    }


    // Un-implimented as was used to develop the EnumGetter interface but doesn't need an Enum itself
    enum Arguments implements EnumAliaseGetter<Arguments>
    {
        NO_PASS("!pass", "nopass");

        final String[] aliasses;

        Arguments(String... aliasses)
        {
            this.aliasses = aliasses;
        }

        @Override
        public String[] aliasses()
        {
            return aliasses;
        }

        @Override
        public Class<Arguments> myClass()
        {
            return Arguments.class;
        }
    }
}
