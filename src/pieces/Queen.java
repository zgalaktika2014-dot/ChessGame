package pieces;
import main.Board;

public class Queen extends Piece{
    public Queen(Board board,int col,int row,boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "Queen";
        if (this.sheet != null) {
            int yOffset = isWhite ? 0 : this.sheetScale;
            this.sprite = this.sheet.getSubimage(1 * this.sheetScale,
                    yOffset,
                    this.sheetScale,
                    this.sheetScale).getScaledInstance(board.tileSize, board.tileSize, 4);
        }

    }
    public boolean isValidMovement(int col, int row) {
        // По прямой
        if (this.col == col || this.row == row) {
            return true;
        }
        // По диагонали
        if (Math.abs(col - this.col) == Math.abs(row - this.row)) {
            return true;
        }
        return false;
    }

    public boolean moveCollidesWithPiece(int col, int row) {
        int deltaCol = Math.abs(col - this.col);
        int deltaRow = Math.abs(row - this.row);

        boolean isValidQueenMove = (this.col == col || this.row == row) || (deltaCol == deltaRow);
        if (!isValidQueenMove) {
            return true;
        }

        if (this.col == col || this.row == row) {

            // ВЛЕВО
            if (this.col > col) {
                for (int c = this.col - 1; c > col; c--) {
                    if (board.getPiece(c, this.row) != null) {
                        return true;
                    }
                }
            }

            // ВПРАВО
            if (this.col < col) {
                for (int c = this.col + 1; c < col; c++) {
                    if (board.getPiece(c, this.row) != null) {
                        return true;
                    }
                }
            }

            // ВВЕРХ
            if (this.row > row) {
                for (int r = this.row - 1; r > row; r--) {
                    if (board.getPiece(this.col, r) != null) {
                        return true;
                    }
                }
            }

            // ВНИЗ
            if (this.row < row) {
                for (int r = this.row + 1; r < row; r++) {
                    if (board.getPiece(this.col, r) != null) {
                        return true;
                    }
                }
            }
        }

        if (deltaCol == deltaRow) {

            // Вверх влево
            if (this.col > col && this.row > row) {
                for (int i = 1; i < deltaCol; i++) {
                    if (board.getPiece(this.col - i, this.row - i) != null) {
                        return true;
                    }
                }
            }

            // Вверх вправо
            if (this.col < col && this.row > row) {
                for (int i = 1; i < deltaCol; i++) {
                    if (board.getPiece(this.col + i, this.row - i) != null) {
                        return true;
                    }
                }
            }

            // Вниз влево
            if (this.col > col && this.row < row) {
                for (int i = 1; i < deltaCol; i++) {
                    if (board.getPiece(this.col - i, this.row + i) != null) {
                        return true;
                    }
                }
            }

            // Вниз вправо
            if (this.col < col && this.row < row) {
                for (int i = 1; i < deltaCol; i++) {
                    if (board.getPiece(this.col + i, this.row + i) != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }



    }

