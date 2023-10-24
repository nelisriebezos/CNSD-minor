package com.nelis.cnsd;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sqs")
@Slf4j
@AllArgsConstructor
public class SqsController {
//    private final String queue = "https://sqs.us-east-1.amazonaws.com/743524578281/first_queue";
    private final String queue = "first_queue";

    private QueueMessagingTemplate queueMessagingTemplate;

    @PostMapping(value = "/send")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void sendMessageToSqs(@RequestBody String message) {
        log.info("Sending the message [{}] to the Amazon SQS [{}]", message, queue);
        queueMessagingTemplate.convertAndSend(queue, message);
    }

    @SqsListener(value = queue, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void getMessageFromSqs(String message) {
        log.info("Received message= {}", message);
    }
}
