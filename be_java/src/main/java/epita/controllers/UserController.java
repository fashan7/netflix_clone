package epita.controllers;

import epita.datamodel.User;
import epita.exceptions.BadRequestAlertException;
import epita.repository.UserRepository;
import epita.service.CloakConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/netflix")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = new ArrayList<>();
            userRepository.getUsers().forEach(users::add);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {

        if (user.getId() != null) {
            throw new BadRequestAlertException("A new user cannot already have an ID");
            // Lowercase the user login before comparing with database
        } else {
            User newUser = userRepository.createUser(user);

            CloakConfiguration cloakConfiguration = new CloakConfiguration(user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail());
            cloakConfiguration.configuration();
            return ResponseEntity.created(new URI("/netflix/users/" + newUser.getId()))
                    .body(newUser);
        }
    }


    @DeleteMapping("/users/{name}")
    public ResponseEntity<HttpStatus> deleteUsers(@PathVariable("name") String userName) {
        userRepository.deleteByName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
