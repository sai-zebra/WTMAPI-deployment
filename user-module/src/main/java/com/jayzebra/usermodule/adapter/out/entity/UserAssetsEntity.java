package com.jayzebra.usermodule.adapter.out.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserAssetsEntity {
    private String userId;
    private byte[] image;
}
