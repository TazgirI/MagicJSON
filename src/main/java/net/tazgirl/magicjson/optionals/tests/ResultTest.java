package net.tazgirl.magicjson.optionals.tests;

public record ResultTest<T>(Class<T> type)
{
    public static final ResultTest<Integer> INTEGER = new ResultTest<>(Integer.class);
    public static final ResultTest<Double> DOUBLE = new ResultTest<>(Double.class);
    public static final ResultTest<Float> FLOAT = new ResultTest<>(Float.class);
    public static final ResultTest<Long> LONG = new ResultTest<>(Long.class);
    public static final ResultTest<Boolean> BOOLEAN = new ResultTest<>(Boolean.class);
    public static final ResultTest<String> STRING = new ResultTest<>(String.class);

    public boolean test(Object input)
    {
        return type.isInstance(input);
    }

}
