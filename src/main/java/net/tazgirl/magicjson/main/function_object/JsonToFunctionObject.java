package net.tazgirl.magicjson.main.function_object;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.main.function_object.objects.BaseFunctionObject;

import java.util.Map;

public class JsonToFunctionObject
{

    public static SourceFunctionHolder ConstructFunctionFromJson(JsonObject rootObject)
    {
        FunctionStack stack = new FunctionStack();

        return LoopJsonObject(rootObject, stack);
    }

    // Take object, get its name and corresponding class
    // Instantiate and then fetch the map of names to input types
    // Get those input types, recursively if necessary

    public static SourceFunctionHolder LoopJsonObject(JsonObject object, FunctionStack stack)
    {
        Map<String, JsonElement> contents = object.asMap();


        for(String name: contents.keySet())
        {
            HandleJsonElement(contents.get(name), stack, name);

        }

        return stack.Finish();
    }



    public static void HandleJsonElement(JsonElement element, FunctionStack stack, String elementName)
    {
        if(FunctionTokens.getFunctionObjectKeyAsObject(elementName) instanceof BaseFunctionObject functionObject)
        {
            stack.AddOpen(functionObject);

            if(element instanceof JsonObject object)
            {
                LoopJsonObject(object, stack);
            }
            stack.Close();

            return;
        }


        if(stack.objectStack.isEmpty())
        {
            Logging.Debug("FunctionStack object list is empty after initial function token check", false);
            return;
        }


        if(!stack.AttemptElement(element, elementName))
        {
            Logging.Debug("Failed to process JsonElement   " +
                    "Element: " + element + " " +
                    "Name: " + elementName, false);
        }
    }

}
