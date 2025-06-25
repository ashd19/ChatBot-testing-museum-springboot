package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins="http://localhost:5173/")
public class chatbotController {
 

@RestController

@RequestMapping("/api/qna")
public class AIController {

    private final GeminiService qnAService;

    public AIController(GeminiService qnAService) {
        this.qnAService = qnAService;
    }

    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody Map<String, String> payload){
        String question = payload.get("question");
        String answer = qnAService.getAnswer(question);
        return ResponseEntity.ok(answer);


    }

    @PostMapping("/post")
    public String postEmail(@RequestBody details infoDetails){
       
        return qnAService.postDetails(infoDetails);

    }

    @GetMapping("/getdetails")
    public List<details> getallDetails(){
        return qnAService.getDetails();
    }



}}


