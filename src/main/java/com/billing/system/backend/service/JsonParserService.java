package com.billing.system.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.billing.system.backend.dto.APIResponse;
import com.billing.system.backend.entity.LGA;
import com.billing.system.backend.entity.PropertyClassification;
import com.billing.system.backend.entity.PropertyList;
import com.billing.system.backend.entity.Zone;
import com.billing.system.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class JsonParserService {

  private final RemoteService remoteService;
  private final ObjectMapper objectMapper;
  private final LGARepository lgaRepository;
  private final PropertyClassificationRepository propertyClassificationRepository;
  private final PropertyAssessmentValueRepository propertyAssessmentValueRepository;
  private final PropertyListRepository propertyListRepository;
  private final ZoneRepository zoneRepository;

  @Autowired
  public JsonParserService(@Lazy RemoteService remoteService,
                           ObjectMapper objectMapper,
                           LGARepository lgaRepository,
                           PropertyClassificationRepository propertyClassificationRepository,
                           PropertyAssessmentValueRepository propertyAssessmentValueRepository,
                           PropertyListRepository propertyListRepository,
                           ZoneRepository zoneRepository) {
    this.remoteService = remoteService;
    this.objectMapper = objectMapper;
    this.lgaRepository = lgaRepository;
    this.propertyClassificationRepository = propertyClassificationRepository;
    this.propertyAssessmentValueRepository = propertyAssessmentValueRepository;
    this.propertyListRepository = propertyListRepository;
    this.zoneRepository = zoneRepository;
  }

  public Mono<String> parseGisResponse() {
    return remoteService.callGisEndpoint()
      .flatMap(response -> {
        try {
          // Parse JSON response
          JsonNode rootNode = objectMapper.readTree(response);
          JsonNode dataNode = rootNode.get("data");

          // Process the "data" array
          StringBuilder result = new StringBuilder();

          if (dataNode != null && dataNode.isArray()) {
            for (JsonNode node : dataNode) {
              // Process each element in the array
              String id = node.has("id") ? node.get("id").asText() : "N/A";
              String owner = node.has("Owner") ? node.get("Owner").asText() : "N/A";
              int fid = node.has("fid") ? Integer.parseInt(node.get("fid").asText()) : 0;
              String buildingId = node.has("Bld_ID") ? node.get("Bld_ID").asText() : "N/A";
              String lga = node.has("LGA") ? node.get("LGA").asText() : "N/A";
              String zone = node.has("Zone") ? node.get("Zone").asText() : "N/A";
              String buildingCat = node.has("Bld_Cat") ? node.get("Bld_Cat").asText() : "N/A";
              String kgtin = node.has("KGTIN") ? node.get("KGTIN").asText() : "N/A";
              String payStatus = node.has("Pay_Status") ? node.get("Pay_Status").asText() : "N/A";
              //System.out.println("Owner name is:: "+owner);

              // Append details to the result
              result
                .append("ID: ").append(id)
                .append(", Owner: ")
                .append(owner)
                .append(", fID: ")
                .append(fid)
                .append(", Building ID: ")
                .append(buildingId)
                .append(", LGA: ")
                .append(lga)
                .append(", Zone: ")
                .append(zone)
                .append(", Building Cat: ")
                .append(buildingCat)
                .append(", KGTIN: ")
                .append(kgtin)
                .append(", Pay Status: ")
                .append(payStatus)
                .append("\n");
            }
          }

          // Return the result as a Mono
          return Mono.just(result.toString());

        } catch (IOException e) {
          // Handle JSON parsing errors
          return Mono.error(new RuntimeException("Failed to parse JSON response", e));
        }
      });
  }


  public Mono<APIResponse<Map<String, Integer>>> parseBuildingResponse(String name) {
    return remoteService.loadBuildingsByLgaName(name)
      .publishOn(Schedulers.boundedElastic())
      .flatMap(response -> {
        try {
          // Parse JSON response
          JsonNode rootNode = objectMapper.readTree(response);
          JsonNode dataNode = rootNode.get("data");

          int addedCount = 0;
          int rejectedCount = 0;

          // Process the "data" array
          if (dataNode != null && dataNode.isArray()) {
            for (JsonNode node : dataNode) {
              // Get LGA, Classification, and Zone
              Optional<LGA> lgaOne = lgaRepository.findByLgaName(node.get("LGA").asText());
              Optional<PropertyList> propertyList = propertyListRepository.findByBuildingCode(node.get("Bld_ID").asText());
              Optional<PropertyClassification> propertyClassification = propertyClassificationRepository.findByName(node.get("Bld_Cat").asText());
              Optional<Zone> zoneOne = zoneRepository.findByZone(node.get("Zone").asText());

              if (lgaOne.isPresent() && propertyClassification.isPresent() && zoneOne.isPresent()) {
                if (propertyList.isEmpty()) {
                  // Create and save a new PropertyList
                  PropertyList pList = new PropertyList();
                  pList.setAddress(node.get("Street").asText());
                  pList.setArea(node.get("Bld_area").asText());
                  pList.setBuildingCode(node.get("Bld_ID").asText());
                  pList.setOwnerName(node.get("Owner").asText());
                  pList.setLga(lgaOne.get());
                  pList.setBorehole(node.get("Bld_Storey").asInt());
                  pList.setImage(node.get("Photo").asText());
                  pList.setPower(Objects.equals(node.get("Power").asText(), "Yes") ? 1 : 0);
                  pList.setWater(Objects.equals(node.get("Water").asText(), "Yes") ? 1 : 0);
                  pList.setZoneName(node.get("Zone").asText());

                  String pavCode = node.get("Zone").asText() +
                    propertyClassification.get().getId() +
                    node.get("Occupant").asText();

                  pList.setPavCode(pavCode);
                  pList.setOwnerKgtin(node.get("KGTIN").asText());
                  pList.setCreatedAt(LocalDateTime.now());
                  pList.setUpdatedAt(LocalDateTime.now());
                  pList.setPropertyClassification(propertyClassification.get());

                  propertyListRepository.save(pList);
                  addedCount++;
                } else {
                  rejectedCount++;
                }
              } else {
                rejectedCount++;
              }
            }
          }

          // Prepare response map
          Map<String, Integer> resultMap = new HashMap<>();
          resultMap.put("newRecords", addedCount);
          resultMap.put("existingRecords", rejectedCount);

          // Wrap in APIResponse
          APIResponse<Map<String, Integer>> apiResponse = new APIResponse<>(
            true, "Data synchronization done!", resultMap
          );
          return Mono.just(apiResponse);

        } catch (IOException e) {
          // Handle JSON parsing errors
          APIResponse<Map<String, Integer>> errorResponse = new APIResponse<>(
            false, "Failed to parse JSON response: ", null
          );
          return Mono.just(errorResponse);
        }
      })
      .onErrorResume(e -> {
        // Handle any other unexpected errors
        APIResponse<Map<String, Integer>> errorResponse = new APIResponse<>(
          false, "An error occurred: ", null
        );
        return Mono.just(errorResponse);
      });
  }





}

