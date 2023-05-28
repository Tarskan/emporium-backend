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

    public Response getByIdType(String idType) throws Exception {
        if (typeRepository.findById(idType) != null) {
            return Response.ok(typeRepository.findById(idType).orElseThrow(() -> new Exception("Type not found."))).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("IdType : " + idType + " non trouvé")
                    .build();
        }
    }

    public Response getTypeAutocomplete(String name) {
        return Response.ok(typeRepository.findTypeAutocomplete(name)).build();
    }

    public Response getMostThreePopular() {
        List<Type> listType = typeRepository.findAll();
        List<TypeDTO> typeOrdered = new ArrayList<TypeDTO>();
        for (int i = 0; i < listType.size(); i++) {
            int count = oeuvresRepository.findByIdType(listType.get(i).getIdType()).size();
            if(count > 0) {
                typeOrdered.add(new TypeDTO(listType.get(i), count, null));
            }
        }
        typeOrdered.sort(Comparator.comparingInt(dto -> -dto.count));
        if(typeOrdered.size() > 3) {
            typeOrdered = typeOrdered.subList(0,3);
        }
        String  typeImagePath = null;
        for (int i = 0; i < typeOrdered.size(); i++) {
            typeImagePath = oeuvresRepository.findByIdType((typeOrdered.get(i).getType().getIdType())).get(0).imagePath;
            typeOrdered.get(i).setImageTypePath(typeImagePath);
        }

        return Response.ok(typeOrdered).build();
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
