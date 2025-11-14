package net.tazgirl.magicjson.main.function_object.objects.hooks.hook_objects.return_types;

import net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects.HookParametersFunctionObject;
import net.tazgirl.magicjson.main.function_object.objects.hooks.hook_objects.ReturnHook;

import java.util.function.Function;

public class BooleanHook extends ReturnHook<Boolean>
{
    public BooleanHook(Function<HookParametersFunctionObject, Boolean> function)
    {
        super(function);
    }
}
