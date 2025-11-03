
import net.tazgirl.magicjson.main.DefaultValues;
import net.tazgirl.magicjson.main.statement_object.numbers.objects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberStatementObjectTests
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
    void testIntegerDefault()
    {
        assertEquals(Integer.valueOf(0), DefaultValues.getDefault(Integer.class));
    }

    @Test
    void testFloatDefault()
    {
        assertEquals(Float.valueOf(0f), DefaultValues.getDefault(Float.class));
    }

    @Test
    void testDoubleDefault()
    {
        assertEquals(Double.valueOf(0.0d), DefaultValues.getDefault(Double.class));
    }

    @Test
    void testLongDefault()
    {
        assertEquals(Long.valueOf(0L), DefaultValues.getDefault(Long.class));
    }

    @Test
    void testIntegerDefaultResolve()
    {
        assertEquals(emptyIntegerStatementObject.Resolve(), Integer.valueOf(0));
    }

    @Test
    void testFloatDefaultResolve()
    {
        assertEquals(emptyFloatStatementObject.Resolve(), Float.valueOf(0f));
    }

    @Test
    void testDoubleDefaultResolve()
    {
        assertEquals(emptyDoubleStatementObject.Resolve(), Double.valueOf(0.0d));
    }

    @Test
    void testLongDefaultResolve()
    {
        assertEquals(emptyLongStatementObject.Resolve(), Long.valueOf(0L));
    }

    static void Print(String message)
    {
        System.out.println(message);
    }
}