package fr.clems.cv.CV.controller;

import fr.clems.cv.CV.dao.CategorieDAO;
import fr.clems.cv.CV.entity.Categorie;
import fr.clems.cv.CV.pojo.CategorieCV;
import fr.clems.cv.CV.utils.CategorieUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
        
        res = CategorieUtil.formatTree(lc, onlyActive);
        
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
    public Categorie add(Categorie cat) {
    	try {
    		return this.categorieRepo.save(cat);
    	} catch (IllegalArgumentException e) {
    		return null;
    	}
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

    @PatchMapping("/{id:[\\d]+}")
    public boolean partialUpdate(@PathVariable("id") Long id, Categorie patch) {
    	Categorie categorie = this.categorieRepo.findById(id).get();
    	
    	if (patch.getLibelle() != null && !patch.getLibelle().equals(categorie.getLibelle())) {
    		categorie.setLibelle(patch.getLibelle());
    	}
    	
    	if (patch.getParent() != null && !patch.getParent().equals(categorie.getParent())) {
    		categorie.setParent(patch.getParent());
    	}
    	
    	if (patch.getPosition() != null && patch.getPosition() != categorie.getPosition()) {
    		categorie.setPosition(patch.getPosition());
    	}
    	
    	if (patch.isActive() != null && patch.isActive() != categorie.isActive()) {
    		categorie.setActive(patch.isActive());
    	}
    	
    	if (patch.isImageCategorie() != null && patch.isImageCategorie() != categorie.isImageCategorie()) {
    		categorie.setImageCategorie(patch.isImageCategorie());
    	}
    	 
    	if (patch.getLignes() != null && patch.getLignes() != categorie.getLignes()) {
    		categorie.setLignes(patch.getLignes());
    	}
    	
    	try {
    		this.categorieRepo.save(categorie);
    		return true;
    	} catch (IllegalArgumentException e) {
    		return false;
    	}
    }
}















