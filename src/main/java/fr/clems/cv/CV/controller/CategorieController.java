package fr.clems.cv.CV.controller;

import fr.clems.cv.CV.dao.CategorieDAO;
import fr.clems.cv.CV.entity.Categorie;
import fr.clems.cv.CV.entity.Ligne;
import fr.clems.cv.CV.pojo.CategorieCV;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("categorie")
public class CategorieController {
    
    @Autowired
    private CategorieDAO categorieRepo;
    
    // Retourne toutes les catégories active avec les lignes actives à l'intérieurs
    @GetMapping("/")
    public List<CategorieCV> getAll(@RequestParam(defaultValue="false", name="actif") boolean active) {
        ArrayList<Categorie> lc = new ArrayList<Categorie>(this.categorieRepo.getAllActiveOrderByPositionAsc());

        ArrayList<CategorieCV> res = new ArrayList<CategorieCV>();

        /*
        for (Categorie c : lc) {
            
            ArrayList<Ligne> r = new ArrayList<Ligne>();
            
            for (Ligne l : c.getLignes()) {
                if (l.isActive() || active)
                    r.add(l);
            }
            
            c.setLignes(r);
            
            if (c.getParent() == null)
            	res.add(new CategorieCV(c));
        }
        
        for (Categorie c: lc) {
        	if (c.getParent() != null) {
        		for (CategorieCV cat: res) {
        			if (cat.equals(c.getParent())) {
        				cat.enfants.add(new CategorieCV(c));
        			}
        		}
        	}
        }
        */
        
        res = this.formatTree(lc);
        
        return res;
    }
    
    @GetMapping("/{id:[\\d]+}")
    public Categorie getById(@PathVariable("id") Long id, HttpServletResponse response) {
        Optional<Categorie> opt = this.categorieRepo.findById(id);
        
        if (opt.isPresent())
            return opt.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de categorie correspondante");
    }
    
    @PostMapping("/")
    public boolean add(Categorie cat) {
    	
    	// Faire un try catch (pour toute erreur de contrainte sql par exemple)
    	
        return this.categorieRepo.save(cat).getId() != null;
    }
    
    @DeleteMapping("/{id:[\\d]+}")
    public boolean delete(@PathVariable("id") Long id) {
    	try {
	        this.categorieRepo.deleteById(id);
	        return true;
		} catch (EmptyResultDataAccessException e) {
			return false;
		}
    }
    
    private ArrayList<CategorieCV> formatTree(ArrayList<Categorie> categories) {
    	ArrayList<CategorieCV> res = new ArrayList<CategorieCV>();
    	
        for (Categorie c: categories) {
        	res.add(new CategorieCV(c));
        }
        
        ArrayList<Long> toRemove = new ArrayList<Long>();
        
        int index = 0;
        for (Categorie categorie: categories) {
        	for(CategorieCV c: res) {
        		
        		// Si une catégorie reconnait son parent
        		if (categorie.getParent() != null && c.getId() == categorie.getParent().getId()) {        			
    				c.enfants.add(new CategorieCV(categorie));
    				toRemove.add(categorie.getId());
        		}
        	}
        }
        
        Iterator<CategorieCV> it = res.iterator();
        while( it.hasNext() ) {
         
          CategorieCV c = it.next();
          
          for (Long idToRemove: toRemove) {
	          if( c.getId() == idToRemove ) {	        	  
	        	  // Le problème est que la catégorie avec des enfants est supprimée alors que la catégorie sans enfants dans res est envoyée
	        	  it.remove();
	          }
          }
        }
        
    	return res;
    }
}















