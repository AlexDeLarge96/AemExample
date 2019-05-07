package com.endava.aem.example.core.services.impl;

import com.endava.aem.example.core.services.RequestService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.metatype.annotations.Designate;

@Component(immediate = true, service = RequestService.class, configurationPolicy = ConfigurationPolicy.OPTIONAL)
@Designate(ocd = RequestServiceImplConfiguration.class)
public class RequestServiceImpl implements RequestService {

    private String hostname;
    private String port;

    @Activate
    public void activate(RequestServiceImplConfiguration config) {
        this.hostname = config.hostname();
        this.port = config.port();
    }

    @Override
    public String performRequest(String path) {
        String url = String.format("http://%s:%s/%s", this.hostname, this.port, path);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);
        try {
            return EntityUtils.toString(httpClient.execute(getRequest).getEntity());
        } catch (Exception e) {
            return "";
        }
    }
}

