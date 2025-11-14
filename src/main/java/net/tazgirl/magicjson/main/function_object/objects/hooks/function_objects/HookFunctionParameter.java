package net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects;

import net.tazgirl.magicjson.main.addresses.Address;

import java.util.Map;

public record HookFunctionParameter(String parameterName, Address statementAddress)
{

    public Map.Entry<String, Address> toEntry()
    {
        return Map.entry(parameterName, statementAddress);
    }

}
