package com.IvanEndorphin;

import java.util.ArrayList;

import static com.IvanEndorphin.Digital_tree.DoTest;
import static com.IvanEndorphin.Digital_tree.FieldRect;

/**
 * Created by IvanEndorphin on 24.08.2020.
 */
public class Tree {
    ArrayList<Cell> CellList = new ArrayList<Cell>();

    int [][] TreeGenome=new int[16][4];
    int CellCount=0;
    int startGenomeNumber =0;
    int age;
    boolean alive = true;

    //Для первой созданной семечки
    public Tree(){
        DoTest("Before new Genome");

        GenerateGenome();

        DoTest("Im here - genome was created");


    }

    //Для всех последующих клеток
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

        DoTest("Genome create star method");

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
       public void soutGenomeVertical(){
           System.out.println("Tree genome");
           System.out.println();

        for (int i = 0; i < TreeGenome.length; i++) {
            System.out.print(i+" - \" ");

            System.out.println("Gen №:"+i+"  Left:"+TreeGenome[i][0]+"   Up:"+TreeGenome[i][1]+"   Right:"+TreeGenome[i][2]+"   Down:"+TreeGenome[i][3]);


        }
    }



    void addFirsCell(int y, int x){
        Cell cell = new Cell(y,x, startGenomeNumber);
        CellList.add(cell);
        DoTest("Cell was created");


        FieldRect[y][x].setCell(cell);

        DoTest("Im here 2 - cell with coo=ordinate");

        DoTest("Im here 3 - Field X");


    }




}
