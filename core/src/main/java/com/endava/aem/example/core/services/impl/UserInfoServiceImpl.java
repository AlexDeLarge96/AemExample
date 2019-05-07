package com.endava.aem.example.core.services.impl;

import com.endava.aem.example.core.models.UserInfo;
import com.endava.aem.example.core.services.RequestService;
import com.endava.aem.example.core.services.UserInfoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Optional;

@Component(immediate = true, service = UserInfoService.class)
public class UserInfoServiceImpl implements UserInfoService {

    private static final String BASE_URL = "api/users/";
    private static final Gson GSON = new GsonBuilder().create();

    @Reference
    private RequestService requestService;

    @Override
    public Optional<UserInfo> getUserInfoById(String userId) {
        String response = requestService.performRequest(BASE_URL + userId);
        response = response.replace("[", "").replace("]", "");
        return response.length() == 0 ? Optional.empty() : Optional.of(GSON.fromJson(response, UserInfo.class));
    }
}
