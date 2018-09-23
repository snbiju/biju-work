package com.myapp.card.controller;


import com.myapp.card.model.CardDetails;
import com.myapp.card.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Created by Biju Pillai
 */
@RestController
@EnableAutoConfiguration
@RequestMapping("/card")
public class CreditCardController {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardController.class);
    @Autowired
    CreditCardService service;

    @RequestMapping(value ="/add",
            method = RequestMethod.POST )
    public ResponseEntity<CardDetails> addCardDetails(@Valid @RequestBody CardDetails cardDetails) {
        service.add(cardDetails);
        logger.info("Card added succcessfully");
        return new ResponseEntity("Card added succcessfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/list",
            method = RequestMethod.GET)
    public ResponseEntity<CardDetails> getAll() {
        List<CardDetails> cardList = service.getAll();
        if (cardList.isEmpty()) {
            logger.info("No data found");
            return new ResponseEntity("No Data Found", HttpStatus.NOT_FOUND);
        }
        logger.info("Available Card Details :{}",cardList);
        return new ResponseEntity(cardList, HttpStatus.OK);
    }

}
