package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Genre;
import org.spring_boot.gamestore.repository.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService implements IGenreService{

    @Autowired
    private GenreRepo genreRepo;

    @Override
    public Genre saveGenre(Genre category) {
        return genreRepo.save(category);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepo.findAll();
    }

    @Override
    public Boolean existGenre(String name) {
        return genreRepo.existsByName(name);
    }

    @Override
    public Boolean deleteGenre(int id) {
        Genre genre = genreRepo.findById(id).orElse(null);
        if(genre != null){
            genreRepo.delete(genre);
            return true;
        }
        return false;
    }

    @Override
    public Genre getGenreById(int id) {
        Genre genre = genreRepo.findById(id).orElse(null);
        return genre;
    }
}















