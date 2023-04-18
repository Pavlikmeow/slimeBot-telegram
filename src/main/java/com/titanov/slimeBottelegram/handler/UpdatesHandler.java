package com.titanov.slimeBottelegram.handler;

import com.titanov.slimeBottelegram.action.Action;
import com.titanov.slimeBottelegram.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatesHandler extends TelegramLongPollingBot {
    private final MessageService messageService;
    private final FightService fightService;
    private Map<String, Action> actions;

    @Value("${bot.name}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;

    @Autowired
    public void setBeans(Map<String, Action> actions) {
        this.actions = actions;
    }

    public void onUpdateReceived(Update update) {
        //Messages handler
        if (Objects.nonNull(update.getMessage()) && update.getMessage().hasText()) {

            String text = update.getMessage().getText();
            User user = update.getMessage().getFrom();

            log.info("{} says: {}", user.getUserName(), text);

            String stringAction = update.getMessage().getText().replace("/", "");
            Action action = actions.get(stringAction);
            action.handle(update);
        }

        //Buttons handler
        else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            String callBackData = update.getCallbackQuery().getData();
            String name = update.getCallbackQuery().getFrom().getUserName();

            log.info("{} pressed {}", name, callBackData);

            switch (callBackData) {
                case "attack" : {
                    messageService.dropButtonsFromMessage(chatId, messageId);
                    fightService.attackCommand(chatId, name);
                    break;
                }
                case "heal" : {
                    fightService.healMessage(chatId, false);
                    break;
                }
                case "leave" : {
                    fightService.leaveMessage(chatId, false);
                    break;
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        return botUsername;
    }
    @Override
    public String getBotToken() {
        return botToken;
    }
}
