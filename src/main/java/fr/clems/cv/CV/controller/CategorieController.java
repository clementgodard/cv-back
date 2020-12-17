package fr.clems.cv.CV.controller;

import fr.clems.cv.CV.dao.CategorieDAO;
import fr.clems.cv.CV.entity.Categorie;
import fr.clems.cv.CV.entity.Ligne;
import fr.clems.cv.CV.pojo.CategorieCV;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
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
        List<Categorie> lc = this.categorieRepo.getAllActiveOrderByPositionAsc();

        ArrayList<CategorieCV> res = new ArrayList<CategorieCV>();
        
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
    	
    	// Faire un try catch
    	
        this.categorieRepo.deleteById(id);
        return true;
    }
}
