package fr.clems.cv.CV.utils;

import java.util.Comparator;

import fr.clems.cv.CV.entity.Ligne;

public class LigneSorter implements Comparator<Ligne> {
    public int compare(Ligne a, Ligne b)
    {
        return a.getPosition() - b.getPosition();
    }
}