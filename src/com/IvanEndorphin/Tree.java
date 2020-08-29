package com.IvanEndorphin;

import java.util.ArrayList;
import java.util.List;

import static com.IvanEndorphin.Digital_tree.TreeList;
import static com.IvanEndorphin.Digital_tree.field;

/**
 * Created by IvanEndorphin on 24.08.2020.
 */
public class Tree {
    ArrayList<Cell> CellList = new ArrayList<Cell>();

    int [][] TreeGenome=new int[16][4];
    int CellCount=0;
    int starnGenomeNumber =0;
    int age;
    boolean alive = true;


    public Tree(){
        System.out.println("Before new Genome");

        GenerateGenome();

        System.out.println("Im here - genome was created");


    }
    public Tree(Cell cell){
        cell.setCellFallFalse();
        this.TreeGenome = cell.ParentTree.TreeGenome;
        this.age=0;
        this.CellList.add(cell);


    }

    void IncreaseCellCount  (){
        this.CellCount++;
    }


    void GenerateGenome() {

        System.out.println("Genome create star method");

        //Make First line genome possible to Growth
        int counter=(int) (Math.random()*16);

        for (int i = 0; i <4 ; i++) {
            TreeGenome[0][i] = counter;
            counter=(int) (Math.random()*16);
        }


        //Random genome code for the other line
            for (int i = 0; i < this.TreeGenome.length; i++) {
                for (int j = 0; j <TreeGenome[0].length ; j++) {
                    TreeGenome[i][j] = counter;
                    counter=(int) (Math.random()*32);
                }

            }



    }
    public void soutGenome(){
        for (int i = 0; i < TreeGenome.length; i++) {
            System.out.print(i+" - \" ");
            for (int j = 0; j <TreeGenome[0].length ; j++) {

                System.out.print(TreeGenome[i][j]+", ");


            }

            System.out.print("\";     ");

        }
    }


    void addFirsCell(int y, int x){

        CellList.add(new Cell(y,x,starnGenomeNumber));
        System.out.println("Cell was created");

        field[y][x] = CellList.get(0);

        System.out.println("Im here 2 - cell with coo=ordinate");

        System.out.println("Im here 3 - Field X");


    }




}
