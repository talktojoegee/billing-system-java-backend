package com.billing.system.backend.service;

import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Random;

@Service
public class UtilityService {


  public static String generateSlug(String input){
    String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
    String withoutDiacritics = normalized.replaceAll("\\p{M}", "");
    String slug = withoutDiacritics
      .toLowerCase()
      .replaceAll("[^a-z0-9\\s-]", "")
      .replaceAll("\\s+", "-")
      .replaceAll("-+", "-")
      .replaceAll("^-|-$", "");

   return slug + " "+ generateRandomString();
  }

  private static String generateRandomString() {
    String characters = "abcdefghijklmnopqrstuvwxyz0123456789";
    Random random = new Random();
    StringBuilder randomString = new StringBuilder(8);

    for (int i = 0; i < 8; i++) {
      randomString.append(characters.charAt(random.nextInt(characters.length())));
    }
    return randomString.toString();
  }




}
