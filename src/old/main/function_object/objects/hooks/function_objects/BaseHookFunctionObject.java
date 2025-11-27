package net.tazgirl.magicjson.old.main.function_object.objects.hooks.function_objects;

import com.google.gson.JsonElement;
import net.tazgirl.magicjson.old.main.function_object.FunctionStack;
import net.tazgirl.magicjson.old.main.function_object.objects.BaseFunctionObject;
import net.tazgirl.magicjson.old.main.hook_object.HookParameters;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class BaseHookFunctionObject extends BaseFunctionObject
{
    String hookName = "";

    HookParameters parameters;

    public void setHookName(String hookName)
    {
        this.hookName = hookName;
    }

    @Override
    public @NotNull Boolean HandleElement(JsonElement element, @Nullable String elementName, @Nullable FunctionStack stack)
    {
        return false;
    }


    // TODO: Update with keys and handle JSON object parameters
    @Override
    public @NotNull Boolean HandleObject(Object value, String key, FunctionStack stack)
    {
        if(value instanceof String string)
        {
            hookName = string;
            return true;
        }
        if(value instanceof HookParametersFunctionObject hookParameters)
        {
            // Skips the manager step add as all HookParametersFunctionObject are destroyed after construction
            parameters = hookParameters.RunPersonal();
            return true;
        }


        return false;

    }
}
