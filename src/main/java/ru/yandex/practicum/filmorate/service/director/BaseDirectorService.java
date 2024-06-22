package ru.yandex.practicum.filmorate.service.director;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.NotFoundException;
import ru.yandex.practicum.filmorate.model.Director;
import ru.yandex.practicum.filmorate.repository.director.DirectorRepository;
import ru.yandex.practicum.filmorate.repository.film.FilmRepository;
import ru.yandex.practicum.filmorate.service.validation.ValidationService;

import java.util.Collection;
import java.util.LinkedHashSet;

@Service
@RequiredArgsConstructor
public class BaseDirectorService implements DirectorService {

    private final DirectorRepository directorRepository;
    private final ValidationService validationService;
    private final FilmRepository filmRepository;

    @Override
    public Collection<Director> getAll() {
        return directorRepository.getAll();
    }

    @Override
    public Director getById(long directorId) {
        return directorRepository.getById(directorId).orElseThrow(
                () -> new NotFoundException("Директор с id=" + directorId + " не найден"));
    }

    @Override
    public Director save(Director director) {
        validationService.validateNewData(director);
        return directorRepository.save(director);
    }

    @Override
    public Director update(Director director) {
        validationService.validateNewData(director);
        return directorRepository.update(director);
    }

    @Override
    public void deleteById(long directorId) {
        directorRepository.deleteById(directorId);
    }

    @Override
    public LinkedHashSet<Director> getDirectors(long filmId) {
        filmRepository.getById(filmId);
        return directorRepository.getDirectors(filmId);
    }
}