package by.epam.shop.soap.service;

import by.epam.shop.soap.bean.Price;
import by.epam.shop.soap.repository.PriceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceService {

    public enum Status {
        SUCCESS, FAILURE;
    }

    final PriceRepository priceRepository;

    public PriceService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public List<Price> getPricesByCurrency(String currency) {
        return priceRepository.findByCurrency(currency);
    }

    public List<Price> getPricesByProductId(Integer id) {
        return priceRepository.findByProductId(id);
    }

    public List<Price> getPricesByRange(Integer from, Integer to) {
        return priceRepository.findByValueGreaterThanAndValueLessThan(from, to);
    }

    public void add(Price price) {
        priceRepository.save(price);
    }

    public void update(Price price) {
        priceRepository.save(price);
    }

    public void deleteById(int id) {
        priceRepository.deleteById(id);
    }
}
