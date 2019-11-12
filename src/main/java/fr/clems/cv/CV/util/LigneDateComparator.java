/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.clems.cv.CV.util;

import fr.clems.cv.CV.entity.Ligne;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author cleme
 */
public class LigneDateComparator implements Comparator<Ligne> {

    @Override
    public int compare(Ligne o1, Ligne o2) {
        Pattern p = Pattern.compile("\\d{2}\\/\\d{2}\\/\\d{4}") ;  
  
        String s1 = o1.getDate();
        String s2 = o2.getDate();
        
        Matcher m = p.matcher(s1);
        
        String[] tabDate1 = new String[2];
        int i=0;
        while (m.find()) {
            tabDate1[i++] = m.group();
        }
        
        m = p.matcher(s2);
        
        String[] tabDate2 = new String[2];
        i=0;
        while (m.find()) {
            tabDate2[i++] = m.group();
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            Date dateDebutLigne1 = sdf.parse(tabDate1[0]);
            Date dateFinLigne1   = sdf.parse(tabDate1[1]);
            
            Date dateDebutLigne2 = sdf.parse(tabDate2[0]);
            Date dateFinLigne2   = sdf.parse(tabDate2[1]);
            
            return dateDebutLigne2.compareTo(dateDebutLigne1);
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return 0;
    }
    
}
