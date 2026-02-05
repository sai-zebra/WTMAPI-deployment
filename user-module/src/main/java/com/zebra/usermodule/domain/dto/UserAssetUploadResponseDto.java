package com.zebra.usermodule.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class UserAssetUploadResponseDto {
    private String userId;
    private String newProfileImageUrl;
}

