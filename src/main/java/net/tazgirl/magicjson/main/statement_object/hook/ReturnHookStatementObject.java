package net.tazgirl.magicjson.main.statement_object.hook;

import net.tazgirl.magicjson.PrivateCore;
import net.tazgirl.magicjson.main.hook_object.Hook;
import net.tazgirl.magicjson.main.hook_object.HookParameter;
import net.tazgirl.magicjson.main.hook_object.HookParameters;
import net.tazgirl.magicjson.main.hook_object.ReturnHook;
import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.StatementManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ReturnHookStatementObject<T> extends BaseStatementObject
{
    String hookName;

    Map<String, BaseStatementObject> contents;

    protected Class<T> returnTypeClass;

    @Override
    public Boolean HandleValue(@NotNull Object content)
    {
        if (content instanceof HookParameterStatementObject parameterObject)
        {
            contents.put(parameterObject.name, parameterObject.value);
            return true;
        }

        return false;
    }

    @Override
    public T Resolve()
    {
        if(!(PrivateCore.getHook(hookName) instanceof ReturnHook<?> hook)){return null;}

        HookParameters parameters = new HookParameters();

        for(Map.Entry<String, BaseStatementObject> entry: contents.entrySet())
        {
            parameters.putParameter(new HookParameter(entry.getKey(), entry.getValue().Resolve()));
        }

        Object hookResult = ValueConvert(hook.RunHook(parameters));

        if(returnTypeClass.isInstance(hookResult))
        {
            return (T) hookResult;
        }

        return null;
    }

    public abstract Object ValueConvert(Object value);

    @Override
    public void SpreadManager(@NotNull StatementManager newManager)
    {
        manager = newManager;

        for(BaseStatementObject statementObject: contents.values())
        {
            statementObject.SpreadManager(newManager);
        }
    }

    @Override
    public String toString()
    {
        return identifier + "( " + hookName + "  " + contents + " )";
    }

    @Override
    public BaseStatementObject ImplicitChild()
    {
        return new HookParameterStatementObject();
    }
}
