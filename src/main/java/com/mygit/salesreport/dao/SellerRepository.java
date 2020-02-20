package com.mygit.salesreport.dao;

import com.mygit.salesreport.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Seller> {

}
