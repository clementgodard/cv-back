package fr.clems.cv.CV.pojo;

import java.util.ArrayList;
import java.util.List;

import fr.clems.cv.CV.entity.Categorie;
import fr.clems.cv.CV.entity.Ligne;

public class CategorieCV {
	public Long id;
	public String libelle;
	public int position;
	public boolean active;
	public List<Ligne> lignes;
	public List<CategorieCV> enfants;
	public boolean imageCategorie;
	
	public CategorieCV(Categorie c) {
		this.id = c.getId();
		this.libelle = c.getLibelle();
		this.position = c.getPosition();
		this.active = c.isActive();
		this.lignes = c.getLignes();
		this.imageCategorie = c.isImageCategorie();
		
		this.enfants = new ArrayList<CategorieCV>();
	}
	
	public boolean equals(Categorie cat) {
		return this.id == cat.getId();
	}
}
