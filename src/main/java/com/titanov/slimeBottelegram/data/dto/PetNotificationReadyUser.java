package com.titanov.slimeBottelegram.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetNotificationReadyUser {

    private String telegramId;
    private String firstName;
}
