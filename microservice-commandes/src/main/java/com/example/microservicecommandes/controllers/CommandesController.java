package com.example.microservicecommandes.controllers;

import com.example.microservicecommandes.configurations.ApplicationPropertiesConfiguration;
import com.example.microservicecommandes.models.Commande;
import com.example.microservicecommandes.services.v1.CommandesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Commandes")
public class CommandesController implements HealthIndicator {
    @Autowired
    CommandesService commandesService;

    @Autowired
    ApplicationPropertiesConfiguration appProperties;


    @GetMapping
    public List<Commande> getAllCommandes(){
        System.out.println("### Number commande to return : " + appProperties.getCommandesLast());

        var lastXDays = appProperties.getCommandesLast();
        var dateLastXDays = LocalDateTime.now().minusDays(lastXDays);

        var allCom = commandesService.getAllCommandes();


        List<Commande> lastXComs = allCom.stream()
                .filter(c -> c.getDate().isAfter(dateLastXDays))
                .sorted(Comparator.comparing(Commande::getDate).reversed())
                .collect(Collectors.toList());

        return lastXComs;
    }

    @GetMapping( "/{id}")
    public Commande getCommande(@PathVariable(name = "id") Integer id){
        return commandesService.getCommande(id);
    }

    @PostMapping()
    public Commande postCommande(@RequestBody Commande commande){
        commandesService.Add(commande);
        return commande;
    }

    @DeleteMapping("/{id}")
    public boolean deleteCommande(@PathVariable Integer id){
        return commandesService.DeleteCommande(id);
    }

    @Override
    public Health health() {
        var commandes = commandesService.getAllCommandes();
        return commandes.isEmpty() ? Health.down().build() : Health.up().build();
    }
}
