package org.emporium.service;

import org.emporium.model.*;
import org.emporium.repository.CollectionRepository;
import org.emporium.repository.OeuvresRepository;
import org.emporium.repository.TypeRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.core.Response;
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

    public Response getAllType() {
        return Response.ok(typeRepository.findAll()).build();
    }

    public Response getByIdType(String IdOeuvre) throws Exception {
        return Response.ok(typeRepository.findById(IdOeuvre).orElseThrow(() -> new Exception("Type not found."))).build();
    }

    public Response getTypeAutocomplete(String name) {
        return Response.ok(typeRepository.findTypeAutocomplete(name)).build();
    }

    public Response getMostThreePopular() {
        List<Type> listType = typeRepository.findAll();
        List<TypeDTO> typeOrdered = new ArrayList<TypeDTO>();
        for (int i = 0; i < listType.size(); i++) {
            int count = oeuvresRepository.findByIdType(listType.get(i).getIdType()).size();
            typeOrdered.add(new TypeDTO(listType.get(i), count));
        }
        typeOrdered.sort(Comparator.comparingInt(dto -> -dto.count));
        return Response.ok(typeOrdered.subList(0,3)).build();
    }

    public Response addType(GenericCreateDTO type) throws Exception {
        Type typeNew =  Type.builder()
                .name(type.name)
                .build();

        try {
            return Response.ok(typeRepository.save(typeNew)).build();
        } catch(Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Name Type: " + type.getName() + " en doublon dans la bdd")
                    .build();
        }

    }

    public Response modifyType(GenericModifyDTO type) {
        if (typeRepository.existsById(type.getId())) {
                Type typeModified = Type.builder()
                        .idType(type.getId())
                        .name(type.name)
                        .build();

            try {
                return Response.ok(typeRepository.save(typeModified)).build();
            } catch(Exception e) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Name Type: " + type.getName() + " en doublon dans la bdd")
                        .build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Type: " + type.getId() + " Non trouvée dans la bdd")
                    .build();
        }
    }

    public Response suppType(String idType) throws Exception {
        if (typeRepository.existsById(idType)) {
            Type typeToDelete = typeRepository.findById(idType).orElseThrow(() -> new Exception("Id " + idType + " n'existe pas ou a deja était supprimer"));
            typeRepository.delete(typeToDelete);
            return Response.ok("Le type a était supprimer").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Id Type " + idType + " n'existe pas ou a deja était supprimer")
                    .build();
        }
    }
}
