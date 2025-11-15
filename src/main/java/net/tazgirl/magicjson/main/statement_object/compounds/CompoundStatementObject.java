package net.tazgirl.magicjson.main.statement_object.compounds;

import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.StatementManager;
import net.tazgirl.magicjson.main.statement_object.interface_categories.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CompoundStatementObject<T> extends BaseStatementObject
{
    public List<T> contents = new ArrayList<>();

    public Class<?> compoundType;

    public CompoundStatementObject()
    {
        switch (this)
        {
            case ContainsNumbers containsNumbers -> compoundType = ResolvesNumber.class;
            case ContainsBooleans containsBooleans -> compoundType = ResolvesBoolean.class;

            // To protect type safety, for now if you want to put a numerical comparator or a literal in a plain AND, you must wrap it on its own inside a compound. i.e AND(ANDBOOL(LESSTHAN()))
            case ContainsCompounds containsCompounds -> compoundType = CompoundStatementObject.class;

            default -> {}
        }
    }


    @Override
    public Boolean HandleValue(Object content)
    {
        if(compoundType.isInstance(content))
        {
            contents.add((T) content);
            return true;
        }

        return false;
    }

    @Override
    public Boolean Resolve()
    {
        return null;
    }

    @Override
    public String toString()
    {
        String childrenStrings = "";

        for(T child: contents)
        {
            childrenStrings = childrenStrings.concat(child.toString() + " ");
        }

        return identifier + "( " + childrenStrings + " )";
    }

    @Override
    public void SpreadManager(StatementManager newManager)
    {
        manager = newManager;

        contents.forEach(content -> ((BaseStatementInterface) content).SpreadManager(newManager));
    }
}
