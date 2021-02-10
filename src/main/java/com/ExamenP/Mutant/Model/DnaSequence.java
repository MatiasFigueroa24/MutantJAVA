package com.ExamenP.Mutant.Model;

public class DnaSequence {
    private String[] dna;
    public DnaSequence() {

    }
    public DnaSequence(String[] dna) {
        this.dna = dna;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
