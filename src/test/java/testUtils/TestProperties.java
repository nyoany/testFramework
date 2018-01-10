package testUtils;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties
{
    Properties configProp;

    public TestProperties()
    {
        configProp = new Properties();
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(System.getProperty("testProperties"));
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertyLoader.populate(configProp);
    }

    public String getUrl()
    {
        return configProp.getProperty("url");
    }

    public String getBrowser()
    {
        return configProp.getProperty("browser");
    }
}
