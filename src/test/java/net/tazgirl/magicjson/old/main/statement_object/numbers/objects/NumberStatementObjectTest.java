package net.tazgirl.magicjson.old.main.statement_object.numbers.objects;


import net.tazgirl.magicjson.old.main.DefaultValues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class NumberStatementObjectTest
{
    //DISC: Original first test in each category was hand written and then extruded to the other types by ChatGPT before being reviewed and added


    static IntegerStatementObject emptyIntegerStatementObject = new IntegerStatementObject();
    static DoubleStatementObject emptyDoubleStatementObject = new DoubleStatementObject();
    static FloatStatementObject emptyFloatStatementObject = new FloatStatementObject();
    static LongStatementObject emptyLongStatementObject = new LongStatementObject();

    static List<NumberStatementObject<?>> empties =List.of(emptyIntegerStatementObject, emptyDoubleStatementObject, emptyFloatStatementObject, emptyLongStatementObject);

    @BeforeEach
    void FillEmpties()
    {
        emptyIntegerStatementObject = new IntegerStatementObject();
        emptyDoubleStatementObject = new DoubleStatementObject();
        emptyFloatStatementObject = new FloatStatementObject();
        emptyLongStatementObject = new LongStatementObject();

    }


    @Test
    void integerDefault()
    {
        Assertions.assertEquals(Integer.valueOf(0), DefaultValues.getDefault(Integer.class));
    }

    @Test
    void floatDefault()
    {
        Assertions.assertEquals(Float.valueOf(0f), DefaultValues.getDefault(Float.class));
    }

    @Test
    void doubleDefault()
    {
        Assertions.assertEquals(Double.valueOf(0.0d), DefaultValues.getDefault(Double.class));
    }

    @Test
    void longDefault()
    {
        Assertions.assertEquals(Long.valueOf(0L), DefaultValues.getDefault(Long.class));
    }

    @Test
    void integerDefaultResolve()
    {
        Assertions.assertEquals(emptyIntegerStatementObject.Resolve(), Integer.valueOf(0));
    }

    @Test
    void floatDefaultResolve()
    {
        Assertions.assertEquals(emptyFloatStatementObject.Resolve(), Float.valueOf(0f));
    }

    @Test
    void doubleDefaultResolve()
    {
        Assertions.assertEquals(emptyDoubleStatementObject.Resolve(), Double.valueOf(0.0d));
    }

    @Test
    void longDefaultResolve()
    {
        Assertions.assertEquals(emptyLongStatementObject.Resolve(), Long.valueOf(0L));
    }

}