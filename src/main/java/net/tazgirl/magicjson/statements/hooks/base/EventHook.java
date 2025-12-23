package net.tazgirl.magicjson.statements.hooks.base;

import net.neoforged.bus.api.Event;
import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class EventHook extends Base
{
    protected String function;

    protected List<HookArgument> arguments = new ArrayList<>();
    
    public EventHook(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public Object Resolve()
    {
        Map<String, Base> hookArguments = new HashMap<>();

        if(holder.args.get(Constants.eventParamName) instanceof Event event)
        {
            hookArguments.put(Constants.eventParamName, new EventPrimitive(holder, event));
        }

        for(HookArgument argument: arguments)
        {
            if(argument.Resolve() instanceof Map.Entry<String, Base> entry)
            {
                hookArguments.put(entry.getKey(), entry.getValue());
            }
            else
            {
                Logging.Debug("HookArguments name Object did not resolve to a String " + argument + "   within: " + holder.getAddress());
            }
        }

        return RunHook(hookArguments);
    }

    @Override
    public @NotNull Boolean HandleBase(Base base)
    {
        if(base instanceof HookArgument argument)
        {
            arguments.add(argument);
            return true;
        }
        DebugUnHandledType(base);
        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        if(function == null)
        {
            function = string;
            return true;
        }
        
        return false;
    }

    @Override
    public Base ImplicitChild()
    {
        return new HookArgument(holder);
    }

    @Override
    public String toString()
    {
        return identifier + function + "( " + arguments + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {
        if(oldBase instanceof HookArgument && newBase instanceof HookArgument)
        {
            if(arguments.contains(oldBase))
            {
                arguments.add(arguments.indexOf(oldBase), (HookArgument) newBase);
            }
        }
    }
    
    public abstract Object RunHook(Map<String, Base> hookArguments);
}
