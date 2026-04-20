package net.tazgirl.magicjson.statements.objects;

import net.tazgirl.magicjson.MJLogging;
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

    public StatementHolder(TEST_CASE testCase)
    {

    }


    public Object run()
    {
        Object result = root.resolve();

        clearArgs();

        return result;
    }

    public PrivateCore.StatementResultAndArgs runAndReturnFinalArgs()
    {
        Object result = root.resolve();
        PrivateCore.StatementResultAndArgs returnValue = new PrivateCore.StatementResultAndArgs(result, new HashMap<>(args));

        clearArgs();

        return returnValue;
    }

    public Object runKeepArgs()
    {
        return root.resolve();
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
            MJLogging.debug("The StatementHolder for \"" + address + "\" has failed to find an acceptable root and is incapable of running");
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

    public Object addArg(String string, Object object)
    {
        return args.put(string, object);
    }

    public void clearArgs()
    {
        args = new HashMap<>();
    }

    public void Replace(Base oldBase, Base newBase)
    {
        constructor.Replace(oldBase, newBase);
        if(!uniques.contains(oldBase)) {return;}

        if(childToParent.containsKey(oldBase))
        {
            Base parent = childToParent.get(oldBase);
            parent.replace(oldBase, newBase);
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
                if(newBase.handleBase(child))
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

    public enum TEST_CASE
    {
        THIS_IS_A_TEST_OPTION_ONLY_FOR_CONTROLLED_SITUATIONS
    }
}
