import javax.swing.ImageIcon;
import java.util.ArrayList;

public class King extends ChessGamePiece {
    public King(ChessGameBoard board, int row, int col, int color) {
        super(board, row, col, color, false);
    }

    @Override
    protected ArrayList<String> calculatePossibleMoves(ChessGameBoard board) {
        ArrayList<String> possibleMoves = new ArrayList<String>();
        int[] directions = {-1, 0, 1};

        for (int dx : directions) {
            for (int dy : directions) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int newRow = getRow() + dx;
                int newCol = getCol() + dy;
                if (isValidMove(newRow, newCol)) {
                    possibleMoves.add(getPositionString(newRow, newCol));
                }
            }
        }
        return possibleMoves;
    }

    @Override
    public boolean isChecked(ChessGameBoard board) {
        return getCurrentAttackers(board).size() > 0;
    }

    @Override
    public ImageIcon createImageByPieceType() {
        String filename;
        if (getColorOfPiece() == ChessGamePiece.WHITE) {
            filename = "WhiteKing.gif";
        } else if (getColorOfPiece() == ChessGamePiece.BLACK) {
            filename = "BlackKing.gif";
        } else {
            filename = "default-Unassigned.gif";
        }
        return new ImageIcon(getClass().getResource("chessImages/" + filename));
    }
    
    private boolean isValidMove(int newRow, int newCol) {
        return getBoard().isOnBoard(newRow, newCol)
            && (!getBoard().hasPiece(newRow, newCol)
            || getBoard().getPiece(newRow, newCol).getColorOfPiece() != getColorOfPiece());
    }
    
    private String getPositionString(int row, int col) {
        return (char) ('a' + col) + "" + (row + 1);
    }
}