package net.tazgirl.magicjson.memory;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.saveddata.SavedData;
import net.tazgirl.magicjson.MJLogging;
import net.tazgirl.magicjson.data.Constants;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class WorldMemory extends SavedData
{
    final static String tagName = "MagicJsonWorldMemory";
    private final Map<String, Object> worldMemoryMap = new HashMap<>();

    public static WorldMemory create()
    {
        return new WorldMemory();
    }

    @Override
    public @NotNull CompoundTag save(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider provider)
    {
        ListTag listTag = new ListTag();


        CompoundTag pairTag;
        String address;
        String object;

        for(Map.Entry<String, Object> entry: worldMemoryMap.entrySet())
        {
            pairTag = new CompoundTag();
            address = entry.getKey();
            object = WorldMemoryTransformer.objToString(entry.getValue());

            if(object == null)
            {
                MJLogging.Debug("Attempted to put unacceptable type into world memory, entry has not been saved and will be lost when server closes");
                continue;
            }

            pairTag.putString("address",address);
            pairTag.putString("object", object);

            listTag.add(pairTag);
        }

        tag.put(tagName, listTag);
        return tag;
    }

    public static WorldMemory load(CompoundTag tag, HolderLookup.Provider lookupProvider)
    {
        WorldMemory worldMemory = new WorldMemory();

        ListTag listTag = tag.getList(tagName, 10);

        CompoundTag pairTag;

        String address;
        Object object;

        for (int i = 0; i < listTag.size(); i++)
        {
            pairTag = listTag.getCompound(i);

            address = pairTag.getString("address");
            object = WorldMemoryTransformer.stringToObj(pairTag.getString("object"));

            if(object == null)
            {
                continue;
            }

            worldMemory.worldMemoryMap.put(address, object);
        }

        return worldMemory;
    }

    public Map<String, Object> getWorldMemoryMap()
    {
        return worldMemoryMap;
    }

    Boolean addObject(String address, Object object)
    {
        if(WorldMemoryTransformer.classPrefixes.containsKey(object.getClass()))
        {
            worldMemoryMap.put(address, object);
            this.setDirty();
            return true;
        }

        MJLogging.Warn("Attempted to put unconvertable type in WorldMemory");
        return false;
    }

    void clearNamespaceMemory(String namespace)
    {
        for(String adress: worldMemoryMap.keySet())
        {
            if(adress.startsWith(namespace))
            {
                worldMemoryMap.remove(adress);
            }
        }
        this.setDirty();
    }

    public static Boolean isTypeAcceptable(Object object)
    {
        return WorldMemoryTransformer.classPrefixes.containsKey(object.getClass());
    }

    public static WorldMemory getForOverworld()
    {
        return Constants.server.overworld().getDataStorage().computeIfAbsent(new SavedData.Factory<>(WorldMemory::create, WorldMemory::load), tagName);
    }
}
