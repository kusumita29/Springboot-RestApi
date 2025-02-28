package com.kusumita.rest_api.microservice.social.media;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserDaoService {
	private static int userCount = 0;
    private static List<User> users = new ArrayList<>();

    // Static block to initialize some sample users
    static {
        users.add(new User(++userCount, "Alice", LocalDate.of(1995, 5, 10)));
        users.add(new User(++userCount, "Bob", LocalDate.of(1998, 8, 15)));
        users.add(new User(++userCount, "Charlie", LocalDate.of(2000, 12, 20)));
    }

    // Method to return all users
    public List<User> findAll() {
        return users;
    }
    
    // method to return user by id
    public User findOne(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public User addUser(User user) {
    	user.setId(++userCount);
    	users.add(user);
    	return user;
    }
    
    public void deleteUserById(int id) {
        User user = findOne(id); // Reusing findOne method
        if (user != null) {
            users.remove(user); // Remove user from list
        }
    }
}
