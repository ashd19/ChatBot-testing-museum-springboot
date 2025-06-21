package com.example.restApis;
//service -> business logic 

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
@Service
public class challengeService {
    
    // private  List<challenge> challenges= new ArrayList<>();
   
    @Autowired
    // no in memory list use db for all ops 
    challengeRepository challengerepository;

    // auto assign , no null ,  
         public String postchallenge(@RequestBody challenge challengeToadd){
   // request body is used to get the data from the client
  
   // Always create a new challenge; let DB assign the ID
    challengeToadd.setId(null);
    challengerepository.save(challengeToadd);
     return challengeToadd.getDescription() + " challenge added ";
    // if(challengeToadd.getId() == null || challengeToadd.getDescription() == null){
    //   return "Please provide a valid challenge with an ID and description.";
    // }

    
  } 
     
 

  public challengeService() {
    
}
  public List<challenge> getChallenges()
{ 
     return challengerepository.findAll();
    
}
    public void addChallenge(challenge challengeToAdd) {
      if(challengeToAdd != null){  
      // challenges.add(challengeToAdd); 
      //  using repository db 
      challengerepository.save(challengeToAdd);
    }
    
    }
        public challenge getchallengeById( Long id){

    //         for( challenge c : getChallenges()){
    //        if(c.getId().equals(id)){
    //         return c;
    //     }
    // }
    // return null;
   return challengerepository.findById(id).orElse(null);

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



  // delete 
  // will pass the id ? sufficient ? or only description ?? hmm 
  // delete whole challenge with a specific id
  
  public boolean todelete(Long id){
  //    for(challenge i : getChallenges()){
  //     if(i.getId().equals(id)){
  //       getChallenges().remove(i);
  //       return true;
  //     }
  //    }
  //     return false;
   
  // }
  if(challengerepository.existsById(id)){
    challengerepository.deleteById(id);
    return true;
  // lambda function 
   // In the lambda, challenge is just the variable name for each element in the list as it is being checked.
  } 
  return false;

  }
}



