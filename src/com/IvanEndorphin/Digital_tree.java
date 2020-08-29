package com.IvanEndorphin;

import java.util.ArrayList;
import java.util.List;

public class Digital_tree {

    public static Cell[][] field;
    public static ArrayList <Tree> TreeList = new ArrayList<Tree>();
    public static int Turn = 0;
    public static boolean wasSomeGrowth=false;
    public static int MaxTreeAge = 10;
    public static boolean DoTest = false;
    public static int TurnMaximum = 20;




    public static void main(String[] args) throws InterruptedException {

        //Создаем поле
        CreateField();

            //Основной действующий цикл
            //while (true) {
                //Начальная позиция первого семени
            System.out.println("Im here 5 - before new tree");

        TreeList.add( new Tree());
        TreeList.get(0).addFirsCell(7,5);


        while (true){

            growth();

            soutField();


            Turn++;
            //exitIfTurnCome();

           // exitIfNoGrowth();
           // endTest();
            soutTreeList();



            wasSomeGrowth=false;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




        }


        }

    private static void soutTreeList() {



        for (int i = 0; i <TreeList.size(); i++) {


            if (TreeList.get(i).alive==true){
                System.out.println(i+"_"+TreeList.get(i).age);

            }

        }
    }

    private static void exitIfTurnCome() {
        if (Turn>TurnMaximum){
            System.out.println("Exit");
            System. exit(0);


        }
    }

    private static void endTest() {

        ArrayList<String> sout =new ArrayList<String>();



        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {

               /* if (field[i][j]!=null){
                    System.out.println("Y_ "+ String.valueOf(i) +" X_ "+String.valueOf(j)+" - "+field[i][j].isCellFall());

                }*/
                if (field[i][j]!=null){


                    sout.add("Y_ ");
                    sout.add(String.valueOf(i));
                    sout.add(" X_ ");
                    sout.add(String.valueOf(j));
                    sout.add(" ID_");
                    sout.add(String.valueOf(field[i][j].CellID));
                    sout.add(" gWD_");
                    sout.add(String.valueOf(field[i][j].isGenomeWasDone()));
                    sout.add(" CellFall_");
                    sout.add(String.valueOf(field[i][j].isCellFall()));




                    for (int k = 0; k < sout.size(); k++) {
                        System.out.print(sout.get(k));
                    }
                    sout.clear();

                    System.out.println();



                }



            }

        }





    }

    private static void exitIfNoGrowth() {

        if  (wasSomeGrowth==false&&Turn!=0&&Turn!=1){

            boolean isSomeOneFall=false;
            for (int i = 0; i <field.length ; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    if (field[i][j].isCellFall()==true){

                        isSomeOneFall=true;

                    }

                }

            }

            if (isSomeOneFall==false){
                System.out.println("Exit");
                System. exit(0);

            }


//            for (int i = 0; i <TreeList.get(0).CellList.size() ; i++) {
//                System.out.println("Y: "+TreeList.get(0).CellList.get(i).y_Cell_posion+" X: "+TreeList.get(0).CellList.get(i).x_Cell_posion+" - "+ TreeList.get(0).CellList.get(i).isGenomeWasDone());
//
//            }


            System.out.println("Exit");
            System. exit(0);
        }

    }

    static void CreateField(){
            //Создаем поле
            //Размеры поля - 1-й у-высота, 2-й х-длина

            field = new Cell[8][11];



        }

        static void soutField(){
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {

                    if (field[i][j]==null){
                        System.out.print("  |");
                    }
                    else if (field[i][j].isGenomeWasDone()==false||field[i][j].CellBirthday==Turn){
                        if(field[i][j].getGenomeValue()<10){
                            System.out.print(" "+field[i][j].getGenomeValue()+"|");

                        }
                        else {
                            System.out.print(""+field[i][j].getGenomeValue()+"|");

                        }

                    }
                     else if (field[i][j].CellBirthday!=Turn){
                        System.out.print(" X|");

                    }



                }
                System.out.println();

            }

            System.out.println("Turn: "+Turn);

            TreeList.get(0).soutGenome();

            System.out.println();





        }


        static void growth(){

                for (int i = field.length-1; i >=0; i--) {
                    for (int j = 0; j < field[0].length ; j++) {

                        checkSquare(i,j);
                }
            }
                makeOlderTree();
        }


    private static void makeOlderTree() {
        for (int i = 0; i < TreeList.size() ; i++) {
                if (TreeList.get(i).alive==true){
                TreeList.get(i).age++;
                if (TreeList.get(i).age>MaxTreeAge){
                    makeDead(TreeList.get(i));

                }
            }
        }
    }

    private static void makeDead(Tree tree) {
            //Если семя не отросток то у далить его с поля
        for (int i = 0; i < tree.CellList.size(); i++) {
            if (tree.CellList.get(i).isGenomeWasDone()==true){
                field[tree.CellList.get(i).getY_Cell_posion()][tree.CellList.get(i).x_Cell_posion]=null;

            }
            else if (tree.CellList.get(i).isGenomeWasDone()==false){
                tree.CellList.get(i).setCellFallTrue();
            }
        }
        tree.alive=false;


    }

    static void checkSquare(int y, int x) {
        int newY = y + 1;
        if (field[y][x] == null) {

        }
        //опускаем вниз падающие семена
        else if (field[y][x].isCellFall() == true) {
            //Если возле земли создать
            //Если коснулось препятсвия уничтожить
            //больше не падает
            //Создать новое дерево
            //Геном потом рандомизировтаь

            if (newY < field.length && field[newY][x] == null) {
                //field[newY][x]=field[y][x].ParentTree.CellList.get(field[y][x].CellID);
                field[newY][x] = field[y][x];
                field[y][x] = null;
                field[newY][x].y_Cell_posionPlus();
            } else if (newY < field.length && field[newY][x] != null) {
                field[y][x] = null;

            } else {
                CreateNewTree(field[y][x]);

            }
        }
        else if (field[y][x].CellBirthday < Turn && field[y][x].isCellFall() == false) {
                //Если появиась не в этом ходу, выполнить геном
                doGenome(y, x);
            }

        }


    private static void CreateNewTree(Cell cell) {
        TreeList.add(new Tree(cell));
        //Добавить последнее созданное дерево родительским к клетке которая пустила росток
        //Это нужно когд будет очищаться поле, что убрать все ее отростки
        cell.ParentTree=TreeList.get(TreeList.size()-1);
    }

    private static void doGenome(int y, int x) {

        DoTest("GrowthLeft");
        //Поочередно выполняем 4 числа генома
        //Передаем координаты клетки и ряд с ее геномом
        growth_Left(y,x, field[y][x].ParentTree.TreeGenome[field[y][x].getGenomeValue()][0]);
        growth_Up(y,x, field[y][x].ParentTree.TreeGenome[field[y][x].getGenomeValue()][1]);
        growth_Right(y,x, field[y][x].ParentTree.TreeGenome[field[y][x].getGenomeValue()][2]);
        growth_Down(y,x, field[y][x].ParentTree.TreeGenome[field[y][x].getGenomeValue()][3]);

    }


    //+
    private static void growth_Left(int y, int x, int g) {
        if (g<16) {
            if (x - 1 >= 0){
                if ((field[y][x - 1] == null)||(field[y][x-1].isCellFall()==true)){
                    field[y][x].CreateSeed(y, x,y,x-1, g);


                }
            }

        }
    }

    //
    private static void growth_Up(int y, int x, int g) {
        if (g<16) {
            if (y - 1 >= 0){
                if ((field[y-1][x] == null)||(field[y-1][x].isCellFall()==true)){
                    field[y][x].CreateSeed(y, x,y-1,x, g);

                }
            }
        }
    }
    private static void growth_Right(int y, int x, int g) {
        if (g<16) {
            if (x + 1 <field[0].length){
                if ((field[y][x + 1] == null)||(x + 1 <field[0].length &&field[y][x+1].isCellFall()==true)){
                    field[y][x].CreateSeed(y, x,y,x+1, g);

                }
            }
        }
    }
    private static void growth_Down(int y, int x, int g) {
        if (g<16) {
            if (y + 1 <field.length){
                if ((field[y+1][x] == null)||(y + 1 <field.length &&field[y+1][x].isCellFall()==true)){
                    field[y][x].CreateSeed(y, x,y+1,x, g);

                }
            }
        }
    }

    static void DoTest(String s){
        if (DoTest==true){
            System.out.println(s);
        }

    }





}
