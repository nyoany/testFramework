package components;

import services.MockService;

/**
 * Created by OANY on 1/14/2018.
 */
public class Portfolio
{
    private MockService mockService;

    public int getMarketValue(Stock stock)
    {
        return stock.getQuantity() * mockService.getPrice(stock);
    }

    public void setMockService(MockService service)
    {
        this.mockService = service;
    }
}
