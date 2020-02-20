package com.mygit.salesreport.dao;

import com.mygit.salesreport.entities.SaleItem;
import com.mygit.salesreport.entities.SaleItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SaleItemRepository extends JpaRepository<SaleItem, SaleItemId> {

    @Query(value = "SELECT SUM(total) from (\n" +
            "  SELECT s.SALE_ID as sale_id, (si.PRICE*si.QUANTITY) as total from sale_t s\n" +
            "\t\t\tjoin sale_item_t si on s.SALE_ID = si.SALE_ID\n" +
            "  where s.SALE_ID = :saleId\n" +
            "  ) xis", nativeQuery = true)
    BigDecimal totalSaleValueBySaleId(Long saleId);
}
