package com.example.microservicecommandes.services.v1;

import com.example.microservicecommandes.dao.DbContext;
import com.example.microservicecommandes.models.Commande;
import com.example.microservicecommandes.services.ICommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandesService implements ICommandeService {
    @Autowired
    DbContext _context;

    @Override
    public boolean Add(Commande commande) {
        try{
            _context.getCommandesDAO().save(commande);
            return true;
        }
        catch (Exception exp){
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public Commande getCommande(Integer id) {
        var commande = _context.getCommandesDAO().findById(id);
        return commande.orElse(null);
    }

    @Override
    public List<Commande> getAllCommandes() {
        var commmandes = _context.getCommandesDAO().findAll();
        return  commmandes;
    }

    @Override
    public boolean UpdateCommande(Commande commande) {
        try{
            if(!_context.getCommandesDAO().existsById(commande.getId())){
                return false;
            }
            _context.getCommandesDAO().save(commande);
            return true;
        }
        catch (Exception exp){
            exp.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean DeleteCommande(Integer id) {
        try{
            var commande = getCommande(id);
            _context.getCommandesDAO().delete(commande);
            return true;
        }
        catch (Exception exp){
            exp.printStackTrace();
            return false;
        }
    }
}
