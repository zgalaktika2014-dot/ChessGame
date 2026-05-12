package main;

import pieces.Piece;

public class ChackScanner {
    Board board;

    public ChackScanner(Board board){
        this.board = board;
    }

    public boolean isKingChecked(Move move){
        Piece king = board.findKing(move.piece.isWhite);
        if (king == null) return false;  // заменили assert на нормальную проверку

        int kingCol = king.col;
        int kingRow = king.row;

        // ИСПРАВЛЕНО: используем move.piece, а не board.selectedPiece
        if(move.piece.name.equals("King")){
            kingCol = move.newCol;
            kingRow = move.newRow;
        }

        return  hitByRook  (move.newCol, move.newRow , king, kingCol, kingRow,0,1) ||//u
                hitByRook  (move.newCol, move.newRow , king, kingCol, kingRow,1,0) ||//r
                hitByRook  (move.newCol, move.newRow , king, kingCol, kingRow,0,-1) ||//d
                hitByBishop(move.newCol, move.newRow , king, kingCol, kingRow,-1,0)||//l

                hitByBishop  (move.newCol, move.newRow , king, kingCol, kingRow,-1,-1) ||//u l
                hitByBishop  (move.newCol, move.newRow , king, kingCol, kingRow,1,-1) ||//u r
                hitByBishop  (move.newCol, move.newRow , king, kingCol, kingRow,1,1) ||//d r
                hitByBishop  (move.newCol, move.newRow , king, kingCol, kingRow,-1,1)||//d l

                hitByKnight(move.newCol, move.newRow , king, kingCol, kingRow)||
                hitByPawn(move.newCol, move.newRow , king, kingCol, kingRow)||
                hitByKing(king, kingCol, kingRow,move);


    }
    private boolean hitByBishop(int col, int row, Piece king, int kingCol, int kingRow, int colVal, int rowVal){
        for(int i = 1; i < 8; i++){
            if(kingCol + (i * colVal) == col && kingRow + (i * rowVal) == row){
                break;
            }
            Piece piece = board.getPiece(kingCol + (i * colVal), kingRow + (i * rowVal));
            if(piece != null && piece != board.selectedPiece){
                if(!board.sameTeam(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByRook(int col, int row,Piece king,int kingCol, int kingRow,int colVal, int rowVal){
        for(int i = 1; i < 8; i++){
            if(kingCol + (i * colVal) == col &&  kingRow + (i * rowVal) == row){
                break;
            }
            Piece piece = board.getPiece(kingCol + (i * colVal),kingRow + (i * rowVal));
            if(piece != null && piece != board.selectedPiece){
                if(!board.sameTeam(piece, king) && (piece.name.equals("Rook") || piece.name.equals("Queen"))){
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean hitByKnight(int col, int row, Piece king, int kingCol, int kingRow){

        return checkKnight(board.getPiece(kingCol - 1, kingRow - 2),king, col, row)||
                checkKnight(board.getPiece(kingCol + 1, kingRow - 2),king, col, row)||
                checkKnight(board.getPiece(kingCol + 2, kingRow - 1),king, col, row)||
                checkKnight(board.getPiece(kingCol + 2, kingRow + 1),king, col, row)||
                checkKnight(board.getPiece(kingCol + 1, kingRow + 2),king, col, row)||
                checkKnight(board.getPiece(kingCol - 1, kingRow + 2),king, col, row)||
                checkKnight(board.getPiece(kingCol - 2, kingRow + 1),king, col, row)||
                checkKnight(board.getPiece(kingCol - 2, kingRow - 1),king, col, row);
    }

    private boolean checkKnight(Piece p, Piece k, int col, int row){

        return p != null && !board.sameTeam(p,k) && p.name.equals("Knight") && !(p.col == col && p.row == row);
    }

    private boolean hitByKing(Piece king, int kingCol, int kingRow, Move move){

        return checkKing(board.getPiece(kingCol - 1, kingRow - 1),king)||
                checkKing(board.getPiece(kingCol + 1, kingRow - 1),king)||
                checkKing(board.getPiece(kingCol, kingRow - 1),king)||
                checkKing(board.getPiece(kingCol - 1, kingRow),king)||
                checkKing(board.getPiece(kingCol + 1, kingRow),king)||
                checkKing(board.getPiece(kingCol - 1, kingRow + 1),king)||
                checkKing(board.getPiece(kingCol + 1, kingRow + 1),king)||
                checkKing(board.getPiece(kingCol, kingRow + 1),king);
    }

    private boolean checkEnemyKing(Piece piece, Move move) {
        // Проверяем: есть ли фигура, это король, и он ПРОТИВНИКА
        if (piece == null) return false;
        if (!piece.name.equals("King")) return false;
        // Цвета разные? Значит, вражеский король!
        return piece.isWhite != move.piece.isWhite;
    }

    private boolean checkKing(Piece p, Piece king) {
        return p != null && p.name.equals("King") && p.isWhite != king.isWhite;
    }

    private boolean hitByPawn(int col, int row, Piece king, int kingCol, int kingRow){
        int colorVal = king.isWhite ? -1 : 1;
        return checkPawn(board.getPiece(kingCol + 1, kingRow + colorVal), king, col, row)||
                checkPawn(board.getPiece(kingCol - 1, kingRow + colorVal), king, col, row);
    }

    private boolean checkPawn(Piece p, Piece k, int col, int row){
        return p != null && !board.sameTeam(p, k) && p.name.equals("Pawn");
    }
}
