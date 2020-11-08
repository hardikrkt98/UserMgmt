package com.usermgmt.dem.NewsFeed;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.usermgmt.dem.domain.Feed;
import com.usermgmt.dem.domain.TechFeedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static  com.usermgmt.dem.constants.CommonUrlConstants.*;


@RestController
@RequestMapping("/getNewsFeed")
public class TechController {




     @GetMapping("/Tech")
      private ResponseEntity<?> getTechNews() throws IOException {
          String url = TechFeedUrl;
          RestTemplate  template = new RestTemplate();
          HttpHeaders headers = new HttpHeaders();
         HttpEntity<JsonNode> requestEntity =
                 new HttpEntity<>( headers);
         ResponseEntity<JsonNode> response =
                 template.exchange(url, HttpMethod.GET,requestEntity,JsonNode.class);

         JsonNode parse = response.getBody().get("articles");
         List<Feed> techfeeds = new ArrayList<>();


         for(Integer i=0;i<parse.size();i++ ){

             JsonNode art = parse.get(i);
             Feed feed = new Feed(art.get("content").asText(),art.get("url").asText());
             techfeeds.add(feed);

         }

         TechFeedResponse techFeedResponse = new TechFeedResponse(techfeeds);
         return new ResponseEntity<TechFeedResponse>(techFeedResponse,HttpStatus.OK);
     }





}
