package com.endava.aem.example.core.models.components;

import com.endava.aem.example.core.models.UserInfo;
import com.endava.aem.example.core.services.UserInfoService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.Optional;

@Model(adaptables = SlingHttpServletRequest.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        resourceType = "ExampleAEM/components/content/user")
@Exporter(name = "jackson", extensions = "json")
public class User {

    @Inject
    @SlingObject
    @Required
    private SlingHttpServletRequest request;

    @Inject
    @OSGiService
    private UserInfoService userInfoService;

    private static final String PARAM_USER_ID = "userId";

    private int id;
    private String name;
    private String photoUrl;

    @PostConstruct
    protected void init() {
        String userId = Optional.ofNullable(request.getRequestParameterMap().getValue(PARAM_USER_ID))
                .map(RequestParameter::getString).orElse("");
        UserInfo result = userInfoService.getUserInfoById(userId).orElseGet(UserInfo::new);
        this.id = result.getId();
        this.name = result.getName();
        this.photoUrl = result.getPhotoUrl();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}

