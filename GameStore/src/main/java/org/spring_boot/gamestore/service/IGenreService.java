package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.entity.Genre;

import java.util.List;

public interface IGenreService {

    public Genre saveGenre(Genre genre);

    public List<Genre> getAllGenres();

    public Boolean existGenre(String name);

    public Boolean deleteGenre(int id);

    public Genre getGenreById(int id);
}
