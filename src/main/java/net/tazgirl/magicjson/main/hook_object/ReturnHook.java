package net.tazgirl.magicjson.main.hook_object;

import net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects.HookParametersFunctionObject;

import java.util.List;
import java.util.function.Function;

public abstract class ReturnHook<R> extends Hook<Function<HookParameters, R>>
{

    public ReturnHook(Function<HookParameters, R> function)
    {
        super(function);
    }

    public R RunHook(HookParameters parameters)
    {
        return function.apply(parameters);
    }
}
