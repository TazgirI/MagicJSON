

import net.tazgirl.magicjson.main.DefaultValues;
import net.tazgirl.magicjson.main.statement_object.numbers.objects.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class NumberStatementObject extends TestRoot
{
    //DISC: Original first test in each category was hand written and then extruded to the other types by ChatGPT before being reviewed and added


    static IntegerStatementObject emptyIntegerStatementObject = new IntegerStatementObject();
    static DoubleStatementObject emptyDoubleStatementObject = new DoubleStatementObject();
    static FloatStatementObject emptyFloatStatementObject = new FloatStatementObject();
    static LongStatementObject emptyLongStatementObject = new LongStatementObject();

    static List<net.tazgirl.magicjson.main.statement_object.numbers.objects.NumberStatementObject<?>> empties =List.of(emptyIntegerStatementObject, emptyDoubleStatementObject, emptyFloatStatementObject, emptyLongStatementObject);

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
        assertEquals(Integer.valueOf(0), DefaultValues.getDefault(Integer.class),getFunctionFail("integerDefault"));
    }

    @Test
    void floatDefault()
    {
        assertEquals(Float.valueOf(0f), DefaultValues.getDefault(Float.class),getFunctionFail("floatDefault"));
    }

    @Test
    void doubleDefault()
    {
        assertEquals(Double.valueOf(0.0d), DefaultValues.getDefault(Double.class), getFunctionFail("doubleDefault"));
    }

    @Test
    void longDefault()
    {
        assertEquals(Long.valueOf(0L), DefaultValues.getDefault(Long.class), getFunctionFail("longDefault"));
    }

    @Test
    void integerDefaultResolve()
    {
        assertEquals(emptyIntegerStatementObject.Resolve(), Integer.valueOf(0), getFunctionFail("integerDefaultResolve"));
    }

    @Test
    void floatDefaultResolve()
    {
        assertEquals(emptyFloatStatementObject.Resolve(), Float.valueOf(0f),getFunctionFail("floatDefaultResolve"));
    }

    @Test
    void doubleDefaultResolve()
    {
        assertEquals(emptyDoubleStatementObject.Resolve(), Double.valueOf(0.0d),getFunctionFail("doubleDefaultResolve"));
    }

    @Test
    void longDefaultResolve()
    {
        assertEquals(emptyLongStatementObject.Resolve(), Long.valueOf(0L),getFunctionFail("longDefaultResolve"));
    }

}