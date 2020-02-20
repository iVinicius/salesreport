package com.mygit.salesreport.entities.parsers;

import com.mygit.salesreport.entities.Entity;
import com.mygit.salesreport.entities.Seller;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SellerParser implements EntityParser {

    @Override
    public Long getFileId() {
        return Seller.fileId;
    }

    @Override
    public Entity parseEntity(String[] message) {
        //001ç3245678865434çPauloç40000.99
        return Seller.builder()
                .cpf(Long.valueOf(message[1]))
                .salary(new BigDecimal(message[3]))
                .name(message[2])
                .build();
    }
}
