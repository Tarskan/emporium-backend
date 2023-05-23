package org.emporium.service;

import org.emporium.model.GenericCreateDTO;
import org.emporium.model.GenericModifyDTO;
import org.emporium.model.Type;
import org.emporium.repository.TypeRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Service
public class TypeService {
    @Inject
    TypeRepository typeRepository;

    public List<Type> getAllType() {
        return typeRepository.findAll();
    }

    public Type getByIdType(String IdOeuvre) throws Exception {
        return typeRepository.findById(IdOeuvre).orElseThrow(() -> new Exception("Type not found."));
    }

    public List<Type> getTypeAutocomplete(String name) {
        return typeRepository.findTypeAutocomplete(name);
    }

    public Type addType(GenericCreateDTO type) throws Exception {
        Type typeNew =  Type.builder()
                .name(type.name)
                .build();

        try {
            return typeRepository.save(typeNew);
        } catch(Exception e) {
            throw new IllegalArgumentException("Name Type: " + type.getName() + " en doublon dans la bdd");
        }

    }

    public Type modifyType(GenericModifyDTO type) {
        if (typeRepository.existsById(type.getId())) {
                Type typeModified = Type.builder()
                        .idType(type.getId())
                        .name(type.name)
                        .build();

            try {
                return typeRepository.save(typeModified);
            } catch(Exception e) {
                throw new IllegalArgumentException("Name Type: " + type.getName() + " en doublon dans la bdd");
            }
        } else {
            throw new IllegalArgumentException("Id Type: " + type.getId() + " Non trouvée dans la bdd");
        }
    }

    public String suppType(String idType) throws Exception {
        if (typeRepository.existsById(idType)) {
            Type typeToDelete = typeRepository.findById(idType).orElseThrow(() -> new Exception("Id " + idType + " n'existe pas ou a deja était supprimer"));
            typeRepository.delete(typeToDelete);
            return "Le type a était supprimer";
        } else {
            return "Id Type " + idType + " n'existe pas ou a deja était supprimer";
        }
    }
}
