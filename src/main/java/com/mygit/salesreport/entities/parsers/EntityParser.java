package com.mygit.salesreport.entities.parsers;

import com.mygit.salesreport.entities.Entity;

public interface EntityParser {

    Long getFileId();

    Entity parseEntity(String[] message);

}
