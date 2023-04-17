package com.titanov.slimeBottelegram.service;

import com.titanov.slimeBottelegram.client.SlimeClient;
import com.titanov.slimeBottelegram.client.UserClient;
import com.titanov.slimeBottelegram.data.dto.SystemUser;
import com.titanov.slimeBottelegram.mapper.SystemUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

@Service
@RequiredArgsConstructor
public class UserService {

    private final SystemUserMapper systemUserMapper;
    private final UserClient userClient;
    private final SlimeClient slimeClient;

    public void registerUser(User user) {
        SystemUser systemUser = systemUserMapper.mapToSystemUser(user);
        userClient.registerUser(systemUser);
        slimeClient.createNewSlime(user.getId().toString());
    }
}
