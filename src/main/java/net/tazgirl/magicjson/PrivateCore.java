package net.tazgirl.magicjson;

import net.tazgirl.magicjson.main.addresses.FunctionAddress;
import net.tazgirl.magicjson.main.addresses.StatementAddress;
import net.tazgirl.magicjson.main.function_object.SourceFunctionHolder;
import net.tazgirl.magicjson.main.hook_object.Hook;
import net.tazgirl.magicjson.main.hook_object.HookParameters;
import net.tazgirl.magicjson.main.hook_object.VoidHook;
import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;

import java.util.HashMap;
import java.util.Map;

public class PrivateCore
{
    private static final Map<String, BaseStatementObject> statementMap = new HashMap<>();
    private static final Map<String, SourceFunctionHolder> functionMap = new HashMap<>();
    public static Map<String, Hook<?>> registeredHooks = new HashMap<>();


    public static Map<String, BaseStatementObject> getStatementCopy()
    {
        return new HashMap<>(statementMap);
    }

    public static Map<String, SourceFunctionHolder> getFunctionCopy()
    {
        return new HashMap<>(functionMap);
    }



    public static BaseStatementObject getStatement(String address)
    {
        return statementMap.get(address);
    }

    public static BaseStatementObject getStatement(StatementAddress address)
    {
        return statementMap.get(address.getLocalAddress());
    }

    public static Boolean hasStatement(String attemptString)
    {
        return statementMap.containsKey(attemptString);
    }

    public static SourceFunctionHolder getFunction(String address)
    {
        return functionMap.get(address);
    }

    public static SourceFunctionHolder getFunction(FunctionAddress address)
    {
        return functionMap.get(address.getLocalAddress());
    }

    public static Boolean hasFunction(String attemptString)
    {
        return functionMap.containsKey(attemptString);
    }

    protected static void addStatement(String statementAddress, BaseStatementObject statementObject)
    {
        statementMap.put(statementAddress, statementObject);
    }

    protected static void addFunction(String functionAddress, SourceFunctionHolder functionObject)
    {
        functionMap.put(functionAddress, functionObject);
    }


    public static Hook<?> getHook(String name)
    {
        return registeredHooks.get(name);
    }

    public static Boolean runVoidHook(String name, HookParameters parameters)
    {
        if(registeredHooks.get(name) instanceof VoidHook hook)
        {
            hook.RunHook(parameters);
            return true;
        }

        return false;
    }
}
