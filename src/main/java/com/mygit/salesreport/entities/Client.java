package com.mygit.salesreport.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "CLIENT_T")
public class Client extends Entity {

    public final static Long fileId = 2L;

    @Id
    @Column(name = "CNPJ")
    private Long cnpj;

    private String name;

    private String businessArea;
}
