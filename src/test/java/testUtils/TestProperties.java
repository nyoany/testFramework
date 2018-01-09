package testUtils;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;

@Resource.Classpath("test.properties")
public class TestProperties
{
    public TestProperties()
    {
        PropertyLoader.populate(this);
    }

    @Property("url")
    private String url;
    @Property("browser")
    private String browser;

    public String getUrl()
    {
        return url;
    }

    public String getBrowser()
    {
        return browser;
    }
}
