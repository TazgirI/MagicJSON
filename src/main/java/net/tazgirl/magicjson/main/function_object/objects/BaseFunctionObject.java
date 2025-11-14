package net.tazgirl.magicjson.main.function_object.objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.main.addresses.Address;
import net.tazgirl.magicjson.main.addresses.StatementAddress;
import net.tazgirl.magicjson.main.function_object.FunctionManager;
import net.tazgirl.magicjson.main.function_object.FunctionStack;
import net.tazgirl.magicjson.main.function_object.JsonToFunctionObject;
import net.tazgirl.magicjson.main.function_object.SourceFunctionHolder;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Map;

public abstract class BaseFunctionObject
{
    public Map<String, Object> args;

    public FunctionManager manager;

    public String identifier = "";

    public Map<String, JsonObjectTypes> contents;

    public BaseFunctionObject()
    {

    }

    public abstract Object Run();

    public void SpreadArgs(FunctionManager newManager)
    {
        manager = newManager;
    }

    @NotNull
    public abstract Boolean HandleObject(Object object, @Nullable String key, @Nullable FunctionStack stack);

    @NotNull
    public abstract Boolean HandleElement(JsonElement element, @Nullable String elementName, @Nullable FunctionStack stack);

    public StatementAddress CheckUnknownElementIsStatementAddress(JsonElement element, String identifier, String elementName)
    {
        if(element instanceof JsonPrimitive primitive)
        {
            Address.verdict verdict = Address.Assess(primitive.getAsString());

            if(verdict == Address.verdict.STATEMENT || verdict == Address.verdict.DEFAULT)
            {

                if(identifier != null && elementName != null)
                {
                    Logging.Debug(identifier + " has accepted \"" + elementName + "\" as a StatementAddress despite it not matching an expected property", false);
                }

                return StatementAddress.from(primitive.getAsString());
            }
        }
        return null;
    }

    public SourceFunctionHolder CheckUnknownElementIsSourceFunctionHolder(JsonElement element, String identifier, String elementName)
    {
        if(element instanceof JsonObject object)
        {
            if(JsonToFunctionObject.LoopJsonObject(object, new FunctionStack()) instanceof SourceFunctionHolder holder)
            {
                if(identifier != null && elementName != null)
                {
                    Logging.Debug(identifier + " has accepted \"" + elementName + "\" as a SourceFunctionHolder despite it not matching an expected property", false);
                }

                return holder;
            }
        }
        return null;
    }


    public String LogLocation()
    {
            return "Object: " + this.identifier + "     " +
                "Statement address: " + this.manager.getFunctionAddress();
    }

    public enum JsonObjectTypes
    {
        FUNCTION,
        STATEMENT,
        SOURCE_FUNCTION,
        ANY
    }

    public String getDebugString()
    {
        return "\nFunction Address: " + manager.getFunctionAddress() + "\n" +
                "Object: " + identifier + "\n" +
                "Step: " + manager.getSteps();
    }



    public void LogWrongType(String failedInputName, Class<?> expectedType, Class<?> receivedType)
    {
        Logging.Debug("Function object " + identifier + " expected \"" + failedInputName + "\" to be of type " + expectedType + ", instead received " + receivedType + ", value has been skipped", false);
    }

    public void LogUnknownName(String failedInputName, Class<?> failedInputType)
    {
        Logging.Debug("Function object " + identifier + " received the unknown key \"" + failedInputName + "\" of type " + failedInputType + " and cannot handle it, value has been skipped", false);
    }

    public void LogUnhandledValue(Class<?> failedInputType)
    {
        Logging.Debug("Function object " + identifier + " received a generic argument it was unable to handle of type " + failedInputType + ", value has been skipped", false);
    }

}
