package com.mygit.salesreport.entities.parsers;

import com.mygit.salesreport.entities.Entity;
import com.mygit.salesreport.entities.SaleItem;
import com.mygit.salesreport.entities.SaleItemId;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SaleItemParser implements EntityParser {
    @Override
    public Long getFileId() {
        return SaleItem.fileId;
    }

    @Override
    public Entity parseEntity(String[] message) {
        //003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
        //[1-10-100,2-30-2.50,3-40-3.10]
        //1-10-100
        return SaleItem.builder()
                .id(SaleItemId.builder()
                        .itemId(Long.valueOf(message[0]))
                        .build())
                .quantity(Long.valueOf(message[1]))
                .price(new BigDecimal(message[2]))
                .build();
    }
}
