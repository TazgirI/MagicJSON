package net.tazgirl.magicjson.statements.hooks.base;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class Hook extends Base
{
    protected List<HookArgument> arguments = new ArrayList<>();
    protected Map<String, Base> referenceArguments = new HashMap<>();
    protected static Class<? extends Hook> myClassStatic;

    public Hook(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        for(HookArgument argument: arguments)
        {
            if(argument.Resolve() instanceof Map.Entry<String, Base> entry)
            {
                referenceArguments.put(entry.getKey(), entry.getValue());
            }
            else
            {
                Logging.Debug("HookArguments name Object did not resolve to a String " + argument + "   within: " + holder.getAddress());
            }
        }

        return RunHook(referenceArguments);
    }

    public abstract Object RunHook(Map<String, Base> hookArgs);

    @Override
    public Boolean HandleBase(Base base)
    {
        if(base instanceof HookArgument argument)
        {
            arguments.add(argument);
            return true;
        }

        DebugUnHandledType(base.getClass());
        return false;
    }

    @Override
    public Base ImplicitChild()
    {
        return new HookArgument(holder);
    }

    @Override
    // End bracket is to prevent accidental token matching as it was easier to add an impossible token char than an if statement to the event
    public @NotNull String setIdentifier()
    {
        return "Hook)";
    }

    @Override
    public String toString()
    {
        StringBuilder argsString = new StringBuilder();


        for(HookArgument argument: arguments)
        {
            argsString.append(argument.toString()).append(" ");
        }

        return identifier + "( " + argsString.toString() + ")";
    }

}
