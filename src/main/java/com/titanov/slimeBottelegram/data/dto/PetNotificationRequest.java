package com.titanov.slimeBottelegram.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetNotificationRequest {
    private List<PetNotificationReadyUser> notificationReadyUserList;
}
