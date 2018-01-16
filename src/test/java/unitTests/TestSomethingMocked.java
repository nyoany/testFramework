package unitTests;

import services.MockService;
import components.Portfolio;
import components.Stock;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by OANY on 1/14/2018.
 */
public class TestSomethingMocked {

    MockService mockService;
    Portfolio portfolio;

    @Test
    public void mockedTest()
    {
        portfolio = new Portfolio();
        mockService = mock(MockService.class);
        portfolio.setMockService(mockService);

        Stock googleStock = new Stock("1",10);
        Stock irelandStock = new Stock("2",100);

        when(mockService.getPrice(googleStock)).thenReturn(5000);
        when(mockService.getPrice(irelandStock)).thenReturn(1000);

        assertEquals(50000, portfolio.getMarketValue(googleStock));
        assertEquals(100000, portfolio.getMarketValue(irelandStock));
    }
}
