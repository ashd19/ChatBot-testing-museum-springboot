package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
// import com.example.demo.jpaRepo;
@Service
public class GeminiService {
    // Access to APIKey and URL [Gemini]

@Autowired
 jpaRepo repo;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public GeminiService(WebClient.Builder webClient) {
        this.webClient = webClient.build();
        
    }

    public String getAnswer(String question) {
        Map<String, Object> requestBody = Map.of(
            "contents", List.of(
                Map.of("parts", List.of(
                    Map.of("text", question)
                ))
            )
        );

        String response = webClient.post()
                .uri(geminiApiUrl)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        // Extract the answer text from the Gemini response
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            // Gemini's answer is usually in: candidates[0].content.parts[0].text
            String answer = root.path("candidates")
                                .path(0)
                                .path("content")
                                .path("parts")
                                .path(0)
                                .path("text")
                                .asText();
            return answer.isEmpty() ? "No answer found." : answer;
        } catch (Exception e) {
            return "Error parsing AI response.";
        }
    }


  // post the email
   public  String postDetails(details infodDetails){
      
   repo.save(infodDetails);
    return  "added";
   }
   
   public List<details> getDetails(){
    return repo.findAll();
   }



}