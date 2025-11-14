package net.tazgirl.magicjson.main.function_object.objects.hooks.hook_objects;

import net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects.HookParametersFunctionObject;

import java.util.function.Function;

public abstract class ReturnHook<R> extends Hook<Function<HookParametersFunctionObject, R>>
{

    public ReturnHook(Function<HookParametersFunctionObject, R> function)
    {
        super(function);
    }

    public R RunHook(HookParametersFunctionObject parameters)
    {
        return function.apply(parameters);
    }
}
