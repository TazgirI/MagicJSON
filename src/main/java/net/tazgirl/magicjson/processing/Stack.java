package net.tazgirl.magicjson.processing;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;

import java.util.ArrayList;
import java.util.List;

public class Stack
{
    final String processingResourceAddress;
    final TokensToHolder manager;

    StatementHolder holder;

    List<Base> objectStack = new ArrayList<>();

    // public void OnFirst() that adds object to uniques so holder knows it's root even if there's no children
    // When Put() uses HandleValue, send holder the relationship even on failure

    public Stack(String processingResourceAddress, TokensToHolder manager)
    {
        this.processingResourceAddress = processingResourceAddress;
        this.manager = manager;

        holder = new StatementHolder();
    }

    public Boolean Close()
    {
        Base top = objectStack.getLast();
        objectStack.removeLast();

        if(objectStack.getLast().HandleValue(top))
        {
            holder.AddRelationship(objectStack.getLast(), top);
            return true;
        }

        Logging.Debug(objectStack.getLast().toString() + " failed to handle " + top.toString() + " when processing " + processingResourceAddress);
        return false;
    }

    public boolean Close(int amount)
    {
        boolean returnBool = true;
        for(int i = 0; i < amount; i++)
        {
            returnBool = returnBool ? Close() : false;
        }

        return returnBool;
    }

    public void PutUniqueArgument(String string)
    {
        objectStack.getLast().HandleUniqueArgument(string.toLowerCase());
    }

    public void Put(Base base)
    {
        objectStack.add(base);
    }

    public StatementHolder finalise()
    {
        return holder;
    }
}
