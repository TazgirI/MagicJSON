package net.tazgirl.magicjson.main.function_object.objects.hooks.hook_objects;

import net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects.HookParametersFunctionObject;

public abstract class Hook<T>
{
    T function;

    public abstract Object RunHook(HookParametersFunctionObject parameters);

    public Hook(T function)
    {
        this.function = function;
    }
}
