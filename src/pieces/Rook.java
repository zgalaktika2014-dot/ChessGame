package pieces;
import main.Board;

public class Rook extends Piece{
    public Rook(Board board,int col, int row, boolean isWhite){
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.tileSize;
        this.yPos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "Rook";
        if (this.sheet != null) {
            int yOffset = isWhite ? 0 : this.sheetScale;
            this.sprite = this.sheet.getSubimage(4 * this.sheetScale,
                    yOffset,
                    this.sheetScale,
                    this.sheetScale).getScaledInstance(board.tileSize, board.tileSize, 4);
        }
    }
    public boolean isValidMovement(int col, int row){
        return (this.col == col || this.row == row);
    }
    public boolean moveCollidesWithPiece(int col, int row) {

        // Движение по горизонтали
        if (this.row == row) {

            // ВЛЕВО
            if (this.col > col) {
                for (int c = this.col - 1; c > col; c--) {
                    if (board.getPiece(c, this.row) != null) {
                        return true;
                    }
                }
            }

            // ВПРАВО
            else if (this.col < col) {
                for (int c = this.col + 1; c < col; c++) {
                    if (board.getPiece(c, this.row) != null) {
                        return true;
                    }
                }
            }
        }

        // Движение по вертикали
        else if (this.col == col) {

            // ВВЕРХ
            if (this.row > row) {
                for (int r = this.row - 1; r > row; r--) {
                    if (board.getPiece(this.col, r) != null) {
                        return true;
                    }
                }
            }

            // ВНИЗ
            else if (this.row < row) {
                for (int r = this.row + 1; r < row; r++) {
                    if (board.getPiece(this.col, r) != null) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
