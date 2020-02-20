package com.mygit.salesreport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Builder
@Embeddable
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemId implements Serializable {

    //Sale
    @Column(name = "SALE_ID")
    private Long saleId;

    //Item
    @Column(name = "ITEM_ID")
    private Long itemId;

}
