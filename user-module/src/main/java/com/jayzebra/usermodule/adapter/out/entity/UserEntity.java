package com.jayzebra.usermodule.adapter.out.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class UserEntity {
    @Id
    private String userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
