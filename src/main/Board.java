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

    public Piece selectedPiece;

    Input input = new Input(this);

    public int enPassantTitle = -1;

    public Board() {
        this.setPreferredSize(new Dimension(this.cols * this.tileSize, this.rows * this.tileSize));

        this.addMouseListener(input);
        this.addMouseMotionListener(input);

        addPieces();
    }

    //используем в инпуте при нажатии
    public Piece getPiece(int col, int row){
        for(Piece piece:pieceList){
            if(piece.col == col && piece.row == row){
                return piece;
            }
        }


        return null;
    }

    public void makeMove(Move move){
        if(move.piece.name.equals("Pawn")){
            movePawn(move);
        }else {


            move.piece.col = move.newCol;
            move.piece.row = move.newRow;
            move.piece.xPos = move.newCol * tileSize;
            move.piece.yPos = move.newRow * tileSize;

            move.piece.isFirstMove = false;

            capture(move.capture);
        }

    }

    private void movePawn(Move move) {

        //Взятие на проходе

        int colorIndex = move.piece.isWhite ? 1 : -1;

        if(getTileSize(move.newCol, move.newRow) == enPassantTitle){
            move.capture = getPiece(move.newCol, move.newRow + colorIndex);
        }
        if(Math.abs(move.piece.row - move.newRow) == 2){
            enPassantTitle = getTileSize(move.newCol,move.newRow + colorIndex);
        }else{
            enPassantTitle = -1;
        }

        //Улучшение
        colorIndex = move.piece.isWhite ? 0 : 7;
        if (move.newRow == colorIndex){
            promotePawn(move);
        }

        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xPos = move.newCol * tileSize;
        move.piece.yPos = move.newRow * tileSize;

        move.piece.isFirstMove = false;

        capture(move.capture);
    }

    private void promotePawn(Move move) {
        pieceList.add(new Queen(this, move.newCol, move.newRow, move.piece.isWhite));
        capture(move.piece);
    }

    public void capture(Piece piece){
        pieceList.remove(piece);
    }

    //корректность хода
    public boolean isValidMove(Move move){
        if (sameTeam(move.piece, move.capture)){
            return false;
        }
        if (!move.piece.isValidMovement(move.newCol, move.newRow)){
            return false;
        }
        if (move.piece.moveCollidesWithPiece(move.newCol, move.newRow)){
            return false;
        }

        return true;
    }

    //проверка не бъём ли мы своих
    public boolean sameTeam(Piece p1, Piece p2){
        if(p1 == null || p2 == null){
            return false;
        }
        return p1.isWhite == p2.isWhite;
    }


    public int getTileSize(int col, int row){
        return row * rows * col;
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

        //цвета доски
        for(int r = 0; r < this.rows; ++r)
            for(int c = 0; c < this.cols; ++c) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(228, 213, 165) : new Color(179, 129, 43));
                g2d.fillRect(c * this.tileSize, r * this.tileSize, this.tileSize, this.tileSize);
            }


        //цвета выделения возможных ходов
        if(selectedPiece != null)
        for(int r = 0; r < this.rows; ++r)
            for(int c = 0; c < this.cols; ++c) {

                if (isValidMove(new Move(this, selectedPiece, c, r))) {
                    g2d.setColor(new Color(0, 255, 0, 100));
                    g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                }
            }

        //цвет фигуры
        for(Piece piece : this.pieceList) {
            piece.paint(g2d);
        }

    }
}