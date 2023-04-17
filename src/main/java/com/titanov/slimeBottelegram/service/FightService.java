package com.titanov.slimeBottelegram.service;

import com.titanov.slimeBottelegram.client.FightClient;
import com.titanov.slimeBottelegram.client.SlimeClient;
import com.titanov.slimeBottelegram.data.dto.FightPresentable;
import com.titanov.slimeBottelegram.data.dto.SlimePresentable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FightService {
    private final MessageService messageService;
    private final FightClient fightClient;

    public SendMessage formFightMessage(FightPresentable fight, String username, String chatId, boolean disableNotification) {
        SlimePresentable slime = fight.getSlime();
        int slimeHealth = slime.getHealth();
        if (slimeHealth == 0) {
            SendMessage msg = SendMessage.builder()
                            .text("Ваш слайм умер x_x")
                            .chatId(chatId)
                            .disableNotification(disableNotification)
                            .build();
            return msg;
        } else {
            int healthKit = slime.getHealKit();
            int fightLvl = fight.getFightLvl();
            int bossHealth = fight.getBossHealth();
            String startMsg = String.format("Битва идет!\n" +
                    "Уровень: %d\n" +
                    "Здоровье босса: %d\n" +
                    "---------------\n" +
                    "Слайм: %s\n" +
                    "Здоровье: %d\n" +
                    "Аптечки: %d", fightLvl, bossHealth, username, slimeHealth, healthKit);

            InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            List<InlineKeyboardButton> buttons = new ArrayList<>();

            var button1 = new InlineKeyboardButton();
            button1.setText("Атаковать");
            button1.setCallbackData("attack");

            var button2 = new InlineKeyboardButton();
            button2.setText("Лечиться");
            button2.setCallbackData("heal");

            var button3 = new InlineKeyboardButton();
            button3.setText("Сбежать/попуск");
            button3.setCallbackData("leave");

            buttons.add(button1);
            buttons.add(button2);
            buttons.add(button3);

            keyboard.add(buttons);
            inlineKeyboardMarkup.setKeyboard(keyboard);

            SendMessage msg = SendMessage.builder()
                    .text(startMsg)
                    .chatId(chatId)
                    .disableNotification(disableNotification)
                    .build();
            msg.setReplyMarkup(inlineKeyboardMarkup);

            return msg;
        }
    }

    public void attackCommand(String telegramId, String username) {
        FightPresentable fight = fightClient.attackInFight(telegramId);
        SendMessage msg = formFightMessage(fight, username, telegramId, false);
        messageService.sendMessage(msg);
    }

    public void healMessage(String id, boolean disableNotification) {
        messageService.sendMessage(id, "heal (в процессе)", disableNotification);
    }

    public void leaveMessage(String id, boolean disableNotification) {
        messageService.sendMessage(id, "leave (в процессе)", disableNotification);
    }
}
