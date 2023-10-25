package com.nelis.cnsd;

import com.amazon.sqs.javamessaging.AmazonSQSExtendedClient;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.util.Base64;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.http.HttpStatus;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/sqs")
@Slf4j
@AllArgsConstructor
public class SqsController {
    private final String queue = "first_queue";
    private final String photoQueue ="photo_queue";
    private final String photoBucket = "sqs-foto-bucket";

    private QueueMessagingTemplate queueMessagingTemplate;
    public AmazonS3 s3Client;
    private AmazonSQSExtendedClient sqsExtended;


    //    stuurt een bericht naar de SQS queue
    @PostMapping(value = "/send")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void sendMessageToSqs(@RequestBody String message) {
        log.info("Sending the message [{}] to the Amazon SQS [{}]", message, queue);
        queueMessagingTemplate.convertAndSend(queue, message);
    }

//    SQS listener die luisterd naar de SQS queue
    @SqsListener(value = queue, deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void getMessageFromSqs(String message) {
        log.info("Received message= {}", message);
    }

//    Stuurt een foto naar de SQS queue
    @PostMapping(value = "/foto")
    public void test(@RequestParam("file") MultipartFile file) throws IOException {
        final SendMessageRequest myMessageRequest =
                new SendMessageRequest(photoQueue, Base64.encodeAsString(file.getBytes()));
        log.info("Sending the photo [{}] to the Amazon SQS [{}]", file.getOriginalFilename(), photoQueue);
        sqsExtended.sendMessage(myMessageRequest);
    }

    @JmsListener(destination = photoQueue, containerFactory = "jmsListenerContainerFactory")
    public void consumeMessage(String message) throws InterruptedException {
        log.info("Received message with size [{}]", message.length());
        Thread.sleep(1000);
        String objectKey = UUID.randomUUID().toString();
        String objectUrl = "https://"+photoBucket+".s3.amazonaws.com/"+objectKey;
        final PutObjectRequest req = new PutObjectRequest(photoBucket, objectKey, new ByteArrayInputStream(Base64.decode(message)), new ObjectMetadata())
                .withCannedAcl(CannedAccessControlList.PublicRead);
        s3Client.putObject(req);
        log.info("Photo saved to [{}] [{}] [{}]", photoBucket, objectKey, objectUrl);
    }

//    Haalt bucket informatie op
    @GetMapping(value = "/s3")
    public String getS3Info() {
        final List<Bucket> buckets = s3Client.listBuckets();
        log.info("Got S3 buckets [{}]", buckets);
        return buckets.toString();
    }
}
