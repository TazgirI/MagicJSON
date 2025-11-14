package net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects;

import com.google.gson.JsonElement;
import net.tazgirl.magicjson.Constants;
import net.tazgirl.magicjson.main.function_object.FunctionStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class VoidHookFunctionObject extends BaseHookFunctionObject
{
    @Override
    public Object RunPersonal()
    {
        manager.runVoidHook(hookName, parameters);
        return Constants.trueEmpty;
    }
}
