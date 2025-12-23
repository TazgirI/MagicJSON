package net.tazgirl.magicjson.statements.objects;

import net.tazgirl.magicjson.Logging;
import net.tazgirl.magicjson.processing.Stack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatementHolder
{
    public Map<String, Object> args = new HashMap<>();

    String address;

    Base root = null;

    public List<Base> uniques = new ArrayList<>();
    public Map<Base, List<Base>> parentToChildren = new HashMap<>();
    public Map<Base, Base> childToParent = new HashMap<>();

    Stack constructor;

    public StatementHolder(Stack stack)
    {
        this.constructor = stack;
    }

    public Object Run()
    {
        Object result = root.Resolve();

        args = new HashMap<>();

        return result;
    }

    public Object RunKeepArgs()
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

        constructor = null;

        return this;
    }

    public void clearRelations()
    {
        uniques = new ArrayList<>();
        parentToChildren = new HashMap<>();
        childToParent = new HashMap<>();
    }

    public String getAddress()
    {
        return address;
    }

    public void setArgs(Map<String, Object> args)
    {
        this.args = args;
    }

    public void Replace(Base oldBase, Base newBase)
    {
        constructor.Replace(oldBase, newBase);
        if(!uniques.contains(oldBase)) {return;}

        if(childToParent.containsKey(oldBase))
        {
            Base parent = childToParent.get(oldBase);
            parent.Replace(oldBase, newBase);
            childToParent.remove(oldBase);
            childToParent.put(newBase, parent);
        }

        if(parentToChildren.containsKey(oldBase))
        {
            List<Base> children = parentToChildren.get(oldBase);
            parentToChildren.remove(oldBase);
            List<Base> acceptedChildren = new ArrayList<>();

            for(Base child: children)
            {
                childToParent.remove(child);
                if(newBase.HandleBase(child))
                {
                    acceptedChildren.add(child);
                    childToParent.put(child, newBase);
                }
                else
                {
                    uniques.remove(child);
                }
            }

            parentToChildren.put(newBase, acceptedChildren);
        }
    }
}
