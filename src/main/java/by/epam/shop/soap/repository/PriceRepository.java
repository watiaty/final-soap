package by.epam.shop.soap.repository;

import by.epam.shop.soap.bean.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends CrudRepository<Price, Integer> {
    List<Price> findByCurrency(String currency);

    List<Price> findByProductId(Integer id);

    List<Price> findByValueGreaterThanAndValueLessThan(Integer from, Integer to);

}
