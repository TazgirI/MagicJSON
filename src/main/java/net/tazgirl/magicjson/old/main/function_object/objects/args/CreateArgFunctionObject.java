package net.tazgirl.magicjson.old.main.function_object.objects.args;

import com.google.gson.JsonElement;
import net.tazgirl.magicjson.old.main.function_object.FunctionStack;
import net.tazgirl.magicjson.old.main.function_object.objects.BaseFunctionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CreateArgFunctionObject extends BaseFunctionObject
{
    @Override
    public Object RunPersonal()
    {
        return null;
    }

    @Override
    public @NotNull Boolean HandleObject(Object object, @Nullable String key, @Nullable FunctionStack stack)
    {
        return null;
    }

    @Override
    public @NotNull Boolean HandleElement(JsonElement element, @Nullable String elementName, @Nullable FunctionStack stack)
    {
        return null;
    }
}
