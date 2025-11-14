package net.tazgirl.magicjson.main.function_object.objects.returns;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.main.addresses.StatementAddress;
import net.tazgirl.magicjson.main.function_object.FunctionStack;
import net.tazgirl.magicjson.main.function_object.objects.BaseFunctionObject;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class BaseReturnFunctionObject<T extends Class<?>> extends BaseFunctionObject
{

    public StatementAddress returnValue;

    public BaseReturnFunctionObject()
    {
        contents = Map.of(
                "returnValue", JsonObjectTypes.STATEMENT
        );
    }


    @Override
    public Object Run()
    {
        Object statementResult = MagicJson.RunStatement(manager.generateStatementManager(returnValue));

        if(statementResult != null)
        {
            manager.Return(statementResult, this);
            return statementResult;
        }

        return null;
    }

    @Override
    public @NotNull Boolean HandleObject(Object object, String key, FunctionStack stack)
    {
        if(object instanceof String string)
        {
            returnValue = StatementAddress.from(string);
            return true;
        }
        else if(object instanceof StatementAddress address)
        {
            returnValue = address;
            return true;
        }

        return false;
    }

    @Override
    public @NotNull Boolean HandleElement(JsonElement element, String name, FunctionStack stack)
    {
        if(element instanceof JsonPrimitive primitive)
        {
            returnValue = StatementAddress.from(primitive.getAsString());

            return true;
        }

        return false;
    }
}
