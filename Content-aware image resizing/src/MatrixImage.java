import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MatrixImage implements Image {
    public int[][] field;
    //private int counter = 0;

    public MatrixImage(int sx, int sy) {
        field = new int[sx][sy];
    }

    // copy constructor
    public MatrixImage(MatrixImage that) {
        this(that.sizeX(), that.sizeY());
        for (int x = 0; x < sizeX(); x++) {
            field[x] = that.field[x].clone();
        }
    }

    public MatrixImage(String filename) throws java.io.FileNotFoundException {
        System.setIn(new FileInputStream(filename));
        Scanner in = new Scanner(System.in);
        int sx = in.nextInt();
        int sy = in.nextInt();
        field = new int[sx][sy];
        for (int y = 0; y < sy; y++) {
            for (int x = 0; x < sx; x++) {
                field[x][y] = in.nextInt();
            }
        }
    }

    @Override
    public int sizeX() { return field.length; }

    @Override
    public int sizeY() {
        return field[0].length;
    }

    @Override
    public double contrast(Coordinate p0, Coordinate p1) throws InputMismatchException {
        // TODO

        double difResult;
        if(p0.x>= sizeX() || p1.x>= sizeX() || p0.x < 0 || p1.x < 0 ) {throw new InputMismatchException("You aren't supposed to be in this image!");}
        if(p0.y>= sizeY() || p1.y>= sizeY() || p0.y < 0 || p1.y < 0) {throw new InputMismatchException("You aren't supposed to be in this image!");}
        else{difResult = field[p0.x][p0.y]- field[p1.x][p1.y]; return Math.abs(difResult); }



    }

    @Override
    public void removeVPath(int[] path) {
        // TODO
        int[][] anotherField = new int[sizeX()-1][sizeY()];


        for(int y = 0; y < sizeY();y++) {
            int counter = 0;
            for (int x = 0; x < sizeX() ; x++) {
                if (x == path[y] && field[x][y] == field[path[y]][y]) {
                    int check = (path[y] <= sizeX() - 2) ? 1 : 0;
                    if(check == 1){
                        x++; counter++;
                        anotherField[path[y]][y] = field[x][y];
                    }
                }else {
                    anotherField[x - counter][y] = field[x][y];
                }



            }
        }



        field = anotherField;


    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < sizeY(); y++) {
            for (int x = 0; x < sizeX(); x++) {
                str += field[x][y] + " ";
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public void render() {
        System.out.println(toString());
    }

}

