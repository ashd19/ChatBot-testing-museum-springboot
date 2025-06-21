package com.example.restApis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class challenge {
   @Id
   // auto increment in db 
   // ALTER TABLE challenge MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;
   // always empty when auto increment , truncate table table_name;
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(name ="Status")
   private boolean status;
   
   private String  description;
   public challenge(Long id, boolean status, String description) {
    this.id = id;
    this.status = status;
    this.description = description;
}

// default contructor for jpa 
public challenge(){
   
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
