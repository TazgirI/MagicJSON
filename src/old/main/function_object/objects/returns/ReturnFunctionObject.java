package net.tazgirl.magicjson.old.main.function_object.objects.returns;

import com.google.gson.JsonElement;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.old.main.addresses.StatementAddress;
import net.tazgirl.magicjson.old.main.function_object.FunctionStack;
import net.tazgirl.magicjson.old.main.function_object.objects.BaseFunctionObject;
import org.jetbrains.annotations.NotNull;

public abstract class ReturnFunctionObject extends BaseFunctionObject
{

    public StatementAddress returnValue;


    public ReturnFunctionObject()
    {
        identifier = "Return";
    }

    @Override
    public Object RunPersonal()
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
        return switch(name)
        {
            case "returnValue" ->
            {
                if(ElementAsStatementAddress(element) instanceof StatementAddress address)
                {
                    returnValue = address;
                    yield true;
                }
                LogConversionFailedForRecognisedKey(element, StatementAddress.class, name);
                yield false;
            }
            case null, default ->
            {
                if(CheckUnknownElementIsStatementAddress(element, identifier, name) instanceof StatementAddress statementAddress)
                {
                    returnValue = statementAddress;
                    yield true;
                }
                LogConversionFailedForUnknownName(element, element.getClass(), name);
                yield false;
            }
        };
    }
}
