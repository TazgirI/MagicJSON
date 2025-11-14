package net.tazgirl.magicjson.main.function_object.objects.flow;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.MagicJson;
import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.main.addresses.StatementAddress;
import net.tazgirl.magicjson.main.function_object.FunctionManager;
import net.tazgirl.magicjson.main.function_object.FunctionStack;
import net.tazgirl.magicjson.main.function_object.JsonToFunctionObject;
import net.tazgirl.magicjson.main.function_object.SourceFunctionHolder;
import net.tazgirl.magicjson.main.function_object.objects.BaseFunctionObject;
import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesBoolean;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class IfFunctionObject extends BaseFunctionObject
{
    StatementAddress checkStatement = null;

    SourceFunctionHolder trueObject = null;

    SourceFunctionHolder falseObject = null;

    public IfFunctionObject()
    {
        identifier = "If";
    }


    @Override
    public Boolean RunPersonal()
    {
        if(checkStatement == null)
        {
            Logging.Debug("If function is missing it's check statement " + getDebugString(), false);
        }

        if(!(PrivateCore.getStatement(checkStatement) instanceof ResolvesBoolean))
        {
            Logging.Debug("If function has non-boolean Statement as it's check" + getDebugString(), false);
            return null;
        }

        if(MagicJson.RunStatement(manager.generateStatementManager(checkStatement)) instanceof Boolean bool && bool)
        {
            if(trueObject != null){MagicJson.RunFunction(trueObject, manager);}

            return true;
        }
        else
        {
            if(falseObject != null){MagicJson.RunFunction(falseObject, manager);}

            return false;
        }

    }

    @Override
    public void SpreadArgs(FunctionManager newManager)
    {
        super.SpreadArgs(newManager);

        trueObject.SpreadManager(newManager);
        falseObject.SpreadManager(newManager);
    }

    @Override
    public @NotNull Boolean HandleObject(Object object, @Nullable String key, @Nullable FunctionStack stack)
    {
        return false;
    }

    @Override
    public @NotNull Boolean HandleElement(JsonElement element, String elementName, @Nullable FunctionStack stack)
    {
        return switch (elementName)
        {
            case "check" ->
            {
                if (ElementAsStatementAddress(element) instanceof StatementAddress address)
                {
                    checkStatement = address;
                    yield true;
                }
                LogWrongType(elementName, JsonPrimitive.class, element.getClass());
                yield false;
            }
            case "true", "false" ->
            {
                if (ElementAsSourceFunctionHolder(element) instanceof SourceFunctionHolder holder)
                {
                    if(elementName.equals("true"))
                    {
                        trueObject = holder;
                    }
                    else
                    {
                        falseObject = holder;
                    }
                    yield true;
                }
                LogConversionFailedForRecognisedKey(element, SourceFunctionHolder.class, elementName);
                yield false;
            }
            case null, default ->
            {
                if(super.CheckUnknownElementIsStatementAddress(element, identifier, elementName) instanceof StatementAddress statementAddress)
                {
                    checkStatement = statementAddress;
                    yield true;
                }
                if(super.CheckUnknownElementIsSourceFunctionHolder(element, identifier, elementName) instanceof SourceFunctionHolder holder)
                {
                    if(trueObject == null)
                    {
                        trueObject = holder;
                        yield true;
                    }

                    falseObject = holder;
                    yield false;
                }

                LogUnknownName(elementName, element.getClass());
                yield false;
            }
        };
    }
}
