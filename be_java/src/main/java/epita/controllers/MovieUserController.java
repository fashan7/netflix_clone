package epita.controllers;

import epita.datamodel.MovieUser;
import epita.exceptions.BadRequestAlertException;
import epita.repository.MovieUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/netflix")
public class MovieUserController {
    @Autowired
    private MovieUserRepository movieUserRepository;

    @PostMapping("/movie-users")
    public ResponseEntity<MovieUser> createMovieUser(@RequestBody MovieUser movieUser) throws URISyntaxException {
        if (movieUser.getId() != null) {
            throw new BadRequestAlertException("A new movieUser cannot already have an ID");
        }
        MovieUser result = movieUserRepository.createMovieUser(movieUser);
        return ResponseEntity.created(new URI("/netflix/movie-users/" + result.getId()))
                .body(result);
    }


    @GetMapping("/movie-users")
    public List<MovieUser> getAllMovieUsers(@RequestParam(required = false) String filter, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        if ("contact-is-null".equals(filter)) {
            return StreamSupport
                    .stream(movieUserRepository.getMovies().spliterator(), false)
                    .filter(movieUser -> movieUser.getContact() == null)
                    .collect(Collectors.toList());
        }
        return movieUserRepository.getAllWithRelationships();
    }

    @GetMapping("/movie-users/{id}")
    public ResponseEntity<MovieUser> getMovieUser(@PathVariable Long id) {
        List<MovieUser> movieUser = movieUserRepository.getOneWithRelationships(id);
        return new ResponseEntity<>(movieUser.get(0), HttpStatus.OK);
    }


}
