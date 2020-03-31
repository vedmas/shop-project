package ru.tokarev.shop.flow.register;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterModel implements Serializable {

    private BasicUserInfo basicUserInfo;

    private PersonalUserInfo personalUserInfo;
}
