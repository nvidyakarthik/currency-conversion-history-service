package com.gymnasticbodies.com.currencyconversionhistoryservice;

import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
public class CurrencyConversionHistoryController {

    @Autowired
    UserHistoryRepository userHistoryRepository;
    @Autowired
    CurrencyConversionServiceProxy currencyConversionServiceProxy;

    @GetMapping("/lists")
    public List<UserHistory> retrieveAllCurrencyExchangeList() {
        return userHistoryRepository.findAll();
    }

    @DeleteMapping("/lists/{id}")
    public void deleteCurrencyExchangeRecord(@PathVariable Long id) {
        userHistoryRepository.deleteById(id);
    }

    @PostMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<UserHistory> createCurrencyExchangeRecord(@PathVariable String from,@PathVariable String to,@PathVariable BigDecimal quantity) {
        CurrencyConversionBean response=currencyConversionServiceProxy.retrieveExchangeValue(from,to,quantity);
        UserHistory currencyExchangeRecord=new UserHistory();
        currencyExchangeRecord.setTo(to);
        currencyExchangeRecord.setFrom(from);
        currencyExchangeRecord.setQuantity(quantity);
        currencyExchangeRecord.setConversion_rate(response.getConversionMultiple());
        currencyExchangeRecord.setCalculated_amount(response.getTotalCalculatedAmount());
        UserHistory savedUserHistory = userHistoryRepository.save(currencyExchangeRecord);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUserHistory.getId())
                .toUri();
        return new ResponseEntity<UserHistory>(savedUserHistory,HttpStatus.OK);
        //return new ResponseEntity<CurrencyConversionBean>(new CurrencyConversionBean(response.getId(),currencyExchangeRecord.getFrom(),currencyExchangeRecord.getTo(),response.getConversionMultiple(),quantity,quantity.multiply(response.getConversionMultiple()),response.getPort()),HttpStatus.OK);


    }
    @PutMapping("/lists")
    public ResponseEntity<UserHistory> updateHistoryRecord(@RequestBody UserHistory userHistory) {
        userHistoryRepository.save(userHistory);
        return new ResponseEntity<UserHistory>(userHistory, HttpStatus.OK);
    }

}
