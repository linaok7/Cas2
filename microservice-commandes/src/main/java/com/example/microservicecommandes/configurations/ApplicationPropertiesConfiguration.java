package com.example.microservicecommandes.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-config-ms")
@RefreshScope
public class ApplicationPropertiesConfiguration {
    // correspond à la propriété « mes-configs.commandesLast » dans le fichier de configuration du MS
    private int commandesLast;

    public int getCommandesLast() {
        System.out.println("### limitDeProduits : " + commandesLast);
        return commandesLast;
    }

    public void setCommandesLast(int commandesLast) {
        this.commandesLast = commandesLast;
    }
}
