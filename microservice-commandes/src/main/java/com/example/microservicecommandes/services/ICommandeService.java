package com.example.microservicecommandes.services;

import com.example.microservicecommandes.models.Commande;

import java.util.List;

public interface ICommandeService {
    boolean Add(Commande commande);
    Commande getCommande(Integer id);
    List<Commande> getAllCommandes();
    boolean UpdateCommande(Commande commande);
    boolean DeleteCommande(Integer id);
}
