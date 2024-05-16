package ru.yandex.practicum.filmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @GetMapping("/films/{id}")
    public Film get(@PathVariable long id) {
        log.info("==> GET /films/" + id);
        Film film = filmService.getById(id);
        log.info("<== GET /users/" + id + "  Фильм: " + id);
        return film;
    }

    @GetMapping("/films")
    public Collection<Film> getAll() {
        log.info("==> GET /films ");
        Collection<Film> allFilms = filmService.getAll();
        log.info("<== GET /films Список всех сохраненных фильмов размером: "
                + allFilms.size() + " возвращен");
        return allFilms;
    }

    @GetMapping("/films/popular")
    public Collection<Film> getMostLikedFilms(@RequestParam long count) {
        log.info("==> GET /films/popular?count=" + count);
        Collection<Film> mostLikedFilms = filmService.getMostLikedFilms(count);
        log.info("<== GET /films/popular?count="
                + "Самые популярные фильмы в количестве: " + mostLikedFilms.size());
        return mostLikedFilms;
    }

    @PostMapping("/films")
    public Film save(@RequestBody Film film) {
        log.info("==> POST /films " + film);
        Film newFilm = filmService.save(film);
        log.info("<== POST /films" + newFilm);
        return newFilm;
    }

    @PutMapping("/films")
    public Film update(@RequestBody Film film) {
        log.info("==> PUT /films " + film);
        Film updatedFilm = filmService.update(film);
        log.info("<== PUT /films" + updatedFilm);
        return updatedFilm;
    }

    @PutMapping("/films/{id}/like/{userId}")
    public void addLike(@PathVariable long id, @PathVariable long userId) {
        log.info("==> PUT /films/" + id + "/like/" + userId);
        filmService.addLike(id, userId);
        log.info("<== PUT /films/" + id + "/like/" + userId + "  Лайк фильму " + filmService.getById(id)
                + " от пользователя с ID=" + userId + " поставлен");
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public void deleteLike(@PathVariable long id, @PathVariable long userId) {
        log.info("==> DELETE /films/" + id + "/like/" + userId);
        filmService.deleteLike(id, userId);
        log.info("<== DELETE /films/" + id + "/like/" + userId + "  Лайк фильму " + filmService.getById(id)
                + " от пользователя с ID=" + userId + " удален");
    }


}






