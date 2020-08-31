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
    Rect CellRect;



    //Для первой созданной клетки
    public Cell(int y, int x, int g) {
        DoTest("Cell inside");

        this.ParentTree = TreeList.get(TreeList.size()-1);
        DoTest("Parrent wass added");

        DoTest("Cell coordianate");


        this.x_Cell_posion = x;
        this.y_Cell_posion = y;
        DoTest("Cell coordianate end");

        this.CellID = ParentTree.CellCount;
        this.ParentTree.IncreaseCellCount();


        DoTest("ID & Parent Tree");

        this.GenomeValue = g;
        this.CellBirthday=Turn;

        DoTest("before change color");


        FieldRect[y][x].changeColorYellow();
        app.repaint();

    }

    //Для всех последующих клеток
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

        FieldRect[y][x].changeColorYellow();


        wasSomeGrowth=true;


        app.repaint();


    }


    void checkSquare(int y, int x){

    }

    void CreateSeed(int parent_y, int parent_x,int newY, int newX, int genome){
        //Создать новую клетку списке в клеток дерева.
        Cell cell = new Cell(newY,newX,FieldRect[parent_y][parent_x].RectCell,genome);
        cell.ParentTree.CellList.add(cell);

        //При
        FieldRect[newY][newX].setCell(cell);

        FieldRect[parent_y][parent_x].RectCell.makegenomeWasDoneTrue();
        FieldRect[parent_y][parent_x].changeColorGreen();
        app.repaint();



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
        FieldRect[y_Cell_posion][x_Cell_posion].changeColorYellow();
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
