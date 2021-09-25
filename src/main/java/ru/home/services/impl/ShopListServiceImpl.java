package ru.home.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.exceptions.ObjectNotFoundAdvice;
import ru.home.models.ShopList;
import ru.home.repositories.ShopListRepository;
import ru.home.services.ShopListService;

@Service
@Transactional
public class ShopListServiceImpl implements ShopListService {

    private final ShopListRepository shopListRepository;

    @Autowired
    public ShopListServiceImpl(ShopListRepository shopListRepository) {
        this.shopListRepository = shopListRepository;
    }

    @Override
    public List<ShopList> findAllShopLists() {
        List<ShopList> shopLists = shopListRepository.findAll();
        if(shopLists.isEmpty()) {
            throw new ObjectNotFoundAdvice();
        } else
            return shopLists;
    }

    @Override
    public ShopList getById(int id) {
        return shopListRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
    }

    @Override
    public void save(ShopList shopList) {
        if(shopList.getOrder()==null || shopList.getToy()==null)
            throw new ObjectNotFoundAdvice();
        this.shopListRepository.save(shopList);
    }

    @Override
    public void delete(int id) {
        shopListRepository.deleteById(id);
    }
}
