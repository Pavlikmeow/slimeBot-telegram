package com.titanov.slimeBottelegram.action;

import com.titanov.slimeBottelegram.client.FightClient;
import com.titanov.slimeBottelegram.data.dto.FightPresentable;
import com.titanov.slimeBottelegram.service.FightService;
import com.titanov.slimeBottelegram.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

@Slf4j
@Component("fight")
@RequiredArgsConstructor
public class Fight implements Action {

    private final MessageService messageService;
    private final FightService fightService;
    private final FightClient fightClient;

    @Override
    public void handle(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String username = update.getMessage().getFrom().getUserName();

            FightPresentable fight = fightClient.fightCommand(chatId);
            if (Objects.nonNull(fight)) {
                SendMessage msg = fightService.formFightMessage(fight, username, chatId, false);
                messageService.sendMessage(msg);
            } else {
                messageService.sendMessage(chatId, "Ваш слайм умер x_x", false);
            }
        }
    }
