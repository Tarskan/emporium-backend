package org.emporium.service;

import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.model.Support;
import org.emporium.repository.SupportRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;

@Singleton
@Service
public class SupportService {
    @Inject
    SupportRepository supportRepository;

    public Response getAllSupport() {
        return Response.ok(supportRepository.findAll()).build();
    }

    public Response getByIdSupport(String idSupport) throws Exception {
        if (supportRepository.existsById(idSupport)) {
            return Response.ok(supportRepository.findById(idSupport).orElseThrow(() -> new Exception("Support not found."))).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Support: " + idSupport + " Non trouvée dans la bdd")
                    .build();
        }

    }

    public Response getSupportAutocomplete(String name) {
        return Response.ok(supportRepository.findSupportTypeAutocomplete(name)).build();
    }

    public Response addSupport(GenericCreateDTO support) throws Exception {
        if (supportRepository.findByName(support.name.toLowerCase()) == null) {
            Support supportNew = Support.builder()
                .name(support.name)
                .build();

            return Response.ok(supportRepository.save(supportNew)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Name Support: " + support.getName() + " en doublon dans la bdd")
                    .build();
        }
    }

    public Response modifySupport(GenericModifyDTO support) {
        if (supportRepository.existsById(support.getId())) {
            if (supportRepository.findByName(support.name.toLowerCase()) == null) {
                Support supportModified = Support.builder()
                    .idSupport(support.getId())
                    .name(support.getName())
                    .build();

                return Response.ok(supportRepository.save(supportModified)).build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Name Support: " + support.getName() + " en doublon dans la bdd")
                        .build();

            }
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Support: " + support.getId() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response suppSupport(String idSupport) throws Exception {
        if (supportRepository.existsById(idSupport)) {
            Support supportToDelete = supportRepository.findById(idSupport).orElseThrow(() -> new Exception("Id " + idSupport + " n'existe pas ou a deja était supprimer"));
            supportRepository.delete(supportToDelete);
            return Response.ok("Le support a était supprimer").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id support: " + idSupport + " Non trouvée dans la bdd")
                    .build();
        }
    }
}
