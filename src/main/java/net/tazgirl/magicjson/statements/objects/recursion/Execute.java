package net.tazgirl.magicjson.statements.objects.recursion;

import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.helpers.EnumAliaseGetter;
import net.tazgirl.magicjson.statements.hooks.base.HookArgument;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.primitives.StringObject;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Execute extends Base
{
    Base address;
    List<HookArgument> arguments = new ArrayList<>();

    boolean argPass = true;
    boolean argSync = true;

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
        if(address.resolve() instanceof String string && MagicJson.statementExists(string))
        {
            if(argPass)
            {
                return MagicJson.runStatement(string, holder.args);
            }

            return MagicJson.runStatement(string);
        }

        return null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        if(base instanceof HookArgument hookArgument)
        {
            arguments.add(hookArgument);
            return true;
        }
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
            argPass = false;
            return true;
        }

        return false;
    }

    @Override
    public Base implicitChild()
    {
        return new HookArgument(holder);
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
