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
import org.springframework.web.bind.annotation.PutMapping;
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
    public List<CategorieCV> getAll(@RequestParam(defaultValue="true", name="actif") boolean onlyActive) {
    	
    	ArrayList<Categorie> lc;
    	
    	if (onlyActive) 
    		lc = new ArrayList<Categorie>(this.categorieRepo.getAllActiveOrderByPositionAsc());
    	else
    		lc = new ArrayList<Categorie>(this.categorieRepo.findAll());
    	
        ArrayList<CategorieCV> res = new ArrayList<CategorieCV>();
        
        res = this.formatTree(lc);
        
        return res;
    }
    
    @GetMapping("/{id:[\\d]+}")
    public Categorie getById(@PathVariable("id") Long id) {
        Optional<Categorie> opt = this.categorieRepo.findById(id);
        
        if (opt.isPresent())
            return opt.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de categorie correspondante");
    }
    
    @GetMapping("/parent/{id:[\\d]+}")
    public Long getIdParent(@PathVariable("id") Long id) {
    	Optional<Categorie> opt = this.categorieRepo.findById(id);
    	
    	if (opt.isPresent()) {
    		if (opt.get().getParent() != null)
    			return opt.get().getParent().getId();
    	}
    	
    	return null;
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
    
    @PutMapping("/")
    public boolean update(Categorie categorie) {
    	try {
    		this.categorieRepo.save(categorie);
    		return true;
    	} catch (IllegalArgumentException e) {
    		return false;
    	}
    }
    
    private ArrayList<CategorieCV> formatTree(ArrayList<Categorie> categories) {
    	ArrayList<CategorieCV> res = new ArrayList<CategorieCV>();
    	
        for (Categorie c: categories) {
        	res.add(new CategorieCV(c));
        }
        
        ArrayList<Long> toRemove = new ArrayList<Long>();
        
        for (Categorie categorie: categories) {
        	for(CategorieCV c: res) {
        		// Si une catégorie reconnait son parent
        		if (categorie.getParent() != null && c.getId() == categorie.getParent().getId()) {
        			for (CategorieCV c2: res) {
        				if (c2.getId() == categorie.getId())
        					c.enfants.add(c2);
        			}
    				toRemove.add(categorie.getId());
        		}
        	}
        }
        
        Iterator<CategorieCV> it = res.iterator();
        while( it.hasNext() ) {
          CategorieCV c = it.next();
          
          for (Long idToRemove: toRemove) {
	          if( c.getId() == idToRemove ) {	        	  
	        	  it.remove();
	          }
          }
        }
        
    	return res;
    }
}















