package net.tazgirl.magicjson.game_tests;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.tazgirl.magicjson.data.Constants;
import net.tazgirl.magicjson.registration.registers.base.Register;




import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class AllRegistersTests extends TestRoot
{
    static
    {
        myTests.add(AllRegistersTests::freshEventNotNull);
    }





    public static List<Component> freshEventNotNull()
    {
        List<Component> returnComponents = new ArrayList<>();
        for(Register<?> register: Constants.allRegisters)
        {
            if(register.getFreshStaticEvent() == null)
            {
                returnComponents.add(Component.literal(register.identifier + " returned null as it's event").setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
            }
        }

        if (returnComponents.isEmpty())
        {
            returnComponents.add(Component.literal("All Registers returned non-null events").setStyle(Style.EMPTY.withColor(ChatFormatting.GREEN)));
        }

        return returnComponents;
    }


    public static  Register<?> classToObject(Class<? extends Register<?>> inputClass)
    {
        try
        {
            return inputClass.getDeclaredConstructor().newInstance();
        }
        catch (InvocationTargetException e)
        {
            throw new RuntimeException(e);
        }
        catch (InstantiationException e)
        {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
        catch (NoSuchMethodException e)
        {
            throw new RuntimeException(e);
        }
    }
}
