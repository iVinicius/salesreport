package com.mygit.salesreport.entities.parsers;

import com.mygit.salesreport.entities.Client;
import com.mygit.salesreport.entities.Sale;
import com.mygit.salesreport.entities.SaleItem;
import com.mygit.salesreport.entities.Seller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ParserMapper {

    private final Map mapper = new HashMap<Long, EntityParser>();

    @Autowired
    private ClientParser clientParser;

    @Autowired
    private SaleItemParser itemParser;

    @Autowired
    private SaleParser saleParser;

    @Autowired
    private SellerParser sellerParser;

    @PostConstruct
    private void initMappers() {
        mapper.put(Seller.fileId, sellerParser);
        mapper.put(Client.fileId, clientParser);
        mapper.put(Sale.fileId, saleParser);
        mapper.put(SaleItem.fileId, itemParser);
    }

    public EntityParser loadAction(Long fileId) {
        if (!mapper.containsKey(fileId)) {
            log.error(":) LOL unmapped action happened.");
            throw new IllegalStateException(String.format("Cannot load actions. Unknown type: %s", fileId));
        }
        return (EntityParser) mapper.get(fileId);
    }
}
