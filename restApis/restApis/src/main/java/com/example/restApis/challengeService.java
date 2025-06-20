package com.example.restApis;
//service -> business logic 

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class challengeService {
    
    private  List<challenge> challenges= new ArrayList<>();
    private  Long nextId = 1L; 
    // auto assign , no null ,  
         public String postchallenge(@RequestBody challenge challengeToadd){
   // request body is used to get the data from the client
   if(challengeToadd.getId()==null){
     challengeToadd.setId(nextId++);
   }
    // if(challengeToadd.getId() == null || challengeToadd.getDescription() == null){
    //   return "Please provide a valid challenge with an ID and description.";
    // }

    
    getChallenges().add(challengeToadd);
    return challengeToadd.getDescription() + " challenge added ";
  } 
     
  public void setChallenges(List<challenge> challenges) {
        this.challenges = challenges;
    }
  public challengeService(List<challenge> challenges) {
        this.challenges = challenges;
    }

  public challengeService() {
    
}
  public List<challenge> getChallenges()
{ 
     return challenges;
    
}
    public  void addChallenges(){
        challenges.add(new challenge(1L, false, "Eat lunch"));
         challenges.add(new challenge(2L, true, "gym"));
          return;
        }
        public challenge getchallengeById( Long id){

            for( challenge c : getChallenges()){
           if(c.getId().equals(id)){
            return c;
        }
    }
    return null;
}
 // id and what he wants to change
        public boolean updated( Long id,challenge updatedChallenge) {
            
            for(challenge i : getChallenges()){
                if(i.getId().equals(id)){
                    i.setDescription(updatedChallenge.getDescription());
                    return true;
                }
            }
  return false;
            }
}
