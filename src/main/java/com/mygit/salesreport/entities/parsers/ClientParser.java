package com.mygit.salesreport.entities.parsers;

import com.mygit.salesreport.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientParser implements EntityParser {

    @Override
    public Long getFileId() {
        return Client.fileId;
    }

    @Override
    public Client parseEntity(String[] message) {
        // 002ç2345675434544345çJose da SilvaçRural
        return Client.builder()
                .cnpj(Long.valueOf(message[1]))
                .name(message[2])
                .businessArea(message[3])
                .build();
    }
}
