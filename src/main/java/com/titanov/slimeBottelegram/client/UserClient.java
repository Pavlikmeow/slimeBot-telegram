package com.titanov.slimeBottelegram.client;

import com.titanov.slimeBottelegram.data.dto.SystemUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "slimeBot-user", url = "localhost:8081/user")
public interface UserClient {

    @PostMapping
    void registerUser(@RequestBody SystemUser systemUser);
}
