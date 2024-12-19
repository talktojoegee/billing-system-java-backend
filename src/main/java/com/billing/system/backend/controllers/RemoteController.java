package com.billing.system.backend.controllers;

import com.billing.system.backend.dto.APIResponse;
import com.billing.system.backend.entity.LGA;
import com.billing.system.backend.repository.LGARepository;
import com.billing.system.backend.service.JsonParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RemoteController {


  @Autowired
  private JsonParserService jsonParserService;
  @Autowired
  private final LGARepository lgaRepository;

  public RemoteController(LGARepository lgaRepository) {
    this.lgaRepository = lgaRepository;
  }

  @GetMapping("/call")
  public Mono<String> callExternalGis() {
    return jsonParserService.parseGisResponse();
  }


  @GetMapping("/lga/{name}")
  public Mono<APIResponse<Map<String, Integer>>> showBuildingsByLGA(@PathVariable String name) {
    return jsonParserService.parseBuildingResponse(name);
  }


  @GetMapping("/lga-id/{lgaId}")
  public Mono<APIResponse<Map<String, Integer>>> showBuildingsByLGAId(@PathVariable int lgaId) {
    Optional<LGA> lga = lgaRepository.findById(lgaId);
    if(lga.isPresent()){
      return jsonParserService.parseBuildingResponse(lga.get().getLga_name());
    }
    throw new IllegalArgumentException("No record found");
  }


}
