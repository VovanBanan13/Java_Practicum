package ru.home.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.dto.ToyDto;
import ru.home.mappers.ToyMapper;
import ru.home.models.Toy;
import ru.home.services.BuyService;
import ru.home.services.ToyService;

@RestController
@RequestMapping("/buy")
@Api(value="buy")
public class BuyController {

    private final BuyService buyService;

    @Autowired
    public BuyController(BuyService buyService) {
        this.buyService = buyService;
    }

    @ApiOperation(value = "Add a new buy information")
    @PostMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Double> createBuy(@PathVariable("id") Integer id, @RequestBody List<HashMap<String, Integer>> buyList) {
        double sumAmount = buyService.saveBuy(id, buyList);
        return new ResponseEntity<>(sumAmount, HttpStatus.OK);
    }
}
