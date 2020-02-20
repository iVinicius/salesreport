package com.mygit.salesreport.dao;

import com.mygit.salesreport.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Sale> {

}
