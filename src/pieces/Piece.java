package pieces;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Board;

public class Piece {
    public int col;
    public int row;
    public int xPos;
    public int yPos;
    public boolean isWhite;
    public String name;
    public int value;
    protected BufferedImage sheet;
    protected int sheetScale;
    protected Image sprite;
    protected Board board;

    public boolean isFirstMove = true;

    public Piece(Board board) {
        try {
            this.sheet = ImageIO.read(ClassLoader.getSystemResourceAsStream("res/pieces.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.board = board;
        if (this.sheet != null) {
            this.sheetScale = this.sheet.getWidth() / 6;
        }

    }

    //проверка геометрии
    public boolean isValidMovement(int col, int row){return true;}
    //проверка на наличие преград
    public boolean moveCollidesWithPiece(int col, int row){return false;}

    public void paint(Graphics2D g2d) {
        if (this.sprite != null) {
            g2d.drawImage(this.sprite, this.xPos, this.yPos, (ImageObserver)null);
        }

    }
}
