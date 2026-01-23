package com.jayzebra.usermodule.domain.dto;

import lombok.Data;

@Data
public class UserAssets {
    private String userId;
    private byte[] image;
}
