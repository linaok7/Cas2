package com.example.microservicecommandes.fakedata;

import com.example.microservicecommandes.dao.DbContext;
import com.example.microservicecommandes.models.Commande;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

@Data
@NoArgsConstructor
@Service
public class FakeData {

    @Autowired
    DbContext _context;

    @Bean
    public CommandLineRunner initializeTestData() {
        return args -> {
            // Generate and save 30 fake Commande entities
            for (int i = 0; i < 30; i++) {
                Commande commande = new Commande();
                commande.setDescription("Description " + (i + 1));
                commande.setQuantite(ThreadLocalRandom.current().nextInt(1, 10));
                commande.setDate(generateRandomDate());
                commande.setMontant(ThreadLocalRandom.current().nextFloat() * 100);
                commande.setIdProduit(i);

                _context.getCommandesDAO().save(commande);
            }
        };
    }

    private LocalDateTime generateRandomDate() {
        // Generate a random date between today and 30 days ago
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime thirtyDaysAgo = today.minusDays(30);

        long randomDay = ThreadLocalRandom.current().nextLong(
                thirtyDaysAgo.toEpochSecond(ZoneOffset.UTC),
                today.toEpochSecond(ZoneOffset.UTC)
        );

        return LocalDateTime.ofEpochSecond(randomDay, 0, ZoneOffset.UTC);
    }
}
