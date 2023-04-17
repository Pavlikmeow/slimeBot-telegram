package com.titanov.slimeBottelegram.service;

import com.titanov.slimeBottelegram.client.SlimeClient;
import com.titanov.slimeBottelegram.data.dto.SlimePresentable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlimeService {

    private final SlimeClient slimeClient;
    private final MessageService messageService;

    public void getSlimeByTelegramId(String id) {
        SlimePresentable slime = slimeClient.getSlimeByTelegramId(id);
        String text = String.format("Ваш слаймик :)\nУровень: %d\nОпыт: %d/10\nЗдоровье: %d/%d\nАптечки: %d\nНастроение: %d/100",
                slime.getLevel(), slime.getExp(), slime.getHealth(), slime.getLevel()*20, slime.getHealKit(), slime.getMood());
        messageService.sendMessage(id, text, false);
    }

    public void healSlimeByTelegramId(String id) {
        int hp = slimeClient.healSlimeByTelegramId(id);
        String text = String.format("Вы вылечили своего слайма на 5 hp , теперь у него %d hp", hp);
        messageService.sendMessage(id, text, false);
    }

    public void petSlimeByTelegramId(String id) {
        String text = slimeClient.petSlimeByTelegramId(id);
        messageService.sendMessage(id, text, false);
    }
}
