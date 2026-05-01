package net.tazgirl.magicjson.statements.objects;

import net.tazgirl.magicjson.MJLogging;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class Base
{

    protected StatementHolder holder;
    protected String identifier;

    public Base(StatementHolder holder)
    {
        identifier = setIdentifier();
        this.holder = holder;
    }


    public abstract Object resolve();

    @NotNull
    public abstract Boolean handleBase(Base base);

    @NotNull
    public Boolean HandleObject(Object object)
    {
        return false;
    }

    @NotNull
    public abstract Boolean handleUniqueArgument(String string);


    public Base implicitChild()
    {
        return null;
    }

    @NotNull
    public abstract String setIdentifier();

    @Override
    public abstract String toString();

    public abstract void replace(Base oldBase, Base newBase);

    public void debugUnHandledType(Class<?> failedType)
    {
        MJLogging.debug("Failed to handle Object of type \"" + failedType.toString() + "\" in a(n) " + identifier + " within: " + holder.getAddress());
    }

    public void debugUnHandledType(Object object)
    {
        debugUnHandledType(object.getClass());
    }

    protected List<Base> replaceInList(Base oldBase, Base newBase, List<Base> sourceList)
    {
        if(sourceList.contains(oldBase))
        {
            sourceList.add(sourceList.indexOf(oldBase), newBase);
            sourceList.remove(oldBase);
        }

        return sourceList;
    }

    protected String containerString(Object... objects)
    {
        StringBuilder objectString = new StringBuilder();
        for(Object object : objects)
        {
            objectString.append(object.toString() + ", ");
        }
        int length = objectString.length();
        objectString.delete(length - 2, length);
        return identifier + "( " + objectString + " )";
    }
}
