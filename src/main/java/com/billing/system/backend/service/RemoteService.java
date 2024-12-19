package com.billing.system.backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RemoteService {


  private final WebClient webClient;
  private final JsonParserService jsonParserService;


  public RemoteService(WebClient.Builder webClientBuilder, JsonParserService jsonParserService) {
    this.webClient = webClientBuilder.baseUrl("https://laravel.kofooni.ca/api").build();
    //this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:8000/api").build();
    this.jsonParserService = jsonParserService;
  }

  public Mono<String> callGisEndpoint(){

    return webClient.get()
      .uri("/gis")
      //.uri("/gis")
      .retrieve()
      .bodyToMono(String.class);
  }

  public Mono<String> loadBuildingsByLgaName(String name){

    return webClient.get()
      .uri("/lga/"+name)
      .retrieve()
      .bodyToMono(String.class);
  }

}
