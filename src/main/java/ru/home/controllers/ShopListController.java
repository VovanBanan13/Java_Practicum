package ru.home.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.dto.ShopListDto;
import ru.home.mappers.ShopListMapper;
import ru.home.models.ShopList;
import ru.home.services.ShopListService;

@RestController
@RequestMapping("/shop_list")
@Api(value="shop_list")
public class ShopListController {
    private final ShopListService shopListService;
    private final ShopListMapper shopListMapper;

    @Autowired
    public ShopListController(ShopListService shopListService, ShopListMapper shopListMapper) {
        this.shopListService = shopListService;
        this.shopListMapper = shopListMapper;
    }

    @ApiOperation(value = "View a list of shop lists")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ShopListDto>> getAllShopLists() {
        List<ShopList> shopLists = shopListService.findAllShopLists();

        return new ResponseEntity<>(shopListMapper.toShopListDtos(shopLists), HttpStatus.OK);
    }

    @ApiOperation(value = "View information about the shop list")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ShopListDto> getShopList(@PathVariable("id") Integer id) {
        ShopList shopList = shopListService.getById(id);

        return new ResponseEntity<>(shopListMapper.entityToDto(shopList), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new shop list")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ShopListDto> createShopList(@RequestBody ShopList shopList) {
        shopListService.save(shopList);

        return new ResponseEntity<>(shopListMapper.entityToDto(shopList), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update shop list information")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ShopListDto> updateShopList(@PathVariable("id") Integer id, @RequestBody ShopList shopList) {
        ShopList changedShopList = shopListService.update(id, shopList);

        return new ResponseEntity<>(shopListMapper.entityToDto(changedShopList), HttpStatus.OK);
    }

    @ApiOperation(value = "Remote shop list")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ShopListDto> deleteShopList(@PathVariable("id") Integer id) {
        ShopList shopList = shopListService.getById(id);

        shopListService.delete(id);

        return new ResponseEntity<>(shopListMapper.entityToDto(shopList),HttpStatus.NO_CONTENT);
    }
}
