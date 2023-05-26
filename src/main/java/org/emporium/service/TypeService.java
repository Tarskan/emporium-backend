package org.emporium.service;

import org.emporium.model.*;
import org.emporium.repository.CollectionRepository;
import org.emporium.repository.OeuvresRepository;
import org.emporium.repository.TypeRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Singleton
@Service
public class TypeService {
    @Inject
    TypeRepository typeRepository;

    @Inject
    OeuvresRepository oeuvresRepository;

    public List<Type> getAllType() {
        return typeRepository.findAll();
    }

    public Type getByIdType(String IdOeuvre) throws Exception {
        return typeRepository.findById(IdOeuvre).orElseThrow(() -> new Exception("Type not found."));
    }

    public List<Type> getTypeAutocomplete(String name) {
        return typeRepository.findTypeAutocomplete(name);
    }

    public List<TypeDTO> getMostThreePopular() {
        List<Type> listType = typeRepository.findAll();
        List<TypeDTO> typeOrdered = new ArrayList<TypeDTO>();
        for (int i = 0; i < listType.size(); i++) {
            int count = oeuvresRepository.findByIdType(listType.get(i).getIdType()).size();
            typeOrdered.add(new TypeDTO(listType.get(i), count));
        }
        typeOrdered.sort(Comparator.comparingInt(dto -> -dto.count));

        return typeOrdered.subList(0,3);
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
