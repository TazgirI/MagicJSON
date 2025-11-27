package net.tazgirl.magicjson.old.main.hook_object;

import net.tazgirl.magicjson.old.main.statement_object.StatementManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HookParameters
{
    List<HookParameter> parameters = new ArrayList<>();

    public HookParameters()
    {

    }

    public HookParameters(List<HookParameter> parameters)
    {
        this.parameters = parameters;
    }

    public void putParameter(String name, Object value)
    {
        parameters.add(new HookParameter(name, value));
    }

    public void putParameter(HookParameter parameter)
    {
        parameters.add(parameter);
    }

    // Fetches the intended value for a given paramater name, should be used more than getTrueParameter
    protected Object getOrRunParameter(String name, StatementManager manager)
    {
        for(HookParameter parameter: parameters)
        {
            if(parameter.name().equals(name))
            {
                return parameter.fetchValue(manager);
            }
        }

        return null;
    }

    // Fetches the value without attempting to run it if it matches an Address
    // Should only be used if you believe your hook will be passed String literals corresponding to Addresses
    protected Object getTrueParameter(String name)
    {
        for(HookParameter parameter: parameters)
        {
            if(parameter.name().equals(name))
            {
                return parameter.fetchValueTrue();
            }
        }

        return null;
    }


    protected Object getOrRunParameter(String name, Map<String, Object> args)
    {
        for(HookParameter parameter: parameters)
        {
            if(parameter.name().equals(name))
            {
                return parameter.fetchValue(new StatementManager(args));
            }
        }

        return null;
    }



}
