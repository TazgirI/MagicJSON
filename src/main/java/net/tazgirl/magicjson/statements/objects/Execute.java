package net.tazgirl.magicjson.statements.objects;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.helpers.EnumAliaseGetter;
import net.tazgirl.magicjson.statements.objects.primitives.StringObject;
import org.jetbrains.annotations.NotNull;

public class Execute extends Base
{
    Base address;

    boolean noPass = false;

    public Execute(StatementHolder holder)
    {
        super(holder);
    }

    public Execute(StatementHolder holder, String string)
    {
        super(holder);
        this.handleBase(new StringObject(holder, string));
    }

    @Override
    public Object resolve()
    {
        if(address.resolve() instanceof String string && PrivateCore.hasStatement(string))
        {
            if(!noPass)
            {
                return PrivateCore.runStatement(string, holder.args);
            }

            return PrivateCore.runStatement(string);
        }

        return null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        if(address == null)
        {
            address = base;
            return true;
        }

        return false;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        if(string.equals(".!pass") || string.equals(".nopass"))
        {
            noPass = true;
            return true;
        }

        return false;
    }

    @Override
    public Base implicitChild()
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
    public void replace(Base oldBase, Base newBase)
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
