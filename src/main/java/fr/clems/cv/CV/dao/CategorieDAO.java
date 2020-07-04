package fr.clems.cv.CV.dao;

import fr.clems.cv.CV.entity.Categorie;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CategorieDAO extends JpaRepository<Categorie, Long>{
    
    @Query("SELECT c FROM Categorie c "
            + "WHERE c.active = 1 ORDER BY c.position ASC")
    public List<Categorie> getAllActiveOrderByPositionAsc();
    
    public List<Categorie> findAllByOrderByPositionAsc();
    
}