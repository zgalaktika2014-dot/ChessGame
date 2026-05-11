package main;

import pices.Knight;
import pices.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Board extends JPanel {

    public int tileSize = 85;

    int cols = 8;
    int rows = 8;

    //список хранящий фигуры
    ArrayList<Piece> pieceList = new ArrayList<>();

    //доска
    public Board(){

        this.setPreferredSize(new Dimension(cols * tileSize,rows*tileSize));
        addPieces();
    }

    public void addPieces(){
        pieceList.add(new Knight(this,1,0,false));
        pieceList.add(new Knight(this,6,0,false));
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;


        for(int r = 0;r < rows;r++) {
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(159, 207, 59) : new Color(228, 213, 165));
                g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
            }
        }
        //рисуем фигуры поверх клеток
        for (Piece piece : pieceList) {
            piece.paint(g2d);
        }

    }

}
