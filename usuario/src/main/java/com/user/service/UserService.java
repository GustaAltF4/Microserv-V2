package com.user.service;

import com.user.clients.ProductoFeignClient;
import com.user.dto.ProductDto;
import com.user.entity.User;
import com.user.entity.UserDto;
import com.user.repository.UserRepo;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

@Service
public class UserService {

    @Autowired
    public UserRepo repoUser;

    @Autowired
    private ProductoFeignClient feignClient;

    public List<UserDto> listAllUsersWithoutProducts() {
        List<User> usuarios = repoUser.findAll();
        return usuarios.stream()
                .map(u -> new UserDto(u.getId(), u.getName(), u.getEmail()))
                .toList();
    }
    public List<User> ListAllUsers(){
        List<User> usuarios = repoUser.findAll();




            for (User usuario : usuarios) {
                List<ProductDto> products;
                try {
                    products= feignClient.obtenerProductos(usuario.getId());
                }catch (FeignException.NotFound e){
                    products = Collections.emptyList();
                }
                usuario.setProducts(products);

            }

        return  usuarios;
    }

    public User getUserById(Integer id){
        User user = repoUser.findById(id).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        List<ProductDto> products;
        try{
            products= feignClient.obtenerProductos(id);
        }catch (FeignException.NotFound e){
            products = Collections.emptyList();
        }

        user.setProducts(products);
        return user;
    }

    public User getUserByName(String name){
        User user = repoUser.findByName(name);
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        List<ProductDto> products ;
        try {
            products= feignClient.obtenerProductos(user.getId());
        }catch (FeignException.NotFound e){
            products = Collections.emptyList();
        }

        user.setProducts(products);
        return  user;
    }

    //POST_________________________________

    public User addUser(User user){
        return repoUser.save(user);
    }

    //DELETE_______________________________

    public void deleteUserById(Integer id){
        if (repoUser.existsById(id)){
            repoUser.deleteById(id);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
    }

    public void deleteUserByName(String name){
        User user = repoUser.findByName(name);

        if (user != null){
            repoUser.delete(user);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
    }


    //PUT__________________________________

    public User updateUser(Integer id , User newUser){

        User existingUser = repoUser.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        existingUser.setName(newUser.getName());
        existingUser.setEmail(newUser.getEmail());

        return repoUser.save(existingUser);

    }


    //--------------MÉTODOS DE PRODUCTOS--------------
    public List<ProductDto> getProducts (Integer id){

        return feignClient.obtenerProductos(id);
    }

}
