package com.titanov.slimeBottelegram.controller;

import com.titanov.slimeBottelegram.data.dto.PetNotificationRequest;
import com.titanov.slimeBottelegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final MessageService messageService;

    @PostMapping("/petNotification")
    public void sendPetNotificationMessage(@RequestBody PetNotificationRequest petNotificationRequest) {
        messageService.sendPetNotification(petNotificationRequest);
    }
}
