package fr.clems.cv.CV.controller;

import fr.clems.cv.CV.dao.LigneDAO;
import fr.clems.cv.CV.entity.Categorie;
import fr.clems.cv.CV.entity.Ligne;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("ligne")
public class LigneController {
    
    @Autowired
    private LigneDAO ligneDao;
    
    
    @Value("${spring.servlet.multipart.location}")
    private String imgPath;    
   
    @GetMapping("/")
    public List<Ligne> getAll() 
    {
        // return this.ligneDao.getAllByActiveOrderByDateFin();
        return this.ligneDao.findAll();
    }
    
    @GetMapping("/{id:[\\d]+}")
    public Ligne getById(@PathVariable("id") Long id) {
        Optional<Ligne> opt = this.ligneDao.findById(id);
        
        if (opt.isPresent())
            return opt.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de ligne correspodante");
    }
    
    @GetMapping("/categorie/{id:[\\d]+}")
    public Categorie getCategorieByLigne(@PathVariable("id") Long id) {
    	return this.getById(id).getCategorie();
    }

    @PostMapping("/")
    public boolean add(Ligne ligne, @RequestParam("file") Optional<MultipartFile> file) {
    	
    	Ligne l = this.ligneDao.save(ligne);
    	
    	if (file.isPresent()) {
    		MultipartFile image = file.get();
    		
    		if (image.getContentType().equals("image/jpeg") || image.getContentType().equals("image/jpg") || image.getContentType().equals("image/png")) {
		    	try {
		    		File finalPath = new File(this.imgPath);
		    		
		    		if (!finalPath.exists())
		    			finalPath.mkdirs();
		    		
		    		String fileName = l.getId() + image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf('.'));
		    		
		    		File destination = new File(finalPath, fileName);
		    		
		    		image.transferTo(destination);
		    		
					l.setImage("assets/cv/upload/"+fileName);
					
					l = this.ligneDao.save(l);
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	}
    	
        return l.getId() > 0;
    }
    
    @DeleteMapping("/{id:[\\d]+}")
    public boolean delete(@PathVariable("id") Long id) {
    	try {
    		this.ligneDao.deleteById(id);
    		return true;
    	} catch (EmptyResultDataAccessException e) {
    		return false;
    	}
    }
    
    @PutMapping("/")
    public boolean update(Ligne ligne) {
    	try {
    		this.ligneDao.save(ligne);
    		return true;
    	} catch (IllegalArgumentException e) {
    		return false;
    	}
    }
    
    @PatchMapping("/{id:[\\d]+}")
    public boolean partialUpdate(@PathVariable("id") Long id, Ligne patch, @RequestParam("file") Optional<MultipartFile> file) {   	
    	
    	Ligne ligne = this.ligneDao.findById(id).get();
    	
    	if (patch.getCategorie() != null && patch.getCategorie() != ligne.getCategorie()) {
    		ligne.setCategorie(patch.getCategorie());
    	}
    	
    	if (patch.getContenu() != null && patch.getContenu() != ligne.getContenu()) {
    		ligne.setContenu(patch.getContenu());
    	}
    	
    	// if (patch.getDateDebut() != null && patch.getDateDebut() != ligne.getDateDebut()) {
    		ligne.setDateDebut(patch.getDateDebut());
    	// }
    	
    	// if (patch.getDateFin() != null && patch.getDateFin() != ligne.getDateFin()) {
    		ligne.setDateFin(patch.getDateFin());
    	// }
    	
    	if (patch.getImage() != null && patch.getImage() != ligne.getImage()) {
    		ligne.setImage(patch.getImage());
    	}
    	
    	// if (patch.getLien() != null && patch.getLien() != ligne.getLien()) {
    		ligne.setLien(patch.getLien());
    	// }
    	
    	// if (patch.getNote() != null && patch.getNote() != ligne.getNote()) {
    		ligne.setNote(patch.getNote());
    	// }
    	
    	if (patch.getPosition() != null && patch.getPosition() != ligne.getPosition()) {
    		ligne.setPosition(patch.getPosition());
    	}
    	
    	if (patch.isActive() != null && patch.isActive() != ligne.isActive()) {
    		ligne.setActive(patch.isActive());
    	}
    	
    	if (patch.isOnlyYear() != null && patch.isOnlyYear() != ligne.isOnlyYear()) {
    		ligne.setOnlyYear(patch.isOnlyYear());
    	}
    	
    	if (file.isPresent()) {
    		MultipartFile image = file.get();
    		
    		if (image.getContentType().equals("image/jpeg") || image.getContentType().equals("image/jpg") || image.getContentType().equals("image/png")) {
		    	try {
		    		File finalPath = new File(this.imgPath);
		    		
		    		if (!finalPath.exists())
		    			finalPath.mkdirs();
		    		
		    		String fileName = ligne.getId() + image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf('.'));
		    		
		    		File destination = new File(finalPath, fileName);
		    		
		    		if (destination.exists()) {
		    			destination.delete();
		    		}
		    		
		    		image.transferTo(destination);
					
					ligne.setImage("assets/cv/upload/"+fileName);
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    	try {
    		this.ligneDao.save(ligne);
    		return true;
    	} catch (IllegalArgumentException e) {
    		return false;
    	}
    }
}
