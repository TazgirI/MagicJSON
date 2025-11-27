package net.tazgirl.magicjson.old.main.hook_object;

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
