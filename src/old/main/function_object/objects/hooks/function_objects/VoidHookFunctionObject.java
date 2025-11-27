package net.tazgirl.magicjson.old.main.function_object.objects.hooks.function_objects;

import net.tazgirl.magicjson.old.Constants;

public class VoidHookFunctionObject extends BaseHookFunctionObject
{
    @Override
    public Object RunPersonal()
    {
        manager.runVoidHook(hookName, parameters);
        return Constants.trueEmpty;
    }
}
