import net.tazgirl.magicjson.processing.Tokenisation;
import net.tazgirl.magicjson.processing.TokensToHolder;
import org.junit.jupiter.api.Test;

public class QuickTests
{

    @Test
    void quickTest()
    {
        Print(new Tokenisation("AND(true false)").TokeniseStatement().toString());
    }

    static void Print(String string)
    {
        System.out.println(string);
    }
}
