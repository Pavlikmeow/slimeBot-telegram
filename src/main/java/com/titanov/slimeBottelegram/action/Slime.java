package com.titanov.slimeBottelegram.action;

import com.titanov.slimeBottelegram.service.SlimeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component("slime")
@RequiredArgsConstructor
public class Slime implements Action {

    private final SlimeService slimeService;

    @Override
    public void handle(Update update) {
        String chatId = update.getMessage().getChatId().toString();

        slimeService.getSlimeByTelegramId(chatId);
    }
}
