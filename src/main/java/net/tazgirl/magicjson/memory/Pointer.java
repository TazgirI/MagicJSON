package net.tazgirl.magicjson.memory;

import net.tazgirl.magicjson.statements.objects.StatementHolder;
import net.tazgirl.magicjson.statements.objects.memory.MemoryObject;

public class Pointer
{
    final String name;
    final Location location;
    final MemoryObject.Namespace memoryNamespace;
    final MemoryObject.Space memorySpace;

    public Pointer(String name, Location location, MemoryObject.Namespace memoryNamespace, MemoryObject.Space memorySpace)
    {
        this.name = name;
        this.location = location;
        this.memoryNamespace = memoryNamespace;
        this.memorySpace = memorySpace;
    }

    public Object fetch(StatementHolder holder)
    {
        if(location == Location.ARGS)
        {
            return holder.args.get(name);
        }


    }

    public enum Location
    {
        ARGS,
        MEMORY
    }
}
