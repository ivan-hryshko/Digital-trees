package com.IvanEndorphin;

import static com.IvanEndorphin.Digital_tree.*;


/**
 * Created by IvanEndorphin on 24.08.2020.
 */
public class Cell {
    Tree ParentTree;
    Cell ParentCell;
    int x_Cell_posion;
    private int y_Cell_posion;
    int CellID;
    int GenomeValue;
    int CellBirthday;
    private boolean genomeWasDone = false;
    private boolean CellFall = false;



    public Cell(int y, int x, int g) {
        System.out.println("Cell inside");

        this.ParentTree = TreeList.get(TreeList.size()-1);
        System.out.println("Parrent wass added");

        System.out.println("Cell coordianate");


        this.x_Cell_posion = x;
        this.y_Cell_posion = y;
        System.out.println("Cell coordianate end");

        this.CellID = ParentTree.CellCount;
        this.ParentTree.IncreaseCellCount();

        this.GenomeValue = g;
        this.CellBirthday=Turn;



    }


    public Cell(int y, int x, Cell ParentCell, int genome) {
        DoTest("Second Cell start");

        DoTest("Parent added");


        this.x_Cell_posion = x;
        this.y_Cell_posion = y;


        this.ParentCell = ParentCell;
        this.ParentTree = ParentCell.ParentTree;

        this.CellID = ParentTree.CellCount;
        this.ParentTree.CellCount++;



        this.GenomeValue = genome;
        this.CellBirthday=Turn;

        wasSomeGrowth=true;


    }


    void checkSquare(int y, int x){

    }

    void CreateSeed(int parent_y, int parent_x,int newY, int newX, int genome){
        //Создать новую клетку списке в клеток дерева.
        this.ParentTree.CellList.add(new Cell(newY,newX,field[parent_y][parent_x],genome));

        //При
        field[newY][newX]=this.ParentTree.CellList.get(this.ParentTree.CellList.size()-1);

        field[parent_y][parent_x].makegenomeWasDoneTrue();


    }


    int getGenomeValue(){
        return GenomeValue;
    }


    void makegenomeWasDoneTrue(){
        this.genomeWasDone = true;
    }

    boolean isGenomeWasDone(){
        return this.genomeWasDone;
    }

    void setCellFallTrue(){
        this.CellFall = true;
    }

    void setCellFallFalse(){
        this.CellFall = false;
    }

    boolean isCellFall(){
        return CellFall;
    }


    public void y_Cell_posionPlus() {
        this.y_Cell_posion++;
    }

    public int getY_Cell_posion() {
        return y_Cell_posion;
    }
}
