package fr.clems.cv.CV.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ligne")
public class Ligne implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "Ligne_SEQ", strategy = GenerationType.AUTO)
    private Long id;
    private String contenu;
    private Float note;
    private Integer position;
    
    @JsonBackReference
    @ManyToOne(targetEntity=Categorie.class)
    @JoinColumn(name="categorie_id", nullable=false)
    private Categorie categorie;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateDebut;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateFin;
    
    @Column(name="onlyYearDate")
    private Boolean onlyYear;
    
    private URL lien;
    private String image;
    
    private Boolean active;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }   

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public URL getLien() {
        return lien;
    }

    public void setLien(URL lien) {
        this.lien = lien;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

	public Boolean isOnlyYear() {
		return onlyYear;
	}

	public void setOnlyYear(Boolean onlyYear) {
		this.onlyYear = onlyYear;
	}
	
	@Override
	public String toString() {
		String res = "";
		
		res += (this.contenu != null) ? this.contenu + "\n" : "";
		res += this.active + "\n";
		res += (this.image != null) ? this.image + "\n" : "";
		res += (this.dateDebut != null) ? this.dateDebut.toString() + "\n" : "";
		res += (this.dateFin != null) ? this.dateFin.toString() + "\n" : "";
		res += this.position + "\n";
		res += this.note + "\n";
		res += this.onlyYear + "\n";
		res += (this.lien != null) ? this.lien.toString() + "\n" : "";
		res += (this.categorie != null) ? this.categorie.getId() + "\n" : "";
		
		return res;
	}
}
