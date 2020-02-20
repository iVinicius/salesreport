package com.mygit.salesreport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "SALE_T")
@EqualsAndHashCode
public class Sale extends Entity {

    public final static Long fileId = 3L;

    @Id
    @Column(name = "SALE_ID")
    private Long saleId;

    @Column(name = "SELLER_NAME")
    private String sellerName;
}
