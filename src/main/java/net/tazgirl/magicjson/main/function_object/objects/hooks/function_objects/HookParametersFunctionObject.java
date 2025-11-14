package net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects;

import com.google.gson.JsonElement;
import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.main.addresses.Address;
import net.tazgirl.magicjson.main.addresses.FunctionAddress;
import net.tazgirl.magicjson.main.addresses.StatementAddress;
import net.tazgirl.magicjson.main.function_object.FunctionManager;
import net.tazgirl.magicjson.main.function_object.FunctionStack;
import net.tazgirl.magicjson.main.function_object.objects.BaseFunctionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class HookParametersFunctionObject extends BaseFunctionObject
{

    FunctionManager manager;

    Map<String, Address> parameters = new HashMap<>();

    public HookParametersFunctionObject(FunctionManager manager)
    {
        this.manager = manager;
    }

    public void PutParameter(String parameterName,Address parameterAddress)
    {
        parameters.put(parameterName, parameterAddress);
    }


    // Takes in name of parameter
    // If the name corresponds to a StatementAddress, fetch it, generate a new StatementManager and return its Resolve value
    // If the name corresponds to a FunctionHolder, fetch it and run it through with a fresh manager made with this ones args and then return the final return value
    public Object get(String name)
    {
        Object parameterAddress = parameters.get(name);

        if(parameterAddress instanceof StatementAddress statementAddress)
        {
            return MagicJson.RunStatement(manager.generateStatementManager(statementAddress));
        }
        else if(parameterAddress instanceof FunctionAddress functionAddress)
        {
            return MagicJson.RunFunction(manager.copyWithNewAddress(functionAddress));
        }

        Logging.Debug("Parameter \"" + name + "\" was requested but does not exist in the parameter set: " + parameters.toString() +
                "\nNull has been returned and issues may occur", false);

        // This method is only called by Java classes, not MagicJSON objects
        // As such null handling is expected by the caller
        return null;
    }


    @Override
    public Object Run()
    {
        return this;
    }


    @Override
    public @NotNull Boolean HandleObject(Object content, String key, FunctionStack stack)
    {
        if(content instanceof HookFunctionParameter(String parameterName, Address statementAddress))
        {
            parameters.put(parameterName, statementAddress);
        }

        return false;
    }

    @Override
    public @NotNull Boolean HandleElement(JsonElement element, @Nullable String elementName, @Nullable FunctionStack stack)
    {
        return false;
    }
}
