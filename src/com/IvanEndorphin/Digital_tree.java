package com.IvanEndorphin;

import java.util.ArrayList;

public class Digital_tree {

    public static Cell[][] field;
    public static Rect[][] FieldRect = new Rect[10][30];

    public static ArrayList <Tree> TreeList = new ArrayList<Tree>();
    public static int Turn = 0;
    public static boolean wasSomeGrowth=false;
    public static int MaxTreeAge = 10;
    public static boolean DoTest = false;
    public static int TurnMaximum = 20;
    public static PrimitivesAppMy app = new PrimitivesAppMy();





    public static void main(String[] args) throws InterruptedException {


        app.start();
        //Заполняем массив поля прямоугольниками
        app.makeField();


        //Начальная позиция первой семечки
        DoTest("Im here 5 - before new tree");

        //Лист со всеми будущими деревьми
        TreeList.add( new Tree());
        TreeList.get(0).addFirsCell(9,15);

        app.repaint();

        //Вывод генома в консоль
        TreeList.get(0).soutGenomeVertical();



        while (true){

            growth();

            Turn++;

            soutAnotation();

            wasSomeGrowth=false;
            app.repaint();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        }



    private static void soutAnotation() {
        System.out.println("Turn: "+Turn);

        System.out.println();

        soutTreeList();

    }

    private static void soutTreeList() {

        for (int i = 0; i <TreeList.size(); i++) {

            if (TreeList.get(i).alive==true){
                System.out.println("Tree №"+i+"   Age:"+TreeList.get(i).age+"   CellCount:"+TreeList.get(i).CellList.size());

            }

        }
        System.out.println();
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







        }


        static void growth(){

                //Проходимся по по полю слева-направо,снизу-вверх

                for (int j = FieldRect.length-1; j >=0; j--) {
                    for (int i = 0; i < FieldRect[0].length ; i++) {

                        checkSquare(j,i);
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
                FieldRect[tree.CellList.get(i).getY_Cell_posion()][tree.CellList.get(i).x_Cell_posion].setCellNull();
                FieldRect[tree.CellList.get(i).getY_Cell_posion()][tree.CellList.get(i).x_Cell_posion].changeColorGrey();

            }
            else if (tree.CellList.get(i).isGenomeWasDone()==false){
                tree.CellList.get(i).setCellFallTrue();
                FieldRect[tree.CellList.get(i).getY_Cell_posion()][tree.CellList.get(i).x_Cell_posion].changeColorBlue();
            }
        }
        tree.alive=false;


    }

    static void checkSquare(int y, int x) {
        DoTest("CheckSquare_Y"+y+"_Х_"+x);


        int newY = y + 1;

        //Если в ячейке поля нет клетки пропускаем
        if (FieldRect[y][x].isAnyCell() == false) {
            DoTest("isAnyCell = false");


        }

        //опускаем вниз если семечка падающее
        else if (FieldRect[y][x].RectCell.isCellFall() == true) {

            DoTest("isCellFall = true");

            //Если снизу падающей семечки ничего нет опускаем ее

            if (newY < FieldRect.length && FieldRect[newY][x].isAnyCell()==false) {
                //field[newY][x]=field[y][x].ParentTree.CellList.get(field[y][x].CellID);
                FieldRect[newY][x].RectCell = FieldRect[y][x].RectCell;
                FieldRect[newY][x].changeColorBlue();
                FieldRect[y][x].KilCell();

                FieldRect[newY][x].RectCell.y_Cell_posionPlus();

            }
            //По скольку проход делаем слева-напрво, снизу-вверх.
            //То нижняя семечка должна была сдвинуться вниз
            //Соответсвенно она либо коснулась земли либо там дерево
            //По этому в таких случаях уничтожаем семечку
            else if (newY < FieldRect.length && FieldRect[newY][x].isAnyCell()==true) {
                FieldRect[y][x].KilCell();

            }
            //Остается только случай когда мемечка уже на полу
            //По этому создаем дерево
            else {
                CreateNewTree(FieldRect[y][x].RectCell);

            }
        }

        //Если семечка создана не в этом ходу, то выполняем геном, то есть рост.
        else if (FieldRect[y][x].RectCell.CellBirthday < Turn && FieldRect[y][x].RectCell.isCellFall() == false) {
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
        DoTest("Do Genome");


        //Поочередно выполняем 4 числа генома
        //Передаем координаты клетки и ряд с ее геномом
        growth_Left(y,x, FieldRect[y][x].RectCell.ParentTree.TreeGenome[FieldRect[y][x].RectCell.getGenomeValue()][0]);
        growth_Up(y,x, FieldRect[y][x].RectCell.ParentTree.TreeGenome[FieldRect[y][x].RectCell.getGenomeValue()][1]);
        growth_Right(y,x, FieldRect[y][x].RectCell.ParentTree.TreeGenome[FieldRect[y][x].RectCell.getGenomeValue()][2]);
        growth_Down(y,x, FieldRect[y][x].RectCell.ParentTree.TreeGenome[FieldRect[y][x].RectCell.getGenomeValue()][3]);

    }


    //+
    private static void growth_Left(int y, int x, int g) {
        if (g<16) {
            if (x - 1 >= 0){
                if ((FieldRect[y][x - 1].isAnyCell() == false)||(FieldRect[y][x-1].RectCell.isCellFall()==true)){
                    FieldRect[y][x].RectCell.CreateSeed(y, x,y,x-1, g);
                    DoTest("GrowthLeft");



                }
            }

        }
    }

    //
    private static void growth_Up(int y, int x, int g) {
        if (g<16) {
            if (y - 1 >= 0){
                if ((FieldRect[y-1][x].isAnyCell() == false)||(FieldRect[y-1][x].RectCell.isCellFall()==true)){
                    FieldRect[y][x].RectCell.CreateSeed(y, x,y-1,x, g);
                    DoTest("Growth Up");


                }
            }
        }
    }
    private static void growth_Right(int y, int x, int g) {
        if (g<16) {
            if (x + 1 <FieldRect[0].length){
                if ((FieldRect[y][x + 1].isAnyCell() == false)||(x + 1 <FieldRect[0].length &&FieldRect[y][x+1].RectCell.isCellFall()==true)){
                    FieldRect[y][x].RectCell.CreateSeed(y, x,y,x+1, g);
                    DoTest("Growth Right");


                }
            }
        }
    }
    private static void growth_Down(int y, int x, int g) {
        if (g<16) {
            if (y + 1 <FieldRect.length){
                if ((FieldRect[y+1][x].isAnyCell() == false)||(y + 1 <FieldRect.length &&FieldRect[y+1][x].RectCell.isCellFall()==true)){
                    FieldRect[y][x].RectCell.CreateSeed(y, x,y+1,x, g);
                    DoTest("Growth Down");


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
