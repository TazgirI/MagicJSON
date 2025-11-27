package net.tazgirl.magicjson.old.main.function_object;

import com.google.gson.JsonElement;
import net.tazgirl.magicjson.old.main.function_object.objects.BaseFunctionObject;

import java.util.ArrayList;
import java.util.List;

public class FunctionStack
{
    String processedAddress;

    public FunctionStack(String processedAddress)
    {
        this.processedAddress = processedAddress;
    }


    List<BaseFunctionObject> closedObjects = new ArrayList<>();

    List<BaseFunctionObject> objectStack = new ArrayList<>();

    public void AddOpen(BaseFunctionObject object)
    {
        objectStack.add(object);
    }

    public void Close()
    {
        BaseFunctionObject object = objectStack.getLast();

        if(objectStack.size() == 1)
        {
            closedObjects.add(object);
            objectStack = new ArrayList<>();
        }
        else
        {
            objectStack.removeLast();
            objectStack.getLast().HandleObject(object, null, this);
        }
    }

    public boolean HandleElement(JsonElement element)
    {
        if(objectStack.isEmpty()){return false;}

        return objectStack.getLast().HandleElement(element, null, this);
    }

    public boolean HandleElement(JsonElement element, String name)
    {
        if(objectStack.isEmpty()){return false;}

        return objectStack.getLast().HandleElement(element, name, this);
    }

    public boolean AttemptElement(JsonElement element, String name)
    {
        return HandleElement(element, name);
    }

    public SourceFunctionHolder Finish()
    {
        return new SourceFunctionHolder(closedObjects);
    }

}
