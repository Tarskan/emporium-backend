package org.emporium.service;

import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.model.Genre;
import org.emporium.repository.GenreRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Singleton
@Service
public class GenreService {
    @Inject
    GenreRepository genreRepository;

    public Response getAllGenre() {
        return Response.ok(genreRepository.findAll()).build();
    }

    public Response getByIdGenre(String idGenre) throws Exception {
        if (genreRepository.existsById(idGenre)) {
            return Response.ok(genreRepository.findById(idGenre).orElseThrow(() -> new Exception("Genre not found."))).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Genre: " + idGenre + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getGenreAutocomplete(String name) {
        return Response.ok(genreRepository.findGenreAutocomplete(name)).build();
    }

    public Response addGenre(GenericCreateDTO genre) {
        if (genreRepository.findByName(genre.name.toLowerCase()) == null) {
            Genre genreNew = Genre.builder()
                    .name(genre.name)
                    .build();
            return Response.ok(genreRepository.save(genreNew)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Name Genre: " + genre.getName() + " en doublon dans la bdd")
                    .build();
        }
    }

    public Response modifyGenre(GenericModifyDTO genre) {
        if (genreRepository.existsById(genre.getId())) {
            if (genreRepository.findByName(genre.name.toLowerCase()) == null) {
                Genre genreModified = Genre.builder()
                    .idGenre(genre.getId())
                    .name(genre.getName())
                    .build();

                return Response.ok(genreRepository.save(genreModified)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Name Genre: " + genre.getName() + " en doublon dans la bdd")
                        .build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Genre: " + genre.getId() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response suppGenre(String idGenre) throws Exception {
        if (genreRepository.existsById(idGenre)) {
            Genre genreToDelete = genreRepository.findById(idGenre).orElseThrow(() -> new Exception("Id " + idGenre + " n'existe pas ou a deja était supprimer"));
            genreRepository.delete(genreToDelete);
            return Response.ok("Le genre a était supprimer").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Genre: " + idGenre + " Non trouvée dans la bdd")
                    .build();
        }
    }
}
