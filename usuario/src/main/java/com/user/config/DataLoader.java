package com.user.config;

import com.user.entity.User;
import com.user.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepo userRepo;


    public DataLoader(UserRepo prodRepo) {
        this.userRepo = prodRepo;
    }

    @Override
    public void run(String... args) {
        if (userRepo.count() == 0) {
            userRepo.saveAll(
                    List.of(
                            new User("Juan Topo","Juantopo123@example.com"),
                            new User("Alberto Ronaldo","Alberto@example.com"),
                            new User("Lio Messi","Messi10@example.com")


                    )
            );
        }
    }
}
