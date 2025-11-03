package net.tazgirl.magicjson.main.function_object;

import net.tazgirl.magicjson.main.statement_object.interface_categories.ResolvesBoolean;

import java.util.Map;

public class IfFunctionObject extends BaseFunctionObject
{
    ResolvesBoolean checkStatement = null;

    BaseFunctionObject trueObject = null;

    BaseFunctionObject falseObject = null;

    @Override
    public Boolean Resolve()
    {
        if(checkStatement.Resolve())
        {
            trueObject.Resolve();
            return true;
        }
        else
        {
            falseObject.Resolve();
            return false;
        }
    }

    @Override
    public void SpreadArgs(Map<String, Object> newArgs)
    {
        super.SpreadArgs(newArgs);

        trueObject.SpreadArgs(newArgs);
        falseObject.SpreadArgs(newArgs);
    }

    @Override
    public Boolean HandleValue(Object content, HandleValueKey key)
    {
        switch (key)
        {
            case CHECK_STATEMENT:
            {
                if(content instanceof ResolvesBoolean statementObject)
                {
                    checkStatement = statementObject;
                    return true;
                }
                return false;
            }
            case TRUE:
            {
                if(content instanceof BaseFunctionObject functionObject)
                {
                    if(trueObject == null)
                    {
                        trueObject = functionObject;
                        return true;
                    }
                    return false;
                }
            }
            case FALSE:
            {
                if(content instanceof BaseFunctionObject functionObject)
                {
                    if(falseObject == null)
                    {
                        falseObject = functionObject;
                        return true;
                    }
                    return false;
                }
            }
            default:
                return false;
        }
    }
}
