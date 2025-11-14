import org.junit.jupiter.api.Assertions;

public class TestRoot
{
    public void assertEqualsSuper(Object expected, Object actual, String method)
    {
        Assertions.assertEquals(expected, actual, getFunctionFail(method));
        System.out.println(getFunctionSuccess(method));
    }

    public String getFunctionFail(String method)
    {
        return this.toString().substring(0,this.toString().indexOf("@")) + "." + method + " has failed";
    }

    public String getFunctionSuccess(String method)
    {
        return this.toString().substring(0,this.toString().indexOf("@")) + "." + method + " has succeeded";
    }

}
