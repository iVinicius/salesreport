package com.mygit.salesreport.read;

import com.mygit.salesreport.SalesReportConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class InputService {

    @Autowired
    private FileReader fileReader;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Async
    public void convertAndEnqueue(File inputFile) {
        enqueueMessage(fileReader.readFile(inputFile));
    }

    private void enqueueMessage(String message){
        rabbitTemplate.convertAndSend(SalesReportConstants.RMQ_QUEUE_NAME, message);
    }

}
