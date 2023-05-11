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

        return typeRepository.save(typeNew);
    }

    public Type modifyType(GenericModifyDTO type) {
        if (typeRepository.existsById(type.getId())) {
            Type typeModified = Type.builder()
                    .idType(type.getId())
                    .name(type.name)
                    .build();

            return typeRepository.save(typeModified);
        } else {
            throw new IllegalArgumentException("Id: " + type.getId() + " Non trouvée dans la bdd");
        }
    }

    public String suppType(String idType) throws Exception {
        Type typeToDelete = typeRepository.findById(idType).orElseThrow(() -> new Exception("Id " + idType + " n'existe pas ou a deja était supprimer"));
        typeRepository.delete(typeToDelete);
        return "Le type a était supprimer";
    }
}
