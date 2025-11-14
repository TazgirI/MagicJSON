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
import java.util.List;
import java.util.Map;

public abstract class BaseFunctionObject
{
    public Map<String, Object> args;

    public FunctionManager manager;

    public String identifier = "";

    List<Address.verdict> statementMarkers = List.of(Address.verdict.STATEMENT, Address.verdict.DEFAULT);
    List<Address.verdict> functionMarkers = List.of(Address.verdict.FUNCTION, Address.verdict.DEFAULT);

    public BaseFunctionObject()
    {

    }

    public final Object Run()
    {
        manager.addStep();

        return RunPersonal();
    }

    public abstract Object RunPersonal();

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
        if(ElementAsStatementAddress(element) instanceof StatementAddress address)
        {

            if(identifier != null && elementName != null)
            {
                Logging.Debug(identifier + " has accepted \"" + elementName + "\" as a StatementAddress despite it not matching an expected property", false);
            }

            return address;
        }
        return null;
    }

    public SourceFunctionHolder CheckUnknownElementIsSourceFunctionHolder(JsonElement element, String identifier, String elementName)
    {
        if(ElementAsSourceFunctionHolder(element) instanceof SourceFunctionHolder holder)
        {
            if(identifier != null && elementName != null)
            {
                Logging.Debug(identifier + " has accepted \"" + elementName + "\" as a SourceFunctionHolder despite it not matching an expected property", false);
            }

            return holder;
        }
        return null;
    }

    public StatementAddress ElementAsStatementAddress(JsonElement element)
    {
        if(element instanceof JsonPrimitive primitive && statementMarkers.contains(Address.Assess(primitive.getAsString())))
        {
            return StatementAddress.from(element.getAsString());
        }
        return null;
    }

    public SourceFunctionHolder ElementAsSourceFunctionHolder(JsonElement element)
    {
        if(element instanceof JsonObject object && JsonToFunctionObject.LoopJsonObject(object, new FunctionStack()) instanceof SourceFunctionHolder holder)
        {
            return holder;
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

    public SourceFunctionHolder LoopJsonObject(JsonElement element)
    {
        if(element instanceof JsonObject object)
        {
            return JsonToFunctionObject.LoopJsonObject(object, new FunctionStack());
        }

        return null;
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

    public void LogConversionFailedForUnknownName(JsonElement element, Class<?> expectedType, String name)
    {
        Logging.Debug("Function object " + identifier + " has failed to convert the following JsonElement of un-recognised key \"" + name + "\" into a " + expectedType + ":  " + element, false);
    }

    public void LogConversionFailedForRecognisedKey(JsonElement element, Class<?> expectedType, String name)
    {
        Logging.Debug("Function object " + identifier + " has failed to convert the following JsonElement of recognised key \"" + name + "\" into a " + expectedType + ":  " + element, false);
    }

}
