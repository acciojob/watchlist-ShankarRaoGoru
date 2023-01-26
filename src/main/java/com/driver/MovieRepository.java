package com.driver;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.*;

@Repository
public class MovieRepository {
    Map<String,Movie> movieMap=new HashMap<>();
    Map<String,Director> directorMap=new HashMap<>();
    Map<String, List<String>>dirmovmapping=new HashMap<>();

    public void addMovie(Movie movie){
        String name=movie.getName();
        movieMap.put(name,movie);
    }
    public void addDirector( Director director){
        String name=director.getName();
        directorMap.put(name,director);

    }
    public void addMovieDirectorPair(String movie,String dir){
        if(movieMap.containsKey(movie) && directorMap.containsKey(dir)){
            //movieMap.put(movie,movieMap.get(movie));
           // directorMap.put(dir,directorMap.get(dir));
            List<String>list=new ArrayList<>();
            if(dirmovmapping.containsKey(dir)) list=dirmovmapping.get(dir);
            list.add(movie);
            dirmovmapping.put(dir,list);
        }
    }
    public Movie getMovieByName( String movie){
        return movieMap.get(movie);
    }
    public Director getDirectorByName(String director){
        return directorMap.get(director);
    }
    public List<String> getMoviesByDirectorName(String dir){
        List<String>list=new ArrayList<>();
        if(dirmovmapping.containsKey(dir)) list=dirmovmapping.get(dir);
        return list;
    }
    public List<String> findAllMovies(){
       return new ArrayList<>(movieMap.keySet());
    }
    public void deleteDirectorByName(String dir){
        List<String>list=new ArrayList<>();
        if(dirmovmapping.containsKey(dir)){
            list=dirmovmapping.get(dir);
            for(String movies:list){
                if(movieMap.containsKey(movies)){
                    movieMap.remove(movies);
                }
            }
            dirmovmapping.remove(dir);
        }
        if(directorMap.containsKey(dir)){
            directorMap.remove(dir);
        }
    }
    public void deleteAllDirectors(){
        HashSet<String>movies=new HashSet<>();
        for(String dir:dirmovmapping.keySet()){
            for(String movie:dirmovmapping.get(dir)){
                movies.add(movie);
            }
        }
        for(String movie:movies){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
    }




}
