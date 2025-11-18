package net.tazgirl.magicjson.main.hook_object;

import net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects.HookParametersFunctionObject;

import java.util.List;

public abstract class Hook<T>
{
    T function;

    public abstract Object RunHook(HookParameters parameters);

    public Hook(T function)
    {
        this.function = function;
    }
}
