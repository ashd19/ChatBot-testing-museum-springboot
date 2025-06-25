package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Chatbot_app")
public class details {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private String email;
private String query;
private String response;
public details(Long id, String email, String query, String response) {
    this.id = id;
    this.email = email;
    this.query = query;
    this.response = response;
}
public Long getId() {
    return id;
}
public void setId(Long id) {
    this.id = id;
}
public String getEmail() {
    return email;
}
public void setEmail(String email) {
    this.email = email;
}
public String getQuery() {
    return query;
}
public void setQuery(String query) {
    this.query = query;
}
public String getResponse() {
    return response;
}
public void setResponse(String response) {
    this.response = response;
}

public details(){
    
}

}
