package fr.clems.cv.CV.dao;

import fr.clems.cv.CV.entity.Ligne;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LigneDAO extends JpaRepository<Ligne, Long>{
    
}
