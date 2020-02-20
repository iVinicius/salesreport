package com.mygit.salesreport;

import com.mygit.salesreport.dao.ClientRepository;
import com.mygit.salesreport.dao.SaleItemRepository;
import com.mygit.salesreport.dao.SaleRepository;
import com.mygit.salesreport.dao.SellerRepository;
import com.mygit.salesreport.entities.Sale;
import com.mygit.salesreport.entities.parsers.ParserMapper;
import com.mygit.salesreport.write.FileWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SalesReportService {

    @Autowired
    private ParserMapper parserMapper;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private FileWriter fileWriter;

    @Async
    public void generateReport(String fileInput){
        // extractor should function as in PROTOTYPE scope
        MessageExtractor extractor = new MessageExtractor(fileInput, parserMapper);

        // saving to in memory database
        persistData(fileInput, extractor);

        // build analytic hashmap
        Map<Long, BigDecimal> salesTotalBySaleId = new HashMap<>();
        Map<String, BigDecimal> salesTotalBySeller = new HashMap<>();

        for(Sale sale : extractor.getSaleList()) {
            BigDecimal totalSale = saleItemRepository.totalSaleValueBySaleId(sale.getSaleId());
            salesTotalBySaleId.put(sale.getSaleId(), totalSale);

            // a vendor might have made more than one sale
            BigDecimal temp = salesTotalBySeller.get(sale.getSellerName());
            BigDecimal inc = temp == null ? totalSale : temp.add(temp);
            salesTotalBySeller.put(sale.getSellerName(), inc);
        }

        // ID da venda mais cara
        Long highestSaleId = returnHighestSaleId(salesTotalBySaleId);

        // O pior vendedor
        String nameOfWorstSeller = returnNameOfWorstSeller(salesTotalBySeller);

        // quantidade de clientes no arquivo de entrada
        Integer clientCount = returnCountOfClients(extractor);

        // quantidade de vendedores no arquivo de entrada
        Integer sellerCount = returnCountOfSellers(extractor);

        // print to output file
        fileWriter.writeToFile(clientCount, sellerCount, highestSaleId, nameOfWorstSeller);

        log.info("Sales Report created successfully!");
    }

    private Long returnHighestSaleId(Map<Long, BigDecimal> salesTotalBySaleId){
        Map<Long, BigDecimal> sortedSale = salesTotalBySaleId.entrySet()
                .stream()
                .sorted(Map.Entry.<Long, BigDecimal>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return sortedSale.entrySet().iterator().next().getKey();
    }

    private String returnNameOfWorstSeller(Map<String, BigDecimal> salesTotalBySeller){
        Map<String, BigDecimal> sortedSeller = salesTotalBySeller.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1, e2) -> e1, LinkedHashMap::new));

        return sortedSeller.entrySet().iterator().next().getKey();

    }

    private Integer returnCountOfClients(MessageExtractor extractor){
        return extractor.getClientList().size();
    }

    private Integer returnCountOfSellers(MessageExtractor extractor){
        return extractor.getSellerList().size();
    }

    private void persistData(String fileInput, MessageExtractor extractor) {
        clientRepository.saveAll(extractor.getClientList());
        saleRepository.saveAll(extractor.getSaleList());
        sellerRepository.saveAll(extractor.getSellerList());
        saleItemRepository.saveAll(extractor.getSaleItemList());
    }

}
