package com.gymnasticbodies.com.currencyconversionhistoryservice;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserHistory {
    @Id
    @GeneratedValue
    @NonNull private long id;
    @Column(name = "currency_from")
    @NonNull private String from;
    @Column(name = "currency_to")
    @NonNull private  String to;
    @NonNull private BigDecimal conversion_rate;
    @NonNull private BigDecimal quantity;
    @NonNull private BigDecimal calculated_amount;
}


