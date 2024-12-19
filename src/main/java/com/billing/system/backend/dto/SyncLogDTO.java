package com.billing.system.backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SyncLogDTO {

  private String lgaName;
  private int gGis;
  private int kLabs;
  private String lastSync;

  public SyncLogDTO(String lgaName, int gGis, int kLabs, String lastSync) {
    this.lgaName = lgaName;
    this.gGis = gGis;
    this.kLabs = kLabs;
    this.lastSync = lastSync;
  }


  // Getters and Setters
  public String getLgaName() {
    return lgaName;
  }

  public void setLgaName(String lgaName) {
    this.lgaName = lgaName;
  }

  public int getGGis() {
    return gGis;
  }

  public void setGGis(int gGis) {
    this.gGis = gGis;
  }

  public int getKLabs() {
    return kLabs;
  }

  public void setKLabs(int kLabs) {
    this.kLabs = kLabs;
  }

  public String getLastSync() {
    return lastSync;
  }

  public void setLastSync(String lastSync) {
    this.lastSync = lastSync;
  }

}
