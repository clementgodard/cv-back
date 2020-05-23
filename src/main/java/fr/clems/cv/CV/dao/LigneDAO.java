package fr.clems.cv.CV.dao;

import fr.clems.cv.CV.entity.Ligne;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface LigneDAO extends JpaRepository<Ligne, Long>{
//    @Query("SELECT l FROM Ligne l WHERE l.categorie LIKE ?1 ORDER BY l.date DESC, l.contenu ASC")
//    public List<Ligne> getLignesByCategorie(String categorie);
}
