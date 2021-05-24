package by.epam.shop.soap;

import by.epam.shop.price.*;
import by.epam.shop.soap.bean.Price;
import by.epam.shop.soap.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class PriceEndpoint {

    @Autowired
    PriceService priceService;

    @PayloadRoot(namespace = "http://localhost:8081", localPart = "GetPriceByRangeRequest")
    @ResponsePayload
    public GetPriceByRangeResponse processPricesByRangeRequest(@RequestPayload GetPriceByRangeRequest request) {

        GetPriceByRangeResponse response = new GetPriceByRangeResponse();
        List<Price> prices = priceService.getPricesByRange(request.getFrom(), request.getTo());
        for (Price price : prices) {
            response.getPriceDetails().add(mapPrice(price));
        }
        return response;
    }

    @PayloadRoot(namespace = "http://localhost:8081", localPart = "GetPricesByCurrencyRequest")
    @ResponsePayload
    public GetPricesByCurrencyResponse processPricesByCurrencyRequest(@RequestPayload GetPricesByCurrencyRequest request) {

        List<Price> prices = priceService.getPricesByCurrency(request.getCurrency());

        GetPricesByCurrencyResponse response = new GetPricesByCurrencyResponse();
        for (Price price : prices) {
            response.getPriceDetails().add(mapPrice(price));
        }
        return response;
    }

    @PayloadRoot(namespace = "http://localhost:8081", localPart = "GetPricesByProductRequest")
    @ResponsePayload
    public GetPricesByProductResponse processPricesByProductRequest(@RequestPayload GetPricesByProductRequest request) {
        System.out.println(request.getProductDetails().getId());
        List<Price> prices = priceService.getPricesByProductId(request.getProductDetails().getId());
        System.out.println(prices);
        GetPricesByProductResponse response = new GetPricesByProductResponse();
        for (Price price : prices) {
            response.getPriceDetails().add(mapPrice(price));
        }
        return response;
    }

    @PayloadRoot(namespace = "http://localhost:8081", localPart = "DeletePriceRequest")
    @ResponsePayload
    public DeletePriceResponse processDeletePriceRequest(@RequestPayload DeletePriceRequest request) {
        priceService.deleteById(request.getId());
        return new DeletePriceResponse();
    }

    @PayloadRoot(namespace = "http://localhost:8081", localPart = "AddPriceRequest")
    @ResponsePayload
    public AddPriceResponse processAddPriceRequest(@RequestPayload AddPriceRequest request) {
        Price price = new Price();
        price.setCurrency(request.getCurrency());
        price.setValue(request.getValue());
        price.setProductId(request.getProductId());
        priceService.add(price);
        return new AddPriceResponse();
    }

    @PayloadRoot(namespace = "http://localhost:8081", localPart = "UpdatePriceRequest")
    @ResponsePayload
    public UpdatePriceResponse processUpdatePriceRequest(@RequestPayload UpdatePriceRequest request) {
        Price price = new Price();
        price.setId(request.getId());
        price.setCurrency(request.getCurrency());
        price.setValue(request.getValue());
        price.setProductId(request.getProductId());
        priceService.update(price);
        return new UpdatePriceResponse();
    }

    private PriceDetails mapPrice(Price price) {
        PriceDetails priceDetails = new PriceDetails();
        priceDetails.setId(price.getId());
        priceDetails.setCurrency(price.getCurrency());
        priceDetails.setValue(price.getValue());
        return priceDetails;
    }
}
