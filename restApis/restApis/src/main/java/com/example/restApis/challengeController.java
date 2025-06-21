package com.example.restApis;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//  Root url , RequestMapping("/challenges")
public class challengeController {
  // @Autowired
// Spring creates and manages the challengeService object.
// Spring injects the service wherever it is needed (Inversion of Control).
// You can use features like singleton, configuration, and easier testing.
// You must annotate your service class with @Service:

  challengeService service = new challengeService();
  
    



    public  challengeController(){
        service.addChallenges(); 
        
    }
    // The addChallenges() method is just adding initial values to the service.
    // If you want your controller's 'challenges' list to have those values, you should retrieve them from the service.
    // For example:
    // this.challenges = service.getChallenges();
    


 @GetMapping("/getchallenges")
  public List<challenge> getAllChallenges(){
    return service.getChallenges();
  }

 @PostMapping("/postchallenge")
  public String postchallenge(@RequestBody challenge challengeToadd){
   // request body is used to get the data from the client
   
   return service.postchallenge(challengeToadd);
  } 

  


  
@GetMapping("/getchallenge/{id}")
//im not seeing the values after id 2 , maybe because i have not saved it to db ?
// after weere added by postman and not hardcoded 
public ResponseEntity<challenge> getchallengeById(@PathVariable Long id){
//  @PathVariable is an annotation used to extract values from the URI path and bind them to method parameters in your controller
// IOC >>?????  
 if(service.getchallengeById(id) != null){
     return new ResponseEntity<>(service.getchallengeById(id),HttpStatus.OK);
 }
 else{
  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
 }
// ** service 
}
 
@PutMapping("/putchallengedescrip/{id}")
public ResponseEntity<String> isUpdated(@PathVariable Long id,@RequestBody challenge updatedChallenge)
{ 
  boolean ans = service.updated(id,updatedChallenge);
    if(ans){
      return new ResponseEntity<>(updatedChallenge.getDescription()+"DONE successfully",HttpStatus.OK);
    }
    return new ResponseEntity<>("Unsuccessful",HttpStatus.NOT_FOUND);

}

@DeleteMapping("/deletechallenge/{id}")
public ResponseEntity<String> deleted(@PathVariable Long id){
   if(service.todelete(id)){
    return new ResponseEntity<>("The Challlenge with id "+id+" is deleted successfully",HttpStatus.OK);   }
  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}



  @GetMapping("/")
  public String greeting(){
    return """
            
        
         Welcome <br>

         to get all challenges /getchallenges<br>
         to post challenge  /postchallenge<br>
         to get by id  /getchallenge/{id}<br>
         to put/change decrip  /putchallengedescrip/{id} <br>
         to delete /deletechallenge/{id}
         """;
  }
   

}


// browser ->  controller -> service ->  repo -> db 
