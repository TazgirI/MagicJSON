package net.tazgirl.magicjson.old.main.function_object;

import net.tazgirl.magicjson.old.main.function_object.objects.BaseFunctionObject;
import net.tazgirl.magicjson.old.main.function_object.objects.flow.IfFunctionObject;
import net.tazgirl.magicjson.old.main.function_object.objects.returns.ReturnFunctionObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;


public class FunctionTokens
{
    public static Map<String, Class<? extends BaseFunctionObject>> objectTokens = Map.of(
            "if", IfFunctionObject.class,
            "return", ReturnFunctionObject.class

    );


    public static boolean isFunctionObjectKey(String key)
    {
        return objectTokens.get(key) != null;
    }

    public static BaseFunctionObject getFunctionObjectKeyAsObject(String string)
    {
        Class<? extends BaseFunctionObject> functionClass = objectTokens.get(string);

        if(functionClass == null){return null;}

        try
        {
            return functionClass.getDeclaredConstructor().newInstance();
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

}
