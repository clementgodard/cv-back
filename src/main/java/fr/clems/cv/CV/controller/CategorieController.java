package fr.clems.cv.CV.controller;

import fr.clems.cv.CV.dao.CategorieDAO;
import fr.clems.cv.CV.entity.Categorie;
import fr.clems.cv.CV.entity.Ligne;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("categorie")
public class CategorieController {
    
    @Autowired
    private CategorieDAO categorieRepo;
    
    @GetMapping("/")
    public List<Categorie> getAll() {
        List<Categorie> cats = this.categorieRepo.findAll();
        
        cats.sort((Categorie c1, Categorie c2) -> {
            return c1.getPosition() - c2.getPosition();
        });
        
        return cats;
    }
    
    @GetMapping("/{id:[\\d]+}")
    public Categorie getById(@PathVariable("id") Long id, HttpServletResponse response) {
        Optional<Categorie> opt = this.categorieRepo.findById(id);
        
        if (opt.isPresent())
            return opt.get();
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pas de categorie correspodante");
    }
    
    @GetMapping("testadd")
    public String test() {
        
        Categorie c = new Categorie();
        c.setLibelle("Catégorie test");
        c.setPosition(0);
        
        Ligne li1 = new Ligne();
        li1.setCategorie(c);
        li1.setContenu("Hello tour le monde");
        li1.setDate("20/01/2020");
        
        Ligne li2 = new Ligne();
        li2.setCategorie(c);
        li2.setContenu("Hello tour le monde 5");
        li2.setDate("20/05/2020");
        
        ArrayList<Ligne> al = new ArrayList<Ligne>();
        al.add(li1);
        al.add(li2);
        
        c.setLignes(al);
        
        this.categorieRepo.save(c);
        
        return "hello :)";
    }
}
