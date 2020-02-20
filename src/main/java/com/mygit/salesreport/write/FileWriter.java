package com.mygit.salesreport.write;

import com.mygit.salesreport.SalesReportConstants;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class FileWriter {

    public void writeToFile(Integer clientQty, Integer sellerQty, Long highestSaleId, String worstSellerName) {
        java.io.FileWriter fileWriter = null;
        try {
            fileWriter = new java.io.FileWriter(SalesReportConstants.OUTPUT_FILE_DIRECTORY_PATH + SalesReportConstants.OUTPUT_FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.printf("Quantidade de clientes no arquivo de entrada: %d.\n", clientQty);
        printWriter.printf("QUantidade de vendedores no arquivo de entrada: %d.\n", sellerQty);
        printWriter.printf("Venda ID %d foi a mais cara.\n", highestSaleId);
        printWriter.printf("%s foi o pior vendedor.", worstSellerName);

        printWriter.close();
    }
}
