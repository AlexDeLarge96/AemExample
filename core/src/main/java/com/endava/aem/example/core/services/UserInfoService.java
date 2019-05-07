package com.endava.aem.example.core.services;

import com.endava.aem.example.core.models.UserInfo;

import java.util.Optional;

public interface UserInfoService {

    Optional<UserInfo> getUserInfoById(String userId);
}
