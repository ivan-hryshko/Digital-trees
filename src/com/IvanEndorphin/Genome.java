package com.IvanEndorphin;

/**
 * Created by IvanEndorphin on 24.08.2020.
 */
public class Genome {
    int [][] GenomeCode = new int[16][4];
    int counter = (int) (Math.random()*32);

    public Genome(){
        System.out.println("Im here 4 - Public Genome");

        for (int i = 0; i < GenomeCode.length; i++) {
            for (int j = 0; j <GenomeCode[0].length ; j++) {
                GenomeCode[i][j] = counter;
                counter=(int) (Math.random()*32);

            }

        }


    }

    public void soutGenome(){
        for (int i = 0; i < GenomeCode.length; i++) {
            System.out.print(i+" - \" ");
            for (int j = 0; j <GenomeCode[0].length ; j++) {

                System.out.print(GenomeCode[i][j]+", ");


            }

            System.out.print("\";     ");

        }
    }


}
