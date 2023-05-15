package org.emporium.service;

import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.model.Support;
import org.emporium.repository.SupportRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

@Singleton
@Service
public class SupportService {
    @Inject
    SupportRepository supportRepository;

    public List<Support> getAllSupport() {
        return supportRepository.findAll();
    }

    public Support getByIdSupport(String idSupport) throws Exception {
        if (supportRepository.existsById(idSupport)) {
            return supportRepository.findById(idSupport).orElseThrow(() -> new Exception("Type not found."));
        } else {
            throw new IllegalArgumentException("Id: " + idSupport + " Non trouvée dans la bdd");
        }

    }

    public List<Support> getSupportAutocomplete(String name) {
        return supportRepository.findSupportTypeAutocomplete(name);
    }

    public Support addSupport(GenericCreateDTO support) throws Exception {
        if(!Objects.equals(supportRepository.findByName(support.getName()).getName(), support.getName())) {
            Support supportNew = Support.builder()
                    .name(support.name)
                    .build();

            return supportRepository.save(supportNew);
        } else {
            throw new IllegalArgumentException("Name: " + support.getName() + " en doublon dans la bdd");
        }
    }

    public Support modifySupport(GenericModifyDTO support) {
        if (supportRepository.existsById(support.getId())) {
            if(!Objects.equals(supportRepository.findByName(support.getName()).getName(), support.getName())) {
                Support supportModified = Support.builder()
                        .idSupport(support.getId())
                        .name(support.getName())
                        .build();

                return supportRepository.save(supportModified);
            } else {
                throw new IllegalArgumentException("Name: " + support.getName() + " en doublon dans la bdd");
            }
        } else {
            throw new IllegalArgumentException("Id: " + support.getId() + " Non trouvée dans la bdd");
        }
    }

    public String suppSupport(String idSupport) throws Exception {
        if (supportRepository.existsById(idSupport)) {
            Support supportToDelete = supportRepository.findById(idSupport).orElseThrow(() -> new Exception("Id " + idSupport + " n'existe pas ou a deja était supprimer"));
            supportRepository.delete(supportToDelete);
            return "Le support a était supprimer";
        } else {
            throw new IllegalArgumentException("Id: " + idSupport + " Non trouvée dans la bdd");
        }
    }
}
