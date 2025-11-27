package net.tazgirl.magicjson.old.memory;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.saveddata.SavedData;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

import static net.tazgirl.magicjson.old.Constants.server;

@ParametersAreNonnullByDefault
public class WorldMemory extends SavedData
{
    static Map<String, String> worldMemory = new HashMap<>();

    public static WorldMemory create()
    {
        return new WorldMemory();
    }

    @Override
    public CompoundTag save(CompoundTag compoundTag, HolderLookup.Provider provider)
    {
        ListTag listTag = new ListTag();

        worldMemory.forEach((key, value) ->
        {
            CompoundTag tag = new CompoundTag();
            tag.putString("key",key);
            tag.putString("value",value);
            listTag.add(tag);
        });

        compoundTag.put("WorldMemory",listTag);
        return compoundTag;
    }

    public static WorldMemory load(CompoundTag compoundTag, HolderLookup.Provider lookupProvider)
    {
        WorldMemory worldMemoryReturn = new WorldMemory();

        ListTag listTag = compoundTag.getList("WorldMemory",10);

        for(int i = 0; i < listTag.size(); i++)
        {
            CompoundTag tag = listTag.getCompound(i);
            worldMemory.put(tag.get("key").getAsString(),tag.get("value").getAsString());
        }

        return worldMemoryReturn;
    }

    public static WorldMemory getForOverworld()
    {
        return server.overworld().getDataStorage().computeIfAbsent(new SavedData.Factory<>(WorldMemory::create, WorldMemory::load), "WorldMemory");
    }

    public void putMemory(String key, String value)
    {
        worldMemory.put(key, value);
        this.setDirty();
    }

    public Boolean editMemory(String key, String newValue)
    {
        for(Map.Entry<String,String> entry: worldMemory.entrySet())
        {
            if(entry.getKey().equals(key))
            {
                entry.setValue(newValue);
                this.setDirty();
                return true;
            }
        }

        return false;
    }

    public Object getMemory(String key)
    {
        return worldMemory.get(key);
    }

}
