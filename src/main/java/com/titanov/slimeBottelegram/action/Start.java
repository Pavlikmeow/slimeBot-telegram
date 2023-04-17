package com.titanov.slimeBottelegram.action;

import com.titanov.slimeBottelegram.service.MessageService;
import com.titanov.slimeBottelegram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

@Slf4j
@Component("start")
@RequiredArgsConstructor
public class Start implements Action {

    private final UserService userService;
    private final MessageService messageService;

    @Override
    public void handle(Update update) {
        User user = update.getMessage().getFrom();
        String chatId = update.getMessage().getChatId().toString();
        userService.registerUser(user);
        messageService.sendMessage(chatId, "Добро пожаловать!", false);
        log.info("Пользователеть {} с id: {} был зарегистрирован", user.getUserName(), user.getId());
    }
}
