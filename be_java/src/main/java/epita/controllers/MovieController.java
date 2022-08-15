package epita.controllers;

import epita.datamodel.Movie;
import epita.exceptions.BadRequestAlertException;
import epita.exceptions.ResourceNotFoundException;
import epita.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/netflix")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) throws URISyntaxException {

        Movie movieObj = movieRepository.getByExternalID(movie.getExternalId());
        if (movieObj != null) {
            if (movieObj.getExternalId().equals(movie.getExternalId())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            throw new BadRequestAlertException("A Movie is already exisits" + movie.getExternalId());
            }
        }

        if (movie.getId() != null) {
            throw new BadRequestAlertException("A new movie cannot already have an ID");
        }
        Movie result = movieRepository.createMovie(movie);
        return ResponseEntity.created(new URI("/netflix/movies/" + result.getId()))
                .body(result);
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepository.getMovies();
    }


    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = movieRepository.getById(id);
        if(!movie.getId().equals(id)){
            throw new ResourceNotFoundException("Not found movie with id = " + id);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/get-movie/{externalid}")
    public ResponseEntity<Movie> getMoviebyExternalid(@PathVariable String externalid) {
        Movie movie = movieRepository.getByExternalID(externalid);
        if(!movie.getExternalId().equals(externalid)){
            throw new ResourceNotFoundException("Not found movie with id = " + externalid);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Movie movie = movieRepository.getById(id);
        if(!movie.getId().equals(id)){
            throw new ResourceNotFoundException("Not found Role with id = " + id);
        }
        else{
            movieRepository.deleteByName(movie.getTitle());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
