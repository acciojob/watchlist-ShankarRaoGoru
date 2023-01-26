package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
       movieService.addMovie(movie);
       return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }
    //Pair an existing movie and director: PUT /movies/add-movie-director-pair
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("moviename") String moviename,@RequestParam("dirname") String dirname){
       movieService.addMovieDirectorPair(moviename,dirname);
       return new ResponseEntity<>("success",HttpStatus.CREATED);

    }
    @GetMapping("get-movie-by-name/{name}")
    public ResponseEntity getMovieByName(@PathVariable("name") String name){
       Movie movie=movieService.getMovieByName(name);
       return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }
    @GetMapping("get-director-by-name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable("name") String name){
        Director director=movieService.getDirectorByName(name);
        return new ResponseEntity<>(director,HttpStatus.FOUND);
    }
    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String dir){
        List<String>l=movieService.getMoviesByDirectorName(dir);
        return new ResponseEntity<>(l,HttpStatus.FOUND);
    }
    @GetMapping("/get-all-movies")

    public ResponseEntity findAllMovies(){
       List<String>l=movieService.findAllMovies();
       return new ResponseEntity<>(l,HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String dir){
        movieService.deleteDirectorByName(dir);
        return new ResponseEntity<>("success",HttpStatus.CREATED);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
     movieService.deleteAllDirectors();
     return new ResponseEntity<>("success",HttpStatus.CREATED);
    }

}
