package pieces;

import main.Board;

public class Pawn extends Piece {
    public Pawn(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "Pawn";
        if (this.sheet != null) {
            int yOffset = isWhite ? 0 : this.sheetScale;
            this.sprite = this.sheet.getSubimage(5 * this.sheetScale,
                    yOffset,
                    this.sheetScale,
                    this.sheetScale).getScaledInstance(board.tileSize, board.tileSize, 4);
        }

    }
    public boolean isValidMovement(int col, int row){
        int colorIndex = isWhite ? 1 : -1;

        //первый шаг)
        if(this.col == col && row == this.row - colorIndex && board.getPiece(col,row) == null)
            return true;

        //второй шаг
        if(isFirstMove &&  this.col == col && row == this.row - colorIndex * 2 && board.getPiece(col,row) == null && board.getPiece(col,row + colorIndex) == null)
            return true;

        //взятие с лева
        if (col == this.col -1 && row == this.row - colorIndex && board.getPiece(col,row) != null)
            return true;

        //взятие с права
        if (col == this.col +1 && row == this.row - colorIndex && board.getPiece(col,row) != null)
            return true;
        return false;
    }
}
