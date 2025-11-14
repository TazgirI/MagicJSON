package net.tazgirl.magicjson.main.function_object;

import net.tazgirl.magicjson.main.function_object.objects.BaseFunctionObject;

import java.util.List;

public record SourceFunctionHolder(List<BaseFunctionObject> heldObjects)
{
    public void SpreadManager(FunctionManager newManager)
    {
        for(BaseFunctionObject object: heldObjects)
        {
            object.SpreadArgs(newManager);
        }
    }
}
