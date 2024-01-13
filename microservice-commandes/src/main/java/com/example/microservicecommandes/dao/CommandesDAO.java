package com.example.microservicecommandes.dao;

import com.example.microservicecommandes.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
public interface CommandesDAO extends JpaRepository<Commande, Integer> {
}
