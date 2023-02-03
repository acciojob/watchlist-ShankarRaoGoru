package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.*;

@Repository
public class MovieRepository {
    Map<String,Movie>movieMap=new HashMap<>();
    Map<String,Director>directorMap=new HashMap<>();
    Map<String,List<String>>dirmovmap=new HashMap<>();
    public String addMovie(Movie movie){
        String name=movie.getName();
        movieMap.put(name,movie);
        return "success";
    }
    public String addDirector(Director director){
        String name=director.getName();
        directorMap.put(name,director);
        return "success";
    }
    public String addMovieDirectorPair(String movie,String dir){
        if(movieMap.containsKey(movie) && directorMap.containsKey(dir)){
            if(dirmovmap.containsKey(dir)){
                dirmovmap.get(dir).add(movie);
            }else{
                List<String>movies=new ArrayList<>();
                movies.add(movie);
                dirmovmap.put(dir,movies);
            }
        }
        return "success";
    }
    public Movie getMovieByName( String movie){
        if(movieMap.containsKey(movie)){
            return movieMap.get(movie);
        }
        return null;
    }
    public Director getDirectorByName(String director){
        if(directorMap.containsKey(director)){
            return directorMap.get(director);
        }
        return null;
    }
    //Get List of movies name for a given director name
    public List<String> getMoviesByDirectorName(String dir){
        List<String>dirmovies=new ArrayList<>();
        if(dirmovmap.containsKey(dir)){
            dirmovies=dirmovmap.get(dir);
        }
        return dirmovies;
    }
    //Get List of all movies added
    public List<String> findAllMovies(){
        List<String>movies=new ArrayList<>();
        for(String s:movieMap.keySet()){
            movies.add(s);
        }
        return movies;
    }
    //Delete a director and its movies from the records: DELETE /movies/delete-director-by-name
    public void deleteDirectorByName(String dir){
        if(directorMap.containsKey(dir)){
            if(dirmovmap.containsKey(dir)){
                List<String>list=new ArrayList<>();
                list=dirmovmap.get(dir);
                for(String s:list){
                    movieMap.remove(s);
                }
                dirmovmap.remove(dir);
            }
            directorMap.remove(dir);
        }
    }
    public void deleteAllDirectors(){
        ArrayList<String> list=new ArrayList<>();
        for(String s:dirmovmap.keySet())
        {
            for(String m:dirmovmap.get(s))
                list.add(m);
        }
        for(String i:list)
            movieMap.remove(i);
    }




}
