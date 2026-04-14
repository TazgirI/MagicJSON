package net.tazgirl.magicjson.statements.objects.minecraft;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForge;
import net.tazgirl.magicjson.statements.StatementInputVarianceHandler;
import net.tazgirl.magicjson.statements.objects.Base;
import net.tazgirl.magicjson.statements.objects.StatementHolder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Teleport extends Base
{
    List<Base> inputs = new ArrayList<>();

    public Teleport(StatementHolder holder)
    {
        super(holder);
    }

    // On success returns the entity, on failure returns null
    @Override
    public Object resolve()
    {
        StatementInputVarianceHandler handler = new StatementInputVarianceHandler(inputs);

        List<Entity> entities = handler.getInstancesOfType(Entity.class);
        Vec3 vec3 = handler.getFirstInstanceOfType(Vec3.class);
        List<Integer> ints = List.of();

        if(vec3 == null && entities.size() == 1)
        {
            ints = handler.getInstancesOfType(Integer.class);
        }

        if(vec3 == null)
        {
            if(ints.size() >= 3)
            {
                vec3 = new Vec3(ints.get(0), ints.get(1), ints.get(2));
            }
            else if(entities.size() >= 2)
            {
                vec3 = entities.get(1).position();
            }
        }

        if(!entities.isEmpty() && vec3 != null)
        {
            entities.getFirst().teleportTo(vec3.x, vec3.y, vec3.z);
            return true;
        }

        return null;
    }

    @Override
    public @NotNull Boolean handleBase(Base base)
    {
        inputs.add(base);
        return true;
    }

    @Override
    public @NotNull Boolean handleUniqueArgument(String string)
    {
        return false;
    }

    @Override
    public Base implicitChild()
    {
        return null;
    }

    @Override
    public @NotNull String setIdentifier()
    {
        return "Teleport";
    }

    @Override
    public String toString()
    {
        return identifier + "( " + inputs.toString() + " )";
    }

    @Override
    public void replace(Base oldBase, Base newBase)
    {
        inputs = replaceInList(oldBase, newBase, inputs);
    }
}
