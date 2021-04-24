//package com.starter.admin.service.mq;
//
//import com.starter.admin.entity.SysDeptEntity;
//import com.starter.admin.service.DeptService;
//import com.starter.admin.service.SysDeptService;
//import com.starter.admin.service.system.dto.DeptDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class Listener {
//    private final String topic = "byteArray_topic1";
//
//    private final DeptService deptService;
//
//    private final SysDeptService sysDeptService;
//
//
//    @KafkaListener(id="myListener",
//        topicPartitions ={@TopicPartition(topic = topic, partitions = { "0", "1" ,"2","3","4"})})
//    public void listen(List<ConsumerRecord<String, byte[]>> recordList) {
//        recordList.forEach((record)->{
//            log.info("kafka的key: " + record.key());
//            log.info("kafka的value: " + new String(record.value()));
//
//            DeptDto dept = deptService.findById(2L);
//            SysDeptEntity sysDeptEntity = sysDeptService.getById(2L);
//            log.info("dept="+dept);
//            log.info("sysDeptEntity="+sysDeptEntity);
//        });
//    }
//
//    @KafkaListener(id="test3",
//        topicPartitions ={@TopicPartition(topic = "test3", partitions = { "0", "1" ,"2"})})
//    public void listenTest3(List<ConsumerRecord<String, byte[]>> recordList) {
//        recordList.forEach((record)->{
//            log.info("kafka的key: " + record.key());
//            log.info("kafka的value: " + new String(record.value()));
//
////            DeptDto dept = deptService.findById(2L);
////            SysDeptEntity sysDeptEntity = sysDeptService.getById(2L);
////            log.info("dept="+dept);
////            log.info("sysDeptEntity="+sysDeptEntity);
//        });
//    }
//}