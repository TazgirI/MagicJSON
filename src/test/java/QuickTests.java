import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuickTests
{

    @Test
    void quickTest()
    {
        String numString = "11.2";
        Assertions.assertFalse(numString.chars().anyMatch(num -> !Character.isDigit(num) && ((char) num) != '.'));
    }

    static void Print(String string)
    {
        System.out.println(string);
    }
}
