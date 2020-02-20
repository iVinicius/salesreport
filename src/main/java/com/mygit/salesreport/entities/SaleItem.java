package com.mygit.salesreport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "SALE_ITEM_T")
public class SaleItem extends Entity {

    public static final Long fileId = -1L;

    @EmbeddedId
    private SaleItemId id;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "PRICE")
    private BigDecimal price;
}
