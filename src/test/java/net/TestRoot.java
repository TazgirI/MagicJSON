package net;

public class TestRoot
{
    public String getFunctionFail(String method)
    {
        return this.toString().substring(0,this.toString().indexOf("@")) + "." + method + " has failed";
    }
}
