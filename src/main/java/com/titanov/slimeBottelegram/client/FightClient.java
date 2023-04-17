package com.titanov.slimeBottelegram.client;

import com.titanov.slimeBottelegram.data.dto.FightPresentable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "slimeBot-slime1", url = "localhost:8082/slime/fight")
public interface FightClient {

    @PostMapping("/{telegramId}")
    FightPresentable fightCommand(@PathVariable String telegramId);

    @GetMapping("/{telegramId}")
    FightPresentable getFightByTelegramId(@PathVariable String telegramId);

    @PostMapping("/attack/{telegramId}")
    FightPresentable attackInFight(@PathVariable String telegramId);
}
