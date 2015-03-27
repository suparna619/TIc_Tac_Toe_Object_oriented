package com.TTT;

/**
 * Created by suparnad on 3/27/2015.
 */
public class Cell {
    Seed content;
    private int row;
    private int column;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        clear();
    }

    void clear() {
        content = Seed.EMPTY;
    }

    public void paint(){
        switch (content){
            case CROSS:
                System.out.print(" X ");
                break;
            case NOUGHT:
                System.out.print(" O ");
                break;
            case EMPTY:
                System.out.print(" - ");
                break;
        }
    }
}
