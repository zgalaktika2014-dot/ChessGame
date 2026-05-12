package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import pieces.Knight;
import pieces.King;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
import pieces.Bishop;
import pieces.Pawn;

public class Board extends JPanel {
    public int tileSize = 85;
    int cols = 8;
    int rows = 8;
    ArrayList<Piece> pieceList = new ArrayList();

    public Board() {
        this.setPreferredSize(new Dimension(this.cols * this.tileSize, this.rows * this.tileSize));
        this.addPieces();
    }

    public Piece getPiece(int col, int row){
        for(Piece piece:pieceList){
            if(piece.col == col && piece.row == row){
                return piece;
            }
        }


        return null;
    }



    public void addPieces() {
        //White
        this.pieceList.add(new Knight(this, 1, 7, true));
        this.pieceList.add(new Knight(this, 6, 7, true));

        this.pieceList.add(new Rook(this, 0, 7, true));
        this.pieceList.add(new Rook(this, 7, 7, true));

        this.pieceList.add(new Bishop(this, 2, 7, true));
        this.pieceList.add(new Bishop(this, 5, 7, true));

        this.pieceList.add(new King(this,4,7,true));

        this.pieceList.add(new Queen(this,3,7,true));

        this.pieceList.add(new Pawn(this, 0, 6, true));
        this.pieceList.add(new Pawn(this, 1, 6, true));
        this.pieceList.add(new Pawn(this, 2, 6, true));
        this.pieceList.add(new Pawn(this, 3, 6, true));
        this.pieceList.add(new Pawn(this, 4, 6, true));
        this.pieceList.add(new Pawn(this, 5, 6, true));
        this.pieceList.add(new Pawn(this, 6, 6, true));
        this.pieceList.add(new Pawn(this, 7, 6, true));

        //Black
        this.pieceList.add(new Knight(this, 1, 0, false));
        this.pieceList.add(new Knight(this, 6, 0, false));

        this.pieceList.add(new Rook(this, 0, 0, false));
        this.pieceList.add(new Rook(this, 7, 0, false));

        this.pieceList.add(new Bishop(this, 2, 0, false));
        this.pieceList.add(new Bishop(this, 5, 0, false));

        this.pieceList.add(new King(this,4,0,false));

        this.pieceList.add(new Queen(this,3,0,false));

        this.pieceList.add(new Pawn(this, 0, 1, false));
        this.pieceList.add(new Pawn(this, 1, 1, false));
        this.pieceList.add(new Pawn(this, 2, 1, false));
        this.pieceList.add(new Pawn(this, 3, 1, false));
        this.pieceList.add(new Pawn(this, 4, 1, false));
        this.pieceList.add(new Pawn(this, 5, 1, false));
        this.pieceList.add(new Pawn(this, 6, 1, false));
        this.pieceList.add(new Pawn(this, 7, 1, false));


    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

        for(int r = 0; r < this.rows; ++r) {
            for(int c = 0; c < this.cols; ++c) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(228, 213, 165) : new Color(179, 129, 43));
                g2d.fillRect(c * this.tileSize, r * this.tileSize, this.tileSize, this.tileSize);
            }
        }

        for(Piece piece : this.pieceList) {
            piece.paint(g2d);
        }

    }
}