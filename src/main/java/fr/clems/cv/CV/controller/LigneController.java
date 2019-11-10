package fr.clems.cv.CV.controller;

import fr.clems.cv.CV.dao.LigneDAO;
import fr.clems.cv.CV.entity.Ligne;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("ligne")
public class LigneController {
    
    @Autowired
    private LigneDAO ligneDao;
    
    @GetMapping("/")
    public List<Ligne> getAll() 
    {
        return this.ligneDao.findAll();
    }
    
    @GetMapping("/{id}")
    public Ligne getById(Long id) {
        return this.ligneDao.findById(id).get();
    }
    
    @PostMapping("/")
    public boolean add(Ligne ligne) {
        Ligne l = this.ligneDao.save(ligne);
        
        return l.getId() > 0;
    }
    
    @DeleteMapping("/{id}")
    public String delete(Ligne ligne) {
        this.ligneDao.deleteById(ligne.getId());
        
        return "Salut ! C'est moi !";
    }
}
