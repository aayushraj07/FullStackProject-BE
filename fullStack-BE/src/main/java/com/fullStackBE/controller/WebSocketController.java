package com.fullStackBE.controller;

import com.fullStackBE.dto.TextMessageDTO;
import com.fullStackBE.entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebSocketController {

    //    @Autowired
//    SimpMessagingTemplate template;
//
//    @PostMapping("/send")
//    public ResponseEntity<Void> sendMessage (@RequestBody TextMessageDTO textMessageDTO){
//        template.convertAndSend("/topic/message",textMessageDTO);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @MessageMapping("/sendMessage")
//    public void receiveMessage(@Payload TextMessageDTO textMessageDTO) {
//        // receive message from client
//    }
//
//
//    @SendTo("/topic/message")
//    public TextMessageDTO broadcastMessage(@Payload TextMessageDTO textMessageDTO) {
//        return textMessageDTO;
//    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }


}
