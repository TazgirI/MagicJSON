package net.tazgirl.magicjson.statements.objects;

import net.tazgirl.magicjson.Logging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatementHolder
{
    Map<String, Object> args = new HashMap<>();

    String address;

    Base root = null;

    List<Base> uniques = new ArrayList<>();
    Map<Base, List<Base>> parentToChildren = new HashMap<>();
    Map<Base, Base> childToParent = new HashMap<>();

    public Object Run()
    {
        return root.Resolve();
    }

    public void AddRelationship(Base parent, Base child)
    {
        if(!uniques.contains(parent)){uniques.add(parent);}
        if(!uniques.contains(child)){uniques.add(child);}


        childToParent.put(child, parent);

        parentToChildren.computeIfAbsent(parent, p -> new ArrayList<>()).add(child);
    }

    public StatementHolder Finalise()
    {
        for(Base base: uniques)
        {
            if(!childToParent.containsKey(base))
            {
                root = base;
                break;
            }
        }

        if(root == null)
        {
            Logging.Debug("The StatementHolder for \"" + address + "\" has failed to find an acceptable root and is incapable of running");
        }

        return this;
    }



    public String getAddress()
    {
        return address;
    }
}
