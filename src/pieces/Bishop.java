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

    public boolean isValidMovement(int col, int row) {
        return Math.abs(col - this.col) == Math.abs(row - this.row);
    }

    public boolean moveCollidesWithPiece(int col, int row) {

        if (Math.abs(col - this.col) != Math.abs(row - this.row)) {
            return true;  // ход невозможен
        }

        // Вверх влево
        if (this.col > col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col - i, this.row - i) != null) {
                    return true;  // препятствие
                }
            }
        }

        // Вверх вправо
        if (this.col < col && this.row > row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col + i, this.row - i) != null) {
                    return true;
                }
            }
        }

        // Вниз влево
        if (this.col > col && this.row < row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col - i, this.row + i) != null) {
                    return true;
                }
            }
        }

        // Вниз вправо
        if (this.col < col && this.row < row) {
            for (int i = 1; i < Math.abs(this.col - col); i++) {
                if (board.getPiece(this.col + i, this.row + i) != null) {
                    return true;
                }
            }
        }

        return false;  // путь свободен
    }
}
