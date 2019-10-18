package com.gymnasticbodies.com.currencyconversionhistoryservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name="netflix-zuul-api-gateway-server")
//@RibbonClient(name="currency-conversion-service")
public interface CurrencyConversionServiceProxy {

    @GetMapping("/currency-conversion-service/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity);


}

