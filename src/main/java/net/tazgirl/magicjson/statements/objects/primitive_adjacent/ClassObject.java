package net.tazgirl.magicjson.statements.objects.primitive_adjacent;

import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.registration.RegistersForProcessing;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

public class ClassObject extends Base
{
    String argument = "";

    Base classSource;
    boolean readClassSourceString = false;

    public ClassObject(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Class<?> resolve()
    {
        if(argument.isEmpty())
        {
            Object result = classSource.resolve();
            if(readClassSourceString)
            {
                try
                {
                    return Class.forName(result.toString());
                }
                catch (ClassNotFoundException e)
                {
                    MJLogging.debug("Couldn't find class of name matching result: \"" + result + "\". Returning result.getClass() instead");
                    return result.getClass();
                }
            }

            return result.getClass();
        }

        return RegistersForProcessing.stringToClass.get(argument);
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        classSource = base;
        return true;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        if(string.equals("readstring"))
        {
            readClassSourceString = true;
        }
        else
        {
            argument = string;
        }

        return true;
    }

    @Override
    public Base implicitChild()
    {
        return null;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "ClassObject";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + argument + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {

    }
}
