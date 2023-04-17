package com.titanov.slimeBottelegram.client;

import com.titanov.slimeBottelegram.data.dto.SlimePresentable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "slimeBot-slime", url = "localhost:8082/slime")
public interface SlimeClient {

    @PostMapping("/{telegramId}")
    void createNewSlime(@PathVariable String telegramId);

    @GetMapping("/{telegramId}")
    SlimePresentable getSlimeByTelegramId(@PathVariable String telegramId);

    @PostMapping("/heal/{telegramId}")
    int healSlimeByTelegramId(@PathVariable String telegramId);

    @PostMapping("/pet/{telegramId}")
    String petSlimeByTelegramId(@PathVariable String telegramId);
}
