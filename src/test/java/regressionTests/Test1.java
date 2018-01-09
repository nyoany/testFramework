package regressionTests;

import org.testng.annotations.Test;
import testUtils.BaseTest;

public class Test1 extends BaseTest
{
    public int aVariable;
    protected String[] strings;
    private int ii;
    @Test
    public void test1()
    {
        getWebDriver().get(getBaseUrl());
    }

    public enum Enum7
    {
        ALB("d"),
        VERDE("");

        String val;
        Enum7(String val)
        {
            this.val = val;
        }

    }
}
