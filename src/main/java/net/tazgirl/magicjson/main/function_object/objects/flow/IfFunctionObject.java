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

    @Override
    public Boolean Run()
    {
        if(checkStatement == null)
        {
            Logging.Warn("If function is missing it's check statement " + getDebugString(), false);
        }

        if(!(PrivateCore.getStatement(checkStatement) instanceof ResolvesBoolean))
        {
            Logging.Warn("If function has non-boolean Statement as it's check" + getDebugString(), false);
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
        if(elementName == null){return false;}

        return switch (elementName)
        {
            case "check" ->
            {
                if (element instanceof JsonPrimitive jsonPrimitive)
                {
                    checkStatement = StatementAddress.from(jsonPrimitive.getAsString());
                    yield true;
                }
                LogWrongType(elementName, JsonPrimitive.class, element.getClass());
                yield false;
            }
            case "true" ->
            {
                if (element instanceof JsonObject jsonObject)
                {
                    trueObject = JsonToFunctionObject.LoopJsonObject(jsonObject, new FunctionStack());
                    yield true;
                }
                LogWrongType(elementName, JsonObject.class, element.getClass());
                yield false;
            }
            case "false" ->
            {
                if (element instanceof JsonObject jsonObject)
                {
                    trueObject = JsonToFunctionObject.LoopJsonObject(jsonObject, new FunctionStack());
                    yield true;
                }
                LogWrongType(elementName, JsonObject.class, element.getClass());
                yield false;
            }
            default ->
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
