package net.tazgirl.magicjson.memory;

import net.tazgirl.magicjson.statements.objects.StatementHolder;

public class Pointer
{
    final String name;
    final Location location;
    final MemoryNamespace memoryNamespace;
    final MemorySpace memorySpace;

    public Pointer(String name, Location location, MemoryNamespace memoryNamespace, MemorySpace memorySpace)
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

    public enum MemoryNamespace
    {
        LOCAL,
        GLOBAL
    }

    public enum MemorySpace
    {
        RUNTIME,
        WORLD
    }
}
