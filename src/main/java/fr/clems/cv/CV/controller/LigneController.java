package fr.clems.cv.CV.controller;

import fr.clems.cv.CV.dao.LigneDAO;
import fr.clems.cv.CV.entity.Ligne;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("ligne")
public class LigneController {
    
    @Autowired
    private LigneDAO ligneDao;
    
    @GetMapping("/")
    public List<Ligne> getAll() 
    {
        return this.ligneDao.getAllByActiveOrderByDateFin();
        // return this.ligneDao.findAll();
    }
    
    @GetMapping("/{id:[\\d]+}")
    public Ligne getById(@PathVariable("id") Long id, HttpServletResponse response) {
        Optional<Ligne> opt = this.ligneDao.findById(id);
        
        if (opt.isPresent())
            return opt.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de ligne correspodante");
    }

    @PostMapping("/")
    public boolean add(Ligne ligne) {
    	
    	// Faire un try catch
        Ligne l = this.ligneDao.save(ligne);
        
        return l.getId() > 0;
    }
    
    @DeleteMapping("/{id:[\\d]+}")
    public boolean delete(@PathVariable("id") Long id) {
    	// Faire un try catch
        this.ligneDao.deleteById(id);
        return true;
    }
}
