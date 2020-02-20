package com.mygit.salesreport.dao;

import com.mygit.salesreport.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Client> {

}
