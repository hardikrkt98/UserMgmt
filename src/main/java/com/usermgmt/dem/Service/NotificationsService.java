package com.usermgmt.dem.Service;


import com.fasterxml.jackson.databind.JsonNode;
import com.usermgmt.dem.domain.HttpResponse;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.mail.*;
import org.springframework.core.env.Environment;

@Service
public class NotificationsService {

    @Autowired
    private Environment env;

 public void sendEmail(String email,String passWord) throws MessagingException {

     String url  = "https://api.mailgun.net/v3/codeitnow.me/messages";
     String api_key  = env.getProperty("apikey");
     HttpHeaders httpHeaders = new HttpHeaders();
     httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
     MultiValueMap<String, String> multiValueMap= new LinkedMultiValueMap<String, String>();
     multiValueMap.add("to",email);
     multiValueMap.add("from","support@codeitnow.me");
     multiValueMap.add("subject","Password to login");
     multiValueMap.add("text","You can login to codeitnow.me through password "+passWord);
     httpHeaders.setBasicAuth("api",api_key);
     RestTemplate template = new RestTemplate();
     HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(multiValueMap,httpHeaders);
     ResponseEntity<JsonNode> responseEntity = template.exchange(url, HttpMethod.POST,requestEntity,JsonNode.class);




 }

}


