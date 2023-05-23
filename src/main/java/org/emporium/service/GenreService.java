package org.emporium.service;

import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.model.Genre;
import org.emporium.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

@Singleton
@Service
public class GenreService {
    @Inject
    GenreRepository genreRepository;

    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    public Genre getByIdGenre(String idGenre) throws Exception {
        if (genreRepository.existsById(idGenre)) {
            return genreRepository.findById(idGenre).orElseThrow(() -> new Exception("Genre not found."));
        } else {
            throw new IllegalArgumentException("Id Genre: " + idGenre + " Non trouvée dans la bdd");
        }
    }

    public List<Genre> getGenreAutocomplete(String name) {
        return genreRepository.findGenreAutocomplete(name);
    }

    public Genre addGenre(GenericCreateDTO genre) {
        Genre genreNew = Genre.builder()
                .name(genre.name)
                .build();

        try {
            return genreRepository.save(genreNew);
        } catch(Exception e) {
            throw new IllegalArgumentException("Name Genre: " + genre.getName() + " en doublon dans la bdd");
        }
    }

    public Genre modifyGenre(GenericModifyDTO genre) {
        if (genreRepository.existsById(genre.getId())) {
            Genre genreModified = Genre.builder()
                    .idGenre(genre.getId())
                    .name(genre.getName())
                    .build();

            try {
                return genreRepository.save(genreModified);
            } catch(Exception e) {
                throw new IllegalArgumentException("Name Genre: " + genre.getName() + " en doublon dans la bdd");
            }
        } else {
            throw new IllegalArgumentException("Id Genre: " + genre.getId() + " Non trouvée dans la bdd");
        }
    }

    public String suppGenre(String idGenre) throws Exception {
        if (genreRepository.existsById(idGenre)) {
            Genre genreToDelete = genreRepository.findById(idGenre).orElseThrow(() -> new Exception("Id " + idGenre + " n'existe pas ou a deja était supprimer"));
            genreRepository.delete(genreToDelete);
            return "Le genre a était supprimer";
        } else {
            throw new IllegalArgumentException("Id Genre: " + idGenre + " Non trouvée dans la bdd");
        }
    }
}
