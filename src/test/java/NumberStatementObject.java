
import net.tazgirl.magicjson.main.DefaultValues;
import net.tazgirl.magicjson.main.statement_object.numbers.objects.*;
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
        assertEqualsSuper(Integer.valueOf(0), DefaultValues.getDefault(Integer.class),"integerDefault");
    }

    @Test
    void floatDefault()
    {
        assertEqualsSuper(Float.valueOf(0f), DefaultValues.getDefault(Float.class),"floatDefault");
    }

    @Test
    void doubleDefault()
    {
        assertEqualsSuper(Double.valueOf(0.0d), DefaultValues.getDefault(Double.class), "doubleDefault");
    }

    @Test
    void longDefault()
    {
        assertEqualsSuper(Long.valueOf(0L), DefaultValues.getDefault(Long.class), "longDefault");
    }

    @Test
    void integerDefaultResolve()
    {
        assertEqualsSuper(emptyIntegerStatementObject.Resolve(), Integer.valueOf(0), "integerDefaultResolve");
    }

    @Test
    void floatDefaultResolve()
    {
        assertEqualsSuper(emptyFloatStatementObject.Resolve(), Float.valueOf(0f),"floatDefaultResolve");
    }

    @Test
    void doubleDefaultResolve()
    {
        assertEqualsSuper(emptyDoubleStatementObject.Resolve(), Double.valueOf(0.0d),"doubleDefaultResolve");
    }

    @Test
    void longDefaultResolve()
    {
        assertEqualsSuper(emptyLongStatementObject.Resolve(), Long.valueOf(0L),"longDefaultResolve");
    }

}