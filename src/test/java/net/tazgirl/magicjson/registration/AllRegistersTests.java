package net.tazgirl.magicjson.registration;

import net.tazgirl.magicjson.registration.registers.base.Register;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class AllRegistersTests
{
    static List<Class<? extends Register<?>>> registerClasses = List.of();

    @Test
    void freshEventNotNull()
    {
        for(Class<? extends Register<?>> registerClass: registerClasses)
        {
            Assert
        }
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
