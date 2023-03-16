package org.emporium.service;

import org.emporium.model.Bibliotheque;
import org.emporium.repository.BibliothequeRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class BibliothequeService {

    @Inject
    BibliothequeRepository bibliothequeRepository;

    public List<Bibliotheque> GetAllBibliotheque() {
        List<Bibliotheque> ListBibliotheque = bibliothequeRepository.findAll();
        return ListBibliotheque;
    }
}
