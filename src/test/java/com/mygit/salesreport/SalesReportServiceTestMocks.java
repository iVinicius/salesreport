package com.mygit.salesreport;

import com.mygit.salesreport.entities.Sale;
import com.mygit.salesreport.entities.SaleItem;
import com.mygit.salesreport.entities.SaleItemId;

public class SalesReportServiceTestMocks {

    Sale mockSale(Long saleId, String name){
        return Sale.builder()
                .saleId(saleId)
                .sellerName(name)
                .build();
    }

    SaleItem mockSaleItem(){
        return SaleItem.builder()
                .id(SaleItemId.builder()
                        .build())
                .build();
    }
}
