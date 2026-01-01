package net.tazgirl.magicjson.processing;

import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.primitives.numbers.DoubleObject;
import net.tazgirl.magicjson.statements.objects.primitives.numbers.FloatObject;
import net.tazgirl.magicjson.statements.objects.primitives.numbers.IntegerObject;
import net.tazgirl.magicjson.statements.objects.primitives.numbers.LongObject;

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

        holder = new StatementHolder(this);
    }

    public Boolean Close()
    {
        if(objectStack.size() <= 1)
        {
            MJLogging.Debug("Attempted to close top of empty stack, action skipped.   Stack contents: " + objectStack.toString());
            return false;
        }

        Base top = objectStack.getLast();
        objectStack.removeLast();

        if(objectStack.getLast().HandleBase(top))
        {
            holder.AddRelationship(objectStack.getLast(), top);
            return true;
        }

        MJLogging.Debug(objectStack.getLast().toString() + " failed to handle " + top.toString() + " when processing " + processingResourceAddress + ".   Stack contents: " + objectStack);
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
        objectStack.getLast().HandleUniqueArgument(string.substring(1).toLowerCase());
    }

    public void Put(Base base)
    {
        objectStack.add(base);
    }

    public void PutNum(String numString)
    {
        if (Character.isDigit(numString.charAt(numString.length() - 1)))
        {
            if (numString.contains("."))
            {
                Put(new DoubleObject(holder, Double.valueOf(numString)));
            } else
            {
                Put(new IntegerObject(holder, Integer.valueOf(numString)));
            }
            return;
        }

        char checkChar = numString.toLowerCase().charAt(numString.length() - 1);
        numString = numString.substring(0, numString.length() - 1);

        if(numString.chars().anyMatch(num -> !Character.isDigit(num) && ((char) num) != '.'))
        {
            return;
        }

        switch(checkChar)
        {
            case 'i' ->
            {
                Put(new IntegerObject(holder, Integer.valueOf(numString)));
            }
            case 'f' ->
            {
                Put(new FloatObject(holder, Float.valueOf(numString)));
            }
            case 'd' ->
            {
                Put(new DoubleObject(holder, Double.valueOf(numString)));
            }
            case 'l' ->
            {
                Put(new LongObject(holder, Long.valueOf(numString)));
            }
        }
    }

    public StatementHolder finalise()
    {
        if(holder.uniques.isEmpty())
        {
            holder.uniques.add(objectStack.getFirst());
        }
        return holder.Finalise();
    }

    public void Replace(Base oldBase, Base newBase)
    {
        // If oldBase has arguments then the holder transfers them, if it doesn't then only the stack swap is required
        if(objectStack.contains(oldBase))
        {
            objectStack.set(objectStack.indexOf(oldBase), newBase);
        }
    }
}
