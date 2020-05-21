package fr.clems.cv.CV.dao;

import fr.clems.cv.CV.entity.Categorie;
import fr.clems.cv.CV.entity.Ligne;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface CategorieDAO extends JpaRepository<Categorie, Long>{
}
