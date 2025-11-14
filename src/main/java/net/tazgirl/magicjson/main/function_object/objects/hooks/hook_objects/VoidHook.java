package net.tazgirl.magicjson.main.function_object.objects.hooks.hook_objects;

import net.tazgirl.magicjson.Constants;
import net.tazgirl.magicjson.main.function_object.objects.hooks.function_objects.HookParametersFunctionObject;

import java.util.function.Consumer;

public class VoidHook extends Hook<Consumer<HookParametersFunctionObject>>
{
    public VoidHook(Consumer<HookParametersFunctionObject> function)
    {
        super(function);
    }

    @Override
    public Object RunHook(HookParametersFunctionObject parameters)
    {
        function.accept(parameters);
        return Constants.trueEmpty;
    }
}
