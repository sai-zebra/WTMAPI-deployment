package com.zebra.usermodule.adapter.out.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {

    @Id
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String profileImageUrl;

}
