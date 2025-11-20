package net.tazgirl.magicjson.old.main.function_object.objects.hooks.function_objects;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.old.main.function_object.FunctionManager;
import net.tazgirl.magicjson.old.main.function_object.FunctionStack;
import net.tazgirl.magicjson.old.main.function_object.objects.BaseFunctionObject;
import net.tazgirl.magicjson.old.main.hook_object.HookParameter;
import net.tazgirl.magicjson.old.main.hook_object.HookParameters;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HookParametersFunctionObject extends BaseFunctionObject
{

    FunctionManager manager;

    List<HookParameter> parameters = new ArrayList<>();

    public HookParametersFunctionObject(FunctionManager manager)
    {
        this.manager = manager;
    }

    public void putParameter(String name, Object value)
    {
        parameters.add(new HookParameter(name, value));
    }

    public void putParameter(HookParameter parameter)
    {
        parameters.add(parameter);
    }


    @Override
    public HookParameters RunPersonal()
    {
        return new HookParameters(parameters);
    }


    @Override
    public @NotNull Boolean HandleObject(Object content, String key, FunctionStack stack)
    {
        if(content instanceof JsonObject object)
        {
            JsonElement name = object.get("name");
            JsonElement value = object.get("value");

            if(name instanceof JsonPrimitive nameObject && value instanceof JsonPrimitive valueObject)
            {
                parameters.add(new HookParameter(nameObject.getAsString(), valueObject));
                return true;
            }

            Logging.Debug("HookParameter in function did not contain both a \"name\" and \"value\" JsonPrimitive", false);

            return false;
        }

        return false;
    }

    @Override
    public @NotNull Boolean HandleElement(JsonElement element, @Nullable String elementName, @Nullable FunctionStack stack)
    {
        return false;
    }
}
