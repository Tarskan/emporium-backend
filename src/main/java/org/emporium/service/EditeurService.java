package org.emporium.service;

import org.emporium.model.Auteur;
import org.emporium.model.Editeur;
import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.repository.EditeurRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import java.util.List;

@Singleton
@Service
public class EditeurService {
    @Inject
    EditeurRepository editeurRepository;

    public Response getAllEditeur() {
        return Response.ok(editeurRepository.findAll()).build();
    }

    public Response getByIdEditeur(String idEditeur) throws Exception {
        if (editeurRepository.existsById(idEditeur)) {
            return Response.ok(editeurRepository.findById(idEditeur)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("id Editeur: " + idEditeur + " en doublon dans la bdd")
                .build();
    }

    public Response getEditeurAutocomplete(String name) {
        return Response.ok(editeurRepository.findEditeurAutocomplete(name)).build();
    }

    public Response addEditeur(GenericCreateDTO editeur) throws Exception {
        if (editeurRepository.findByName(editeur.name.toLowerCase()) == null) {
            Editeur editeurNew = Editeur.builder()
                    .name(editeur.name)
                    .build();

            return Response.ok(editeurRepository.save(editeurNew)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Name Editeur: " + editeur.getName() + " en doublon dans la bdd")
                    .build();
        }
    }

    public Response modifyEditeur(GenericModifyDTO editeur) {
        if (editeurRepository.existsById(editeur.getId())) {
            if(editeurRepository.findByName(editeur.name.toLowerCase()) == null) {
                Editeur editeurModified = Editeur.builder()
                    .idEditeur(editeur.getId())
                    .name(editeur.getName())
                    .build();

                return Response.ok(editeurRepository.save(editeurModified)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Name Editeur: " + editeur.getName() + " en doublon dans la bdd")
                        .build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Editeur: " + editeur.getId() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response suppEditeur(String idEditeur) throws Exception {
        if (editeurRepository.existsById(idEditeur)) {
            Editeur editeurToDelete = editeurRepository.findById(idEditeur).orElseThrow(() -> new Exception("Id " + idEditeur + " n'existe pas ou a deja était supprimer"));
            editeurRepository.delete(editeurToDelete);
            return Response.ok("L'editeur a était supprimer").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Editeur: " + idEditeur + " Non trouvée dans la bdd")
                    .build();
        }
    }
}
