package com.mygit.salesreport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "SELLER_T")
public class Seller extends Entity {

    public static final Long fileId = 1L;

    @Id
    @Column(name = "CPF")
    private Long cpf;

    @Column(name = "NAME")
    private String name;

    private BigDecimal salary;
}
