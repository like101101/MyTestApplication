package com.example.mytestapp.listener;

import com.example.mytestapp.entity.User;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserListener {

    @KafkaListener(topics = "Users", groupId = "group_id")
    public void sendMessage(String message){
        System.out.println("This message is consumed: " + message);
    }

    @KafkaListener(topics = "Users", groupId = "group_json",
    containerFactory = "userKafkaListenerFactory")
    public void sendUser(User user){
        System.out.println("This Json message is consumed: " + user);
    }
}
