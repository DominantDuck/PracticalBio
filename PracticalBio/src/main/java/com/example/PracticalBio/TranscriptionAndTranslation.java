package com.example.PracticalBio;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.RNASequence;
import org.biojava.nbio.core.sequence.template.SequenceView;

import java.util.Random;

public class TranscriptionAndTranslation {
    private String DNA;
    private String RNA;
    private String ProSeq;
    private int length = 9;
    private boolean passed1 = false;

    public TranscriptionAndTranslation() {
        Random rand = new Random();
        StringBuilder dnaSequence = new StringBuilder(length);
        String bases = "ACGT";

        for (int i = 0; i < length; i++) {
            char randomBase = bases.charAt(rand.nextInt(bases.length()));
            dnaSequence.append(randomBase);
        }

        DNA = dnaSequence.toString();
    }

    public String beginProblem(String inputRNA) throws CompoundNotFoundException {
        boolean errorPresent = false;

        DNASequence seq = new DNASequence(DNA.toUpperCase());
        RNASequence transcriptionSeq = seq.getRNASequence();
        SequenceView rnaSeq = transcriptionSeq.getComplement();

        RNA = rnaSeq.getSequenceAsString();
        inputRNA = inputRNA.toUpperCase();
        RNA = RNA.replaceAll("\\s", "");

        //Check inputRNA
        inputRNA = inputRNA.replaceAll("\\s", "");
        if (DNA.length() != inputRNA.length()) {
            errorPresent = true;
        }
        for (int i = 0; i < inputRNA.length(); i++) {
            char x = ' ';
            x = inputRNA.charAt(i);
            x = Character.toUpperCase(x);
            if (x != 'G' && x != 'U' && x != 'A' && x != 'C') {
                errorPresent = true;
            }
        }
        if (RNA.equals(inputRNA) == false) {
            errorPresent = true;
        }
        if (errorPresent) {
            return ("Incorrect. Try Again." + RNA); //leave for meantime
        }
        passed1 = true;
        return "Correct";
    }

    public String endProblem (String inputPro) throws CompoundNotFoundException {
        RNASequence seq = new RNASequence(RNA.toUpperCase());
        ProSeq = seq.getProteinSequence().getSequenceAsString();

        StringBuilder proteinBuilder = new StringBuilder();
        boolean stopFound = false;

        String[][] aminoAcids = { {"A", "Ala"}, {"R", "Arg"}, {"N", "Asn"}, {"D", "Asp"}, {"C", "Cys"}, {"E", "Glu"}, {"Q", "Gln"}, {"G", "Gly"}, {"H", "His"}, {"I", "Ile"}, {"L", "Leu"}, {"K", "Lys"}, {"M", "Met"}, {"F", "Phe"}, {"P", "Pro"}, {"S", "Ser"}, {"T", "Thr"}, {"W", "Trp"}, {"Y", "Tyr"}, {"V", "Val"} };

        for (int i = 0; i < ProSeq.length(); i++) {
            char singleLetterName = ProSeq.charAt(i);
            for (int j = 0; j < aminoAcids.length; j++) {
                if (aminoAcids[j][0].charAt(0) == singleLetterName) {
                    proteinBuilder.append(aminoAcids[j][1]);
                    proteinBuilder.append(" ");
                    break;
                }
                if (ProSeq.charAt(i) == '*') {
                    proteinBuilder.append("STOP");
                    proteinBuilder.append(" ");
                    stopFound = true;
                    break;
                }
            }
            if (stopFound == true) {
                break;
            }
        }
        ProSeq = proteinBuilder.toString().toUpperCase();
        if(inputPro.toUpperCase().equals(ProSeq)) {
            return "Correct";
        }

        return "Try Again. Correct ansewer: " + ProSeq;
    }

    public String getDNA() {
        return DNA;
    }

    public String getRNA() {
        return "Correct: " + RNA;
    }

    public boolean isPassed1() {
        return passed1;
    }
}
