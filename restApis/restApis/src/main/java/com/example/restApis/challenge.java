package com.example.restApis;


public class challenge {
   private Long id;
   private boolean status;
   private String  description;
   public challenge(Long id, boolean status, String description) {
    this.id = id;
    this.status = status;
    this.description = description;
}
   public Long getId() {
    return id;
   }
   public void setId(Long id) {
    this.id = id;
   }
   public boolean isStatus() {
    return status;
   }
   public void setStatus(boolean status) {
    this.status = status;
   }
   public String getDescription() {
    return description;
   }
   public void setDescription(String description) {
    this.description = description;
   }
   
    

}
