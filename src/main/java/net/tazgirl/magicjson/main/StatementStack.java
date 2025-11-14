package net.tazgirl.magicjson.main;


import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.main.statement_object.BaseStatementObject;
import net.tazgirl.magicjson.main.statement_object.BooleanStatementObject;
import net.tazgirl.magicjson.main.statement_object.StringStatementObject;
import net.tazgirl.magicjson.main.statement_object.numbers.objects.*;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class StatementStack
{
    public List<BaseStatementObject> objectStack = new ArrayList<>();

    public List<String> tokensBeingParsed;

    public StatementStack(List<String> newTokensBeingParsed)
    {
        tokensBeingParsed = newTokensBeingParsed;
    }

    public void Put(BaseStatementObject statementObject)
    {
        objectStack.add(statementObject);
    }

    public void Close()
    {
        if(objectStack.size() > 1)
        {
            BaseStatementObject closedObject = objectStack.getLast();
            objectStack.removeLast();

            objectStack.getLast().HandleValue(closedObject);
        }
        //EXP: Forgot to consider the closing symbol that the base object must have
//        else
//        {
//            ParsingError("Attempted to close top object on stack while parsing but there is less than 2 objects in the stack.");
//        }
    }

    public void Put(Boolean bool)
    {
        BooleanStatementObject booleanObject = new BooleanStatementObject(bool);

        if(objectStack.isEmpty())
        {
            objectStack.add(booleanObject);
        }
        else
        {
            if(!objectStack.getLast().HandleValue(booleanObject))
            {
                ParsingError("Attempted to add bool to stack but current stack cannot accept a BooleanObject");
            }
        }
    }

    public void Put(String string)
    {
        if(objectStack.isEmpty())
        {
            StringStatementObject stringObject = new StringStatementObject();
            stringObject.HandleValue(string);

            Put(stringObject);
        }
        else if(!objectStack.getLast().HandleValue(string))
        {
            ParsingError("Stack failed to handle String \"" + string + "\"");
        }
    }

    public void Put(Class<? extends BaseStatementObject> objectClass)
    {
        try
        {
            Put(objectClass.getDeclaredConstructor().newInstance());
        }
        catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void Put(NumberStatementObject<?> numberObject)
    {
        if(objectStack.isEmpty())
        {
            objectStack.add(numberObject);
        }
        else
        {
            if(!objectStack.getLast().HandleValue(numberObject))
            {
                ParsingError("Attempted to add number to stack but current stack cannot accept a NumberObject");
            }

        }
    }

    public void PutNum(String numString)
    {
        char endChar = numString.charAt(numString.length() - 1);

        NumberStatementObject<?> numberObject;

        if(Character.isDigit(endChar))
        {
            if(numString.contains("."))
            {
                numberObject = new DoubleStatementObject();
            }
            else
            {
                numberObject = new IntegerStatementObject();
            }
        }
        else
        {

            switch(endChar)
            {
                case 'i', 'I' -> numberObject = new IntegerStatementObject();
                case 'f', 'F' -> numberObject = new FloatStatementObject();
                case 'l', 'L' -> numberObject = new LongStatementObject();
                case 'd', 'D' -> numberObject = new DoubleStatementObject();
                default -> numberObject = null;
            }
        }

        if(numberObject == null)
        {
            ParsingError("Attempted to match number type the the String \"" + numString + "\" but could not find a match, make sure it has an acceptable type digit and it isn't a regular variable that starts with a number");
            return;
        }

        if(!numberObject.HandleValue(numString))
        {
            ParsingError("Failed to put \"" + numString + "\" into a " + numberObject.identifier + "Object instance");
        }

        objectStack.getLast().HandleValue(numberObject);
    }

    public BaseStatementObject GetStackResult()
    {
        if(objectStack.size() == 1)
        {
            return objectStack.getFirst();
        }
        else if(objectStack.isEmpty())
        {
            Logging.Debug( "Attempted to return an empty stack as BaseObject " +
                    "The process that requested this has been returned a null, stored function will now no longer exist.    " +
                    "The game should not be considered stable but tokenisation will continue to run to allow other debugging messages to be processed   " +
                    "If you are a player then please report this message to the Modpack/Datapack creator unless they have stated this is intended   " +
                    "Tokens being parsed: " + tokensBeingParsed +
                    "   Contents of stack: " + objectStack, false);
            return null;
        }
        else
        {
            ParsingError("Stack result was requested but stack contains more than 1 object, the first has been returned. Anything in the stack except the first object has been discarded");
            return objectStack.getFirst();
        }

    }

    public void ParsingError(String contextMessage)
    {
        Logging.Debug( contextMessage + "   Tokenisation has not been stopped but unintended behaviour may occur!    " +
                "If you are a player then please report this message to the Modpack/Datapack creator unless they have stated this is intended   " +
                "Tokens being parsed: " + tokensBeingParsed +
                "   Contents of stack: " + objectStack, false);
    }

    @Override
    public String toString()
    {
       String stackString = "";

       for(BaseStatementObject object: objectStack)
       {
           stackString = stackString.concat(object.toString() + " ");
       }

       return "Stack of ( " + stackString + " )";
    }
}
