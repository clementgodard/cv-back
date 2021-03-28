package fr.clems.cv.CV.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="categorie")
public class Categorie implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "Categorie_SEQ", strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String libelle;
    private Integer position;
    private Boolean active;

    @JsonManagedReference
    @OneToMany(mappedBy="categorie", cascade=CascadeType.ALL, orphanRemoval=true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Ligne> lignes;
    
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(targetEntity=Categorie.class)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Categorie parent;
    
    private Boolean imageCategorie = false;
            
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(List<Ligne> lignes) {
        this.lignes = lignes;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Categorie getParent() {
        return parent;
    }

    public void setParent(Categorie parent) {
        this.parent = parent;
    }

	public Boolean isImageCategorie() {
		return imageCategorie;
	}

	public void setImageCategorie(Boolean imageCategorie) {
		this.imageCategorie = imageCategorie;
	}
    
}
