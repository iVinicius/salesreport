package com.mygit.salesreport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FilesQueueListener {

    @Autowired
    private SalesReportService service;

    @RabbitListener(queues = "filesQueue")
    public void onMessage(String message){
        log.info(message);
        service.generateReport(message);
    }

}
