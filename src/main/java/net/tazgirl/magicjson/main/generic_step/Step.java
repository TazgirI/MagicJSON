package net.tazgirl.magicjson.main.generic_step;

import net.tazgirl.magicjson.main.function_object.FunctionManager;

public abstract class Step<T>
{
    T value;

    public Step(T value)
    {
        this.value = value;
    }

    public abstract Object getExecutable(FunctionManager manager);


    public abstract Object getAsReturnValue(FunctionManager manager);

}
