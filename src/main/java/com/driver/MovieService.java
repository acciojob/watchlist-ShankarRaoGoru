package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }
    public void addDirector( Director director){
        movieRepository.addDirector(director);

    }
    public void addMovieDirectorPair(String movie,String dir){
       movieRepository.addMovieDirectorPair(movie,dir);
    }
    public Movie getMovieByName( String movie){
        return movieRepository.getMovieByName(movie);
    }
    public Director getDirectorByName(String director){
        return movieRepository.getDirectorByName(director);
    }
    public List<String> getMoviesByDirectorName(String dir){
        return movieRepository.getMoviesByDirectorName(dir);
    }
    public List<String> findAllMovies(){
        return movieRepository.findAllMovies();
    }
    public void deleteDirectorByName(String dir){
     movieRepository.deleteDirectorByName(dir);
    }
    public void deleteAllDirectors(){
       movieRepository.deleteAllDirectors();
    }

}
