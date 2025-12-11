package net.tazgirl.magicjson.statements.objects.compounds;

import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class CompoundBase extends Base
{
    public List<Base> values = new ArrayList<>();
    public Boolean breakOnFind = true;

    public CompoundBase(StatementHolder holder)
    {
        super(holder);
    }

    @Override
    public @NotNull Boolean HandleValue(Object object)
    {
        if(object instanceof Base base)
        {
            values.add(base);
            return true;
        }

        DebugUnHandledType(object.getClass());
        return false;
    }

    @Override
    public @NotNull Boolean HandleUniqueArgument(String string)
    {
        // One of these will always be active by default but this allows for statements to be strongly typed against changes in the default case
        if(string.equals(".break"))
        {
           breakOnFind = true;
           return true;
        }
        if(string.equals(".continue"))
        {
            breakOnFind = false;
            return true;
        }

        return false;
    }

    @Override
    public Base ImplicitChild()
    {
        return null;
    }

    @Override
    public String toString()
    {
        return identifier + "( " + values.toString() + " )";
    }

    @Override
    public void Replace(Base oldBase, Base newBase)
    {

        int index = values.indexOf(oldBase);
        if(index != -1)
        {
            values.remove(index);
            values.add(index, newBase);
        }

    }

    public int numberOfContents()
    {
        return values.size();
    }

    public Object ResolveSpecific(int index)
    {
        if(values.size() <= index){return null;}
        return values.get(index).Resolve();
    }
}
