package com.mygit.salesreport.entities.parsers;

import com.mygit.salesreport.entities.Entity;
import com.mygit.salesreport.entities.Sale;
import org.springframework.stereotype.Component;

@Component
public class SaleParser implements EntityParser {
    @Override
    public Long getFileId() {
        return Sale.fileId;
    }

    @Override
    public Entity parseEntity(String[] message) {
        // 003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
        return Sale.builder()
                .saleId(Long.valueOf(message[1]))
                .sellerName(message[3])
                .build();
    }
}
