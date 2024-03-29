package org.emporium.service;

import org.emporium.model.Auteur;
import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.repository.AuteurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;

@Singleton
@Service
public class AuteurService {
    @Inject
    AuteurRepository auteurRepository;

    public Response getAllAuteur() {
        return Response.ok(auteurRepository.findAll()).build();
    }

    public Response getByIdAuteur(String idAuteur) throws Exception {
        if (auteurRepository.existsById(idAuteur)) {
            return Response.ok(auteurRepository.findById(idAuteur)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Auteur: " + idAuteur + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response getAuteurAutocomplete(String name) {
        return Response.ok(auteurRepository.findAuteurAutocomplete(name)).build();
    }

    public Response addAuteur(GenericCreateDTO auteur) {
        if (auteurRepository.findByName(auteur.name.toLowerCase()) == null) {
            Auteur auteurNew = Auteur.builder()
                    .name(auteur.name)
                    .build();

            return Response.ok(auteurRepository.save(auteurNew)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Name Auteur: " + auteur.getName() + " en doublon dans la bdd")
                    .build();
        }
    }

    public Response modifyAuteur(GenericModifyDTO auteur) {
        if (auteurRepository.existsById(auteur.getId())) {
            if(auteurRepository.findByName(auteur.name.toLowerCase()) == null) {
                Auteur auteurModified = Auteur.builder()
                        .idAuteur(auteur.getId())
                        .name(auteur.getName())
                        .build();

                return Response.ok(auteurRepository.save(auteurModified)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Name Auteur: " + auteur.getName() + " en doublon dans la bdd")
                        .build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Auteur: " + auteur.getId() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response suppAuteur(String idAuteur) throws Exception {
        Auteur auteurToDelete = auteurRepository.findById(idAuteur).orElseThrow(() -> new Exception("Id " + idAuteur + " n'existe pas ou a deja était supprimer"));
        auteurRepository.delete(auteurToDelete);
        return Response.ok("L'auteur a était supprimer").build();
    }
}
