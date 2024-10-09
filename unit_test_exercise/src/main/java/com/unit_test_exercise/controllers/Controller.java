package com.unit_test_exercise.controllers;

import com.unit_test_exercise.entities.User;
import com.unit_test_exercise.repositories.RepositoryUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class Controller {
    // Problemi io ho
    // non connette alla database e' non so la problema

    // parametri
    private User user;
    private RepositoryUser repositoryUser;

    // metodo Create = PostMapping
    @PostMapping("/newuser")
    public ResponseEntity<User> createNewUser(@RequestBody User newUser){
       return ResponseEntity.ok(newUser);
    }
    // metodo Read = GetMapping
    @GetMapping("/elencouser")
    public ResponseEntity<List<User>>  elencoUser(){
        List<User> userList = new ArrayList<>();
        userList.add(user);
        return ResponseEntity.ok(userList);
    }

    // metodo get per prendere solo un user
    @GetMapping("/userinfo/{id}")
    public ResponseEntity<String> prenderUnUser(@PathVariable Long id){
        return ResponseEntity.ok(user.toString());
    }
    // metodo Update = Putmapping
    @PutMapping("/datiuser/{id}")
    public ResponseEntity<User>  aggiornaDatiUser(@PathVariable Long id,@RequestBody User user){
        Optional<User> userOptional = repositoryUser.findById(id);
        if(userOptional.isPresent()){
            User updatedUser = userOptional.get();
            updatedUser.setFullName(user.getFullName());
            updatedUser.setAge(user.getAge());
            updatedUser.setAddress(user.getAddress());
            return ResponseEntity.ok(repositoryUser.save(updatedUser));
        }
        return ResponseEntity.notFound().build();
    }
    // metodo Delete = DeleteMapping
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        repositoryUser.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
