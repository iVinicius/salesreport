package com.mygit.salesreport;

import com.mygit.salesreport.dao.ClientRepository;
import com.mygit.salesreport.dao.SaleItemRepository;
import com.mygit.salesreport.dao.SaleRepository;
import com.mygit.salesreport.dao.SellerRepository;
import com.mygit.salesreport.entities.Client;
import com.mygit.salesreport.entities.Seller;
import com.mygit.salesreport.entities.parsers.*;
import com.mygit.salesreport.write.FileWriter;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = SalesReportService.class)
@Import({ParserMapper.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SalesReportServiceTest extends SalesReportServiceTestMocks {

    @Autowired
    private SalesReportService service;

    private String fileInput = "001ç1234567891234çPedroç50000\n" +
            "001ç3245678865434çPauloç40000.99\n" +
            "002ç2345675434544345çJose da SilvaçRural\n" +
            "002ç2345675433444345çEduardo PereiraçRural\n" +
            "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro\n" +
            "003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo\n";

    @MockBean
    private ClientRepository clientRepository;

    @MockBean
    private SaleRepository saleRepository;

    @MockBean
    private SellerRepository sellerRepository;

    @MockBean
    private SaleItemRepository saleItemRepository;

    @MockBean
    private FileWriter fileWriter;

    @MockBean
    private ClientParser clientParser;

    @MockBean
    private SaleItemParser itemParser;

    @MockBean
    private SaleParser saleParser;

    @MockBean
    private SellerParser sellerParser;

    @Test
    public void testSampleFileInput(){
        when(sellerParser.parseEntity((any()))).thenReturn(new Seller());
        when(clientParser.parseEntity((any()))).thenReturn(new Client());
        when(saleParser.parseEntity((any()))).thenReturn(
                mockSale(10L, "Pedro"), mockSale(8L, "Paulo"));
        when(itemParser.parseEntity((any()))).thenReturn(mockSaleItem());
        when(saleItemRepository.totalSaleValueBySaleId(any())).thenReturn(
                new BigDecimal("500"), new BigDecimal("100"));

        service.generateReport(fileInput);

        verify(clientRepository, times(1)).saveAll(any());
        verify(saleRepository, times(1)).saveAll(any());
        verify(sellerRepository, times(1)).saveAll(any());
        verify(saleItemRepository, times(1)).saveAll(any());

        verify(saleItemRepository, times(2)).totalSaleValueBySaleId(any());
        verify(fileWriter, times(1)).writeToFile(
                2, 2, 10L, "Paulo");
    }
}
