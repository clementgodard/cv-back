package fr.clems.cv.CV.utils;

import java.util.ArrayList;
import java.util.Iterator;

import fr.clems.cv.CV.entity.Categorie;
import fr.clems.cv.CV.entity.Ligne;
import fr.clems.cv.CV.pojo.CategorieCV;

public class CategorieUtil {
	
    public static ArrayList<CategorieCV> formatTree(ArrayList<Categorie> categories, boolean onlyActive) {
    	ArrayList<CategorieCV> res = new ArrayList<CategorieCV>();
    	
        for (Categorie c: categories) {
        	res.add(new CategorieCV(c));
        }
        
        ArrayList<Long> toRemove = new ArrayList<Long>();
        
        if (onlyActive) {
        	for(CategorieCV c: res) {
        		if (c.getLignes() != null && c.getLignes().size() > 0) {
	                Iterator<Ligne> it = c.getLignes().iterator();
	                while( it.hasNext() ) {
	                  Ligne l = it.next();
	                  
	                  if (!l.isActive())
	                	  it.remove();
	                }
        		}
        	}
        }
        
        for (Categorie categorie: categories) {
        	for(CategorieCV c: res) {
        		// Si une catégorie reconnait son parent
        		if (categorie.getParent() != null && c.getId() == categorie.getParent().getId()) {
        			for (CategorieCV c2: res) {
        				if (c2.getId() == categorie.getId())
        					c.enfants.add(c2);
        			}
    				toRemove.add(categorie.getId());
        		}
        	}
        }
        
        Iterator<CategorieCV> it = res.iterator();
        while( it.hasNext() ) {
          CategorieCV c = it.next();
          
          for (Long idToRemove: toRemove) {
	          if( c.getId() == idToRemove ) {	        	  
	        	  it.remove();
	          }
          }
        }
        
    	return res;
    }
}
