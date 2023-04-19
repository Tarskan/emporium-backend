package org.emporium.service;

import org.emporium.repository.CollectionRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Service
public class CollectionService {

    @Inject
    private final CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }
    
}