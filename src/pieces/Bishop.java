package pieces;

import main.Board;

public class Bishop extends Piece {
    public Bishop(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "Bishop";
        if (this.sheet != null) {
            int yOffset = isWhite ? 0 : this.sheetScale;
            this.sprite = this.sheet.getSubimage(2 * this.sheetScale,
                    yOffset,
                    this.sheetScale,
                    this.sheetScale).getScaledInstance(board.tileSize, board.tileSize, 4);
        }

    }
}