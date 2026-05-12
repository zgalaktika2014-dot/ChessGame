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

    }

