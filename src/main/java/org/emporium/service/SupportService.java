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
            return supportRepository.findById(idSupport).orElseThrow(() -> new Exception("Support not found."));
        } else {
            throw new IllegalArgumentException("Id Support: " + idSupport + " Non trouvée dans la bdd");
        }

    }

    public List<Support> getSupportAutocomplete(String name) {
        return supportRepository.findSupportTypeAutocomplete(name);
    }

    public Support addSupport(GenericCreateDTO support) throws Exception {
        Support supportNew = Support.builder()
                .name(support.name)
                .build();

        try {
            return supportRepository.save(supportNew);
        } catch(Exception e) {
            throw new IllegalArgumentException("Name Support: " + support.getName() + " en doublon dans la bdd");
        }
    }

    public Support modifySupport(GenericModifyDTO support) {
        if (supportRepository.existsById(support.getId())) {
            Support supportModified = Support.builder()
                    .idSupport(support.getId())
                    .name(support.getName())
                    .build();

            try {
                return supportRepository.save(supportModified);
            } catch(Exception e) {
                throw new IllegalArgumentException("Name Support: " + support.getName() + " en doublon dans la bdd");
            }
        } else {
            throw new IllegalArgumentException("Id Support: " + support.getId() + " Non trouvée dans la bdd");
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
