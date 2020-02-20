package com.mygit.salesreport;

import com.mygit.salesreport.entities.*;
import com.mygit.salesreport.entities.parsers.ParserMapper;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MessageExtractor {

    private List<Client> clientList;
    private List<Sale> saleList;
    private List<Seller> sellerList;
    private List<SaleItem> saleItemList;

    private ParserMapper parserMapper;

    public MessageExtractor(String message, ParserMapper parserMapper){
        clientList = new ArrayList<>();
        saleList = new ArrayList<>();
        sellerList = new ArrayList<>();
        saleItemList = new ArrayList<>();
        this.parserMapper = parserMapper;

        extractDataFromMessage(message);
    }

    private void extractDataFromMessage(String message){
        InputStream stream = new ByteArrayInputStream(message.getBytes());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))){
            String line;
            while ((line = br.readLine()) != null){
                String[] parsedLine = line.split("ç");

                extractEntities(parsedLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractEntities(String[] parsedLine) {
        Long fileId = Long.valueOf(parsedLine[0]);
        if(fileId == Sale.fileId) {
            extractSale(parsedLine);
        } else {
            Entity parsedEntity = parserMapper.loadAction(fileId).parseEntity(parsedLine);

            boolean b = fileId == Client.fileId ? clientList.add((Client) parsedEntity) :
                    fileId == Seller.fileId ? sellerList.add((Seller) parsedEntity) : false;
        }
    }

    private void extractSale(String[] parsedLine) {
        Sale sale = (Sale) parserMapper.loadAction(Sale.fileId).parseEntity(parsedLine);
        saleList.add(sale);

        extractSaleItems(parsedLine, sale);
    }

    private void extractSaleItems(String[] parsedLine, Sale sale) {
        String[] parsedSaleItems = parsedLine[2].split(",");
        for(int i = 0; i < parsedSaleItems.length; i++){
            String[] parsedItem = parsedSaleItems[i]
                    .replace("[", "")
                    .replace("]", "")
                    .split("-");

            SaleItem parsedEntity = (SaleItem) parserMapper.loadAction(SaleItem.fileId).parseEntity(parsedItem);
            parsedEntity.getId().setSaleId(sale.getSaleId());
            saleItemList.add(parsedEntity);
        }
    }
}
