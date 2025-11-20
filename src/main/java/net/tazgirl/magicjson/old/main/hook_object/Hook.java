package net.tazgirl.magicjson.old.main.hook_object;

public abstract class Hook<T>
{
    T function;

    public abstract Object RunHook(HookParameters parameters);

    public Hook(T function)
    {
        this.function = function;
    }
}
