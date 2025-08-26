package com.user.controller;

import com.user.dto.ProductDto;
import com.user.entity.User;
import com.user.entity.UserDto;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    public UserService service;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsersWithoutProducts() {
        List<UserDto> users = service.listAllUsersWithoutProducts();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/details")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = service.ListAllUsers();
        if (users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id){
        User user = service.getUserById(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<User> getUserByName(@PathVariable String name){
        User user = service.getUserByName(name);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("id/{id}/products")
    public ResponseEntity<?> findProdByUser(@PathVariable Integer id){

        try{
            if (id <= 0) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }

            List<ProductDto> products = service.getProducts(id);

            if (products != null){
                return  ResponseEntity.ok(products);
            }else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //POST________________________________________________________

    @PostMapping("/add")
    public ResponseEntity<User> addUser (@RequestBody User user){
        User created = service.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    //DELETE______________________________________________________

    @DeleteMapping("/del/id/{id}")
    public ResponseEntity<Void> deleteUserById (@PathVariable Integer id){
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/del/name/{name}")
    public ResponseEntity<Void> deleteUserByName(@PathVariable String name){
        service.deleteUserByName(name);
        return ResponseEntity.noContent().build();
    }

    //PUT__________________________________________________________

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
        User updated = service.updateUser(id, user);
        return ResponseEntity.ok(updated);
    }
}
