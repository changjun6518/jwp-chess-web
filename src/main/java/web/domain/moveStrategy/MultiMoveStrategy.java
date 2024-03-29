package web.domain.moveStrategy;

import web.domain.board.Board;
import web.domain.direction.Direction;
import web.domain.piece.Piece;
import web.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class MultiMoveStrategy implements MoveStrategy {

    private final List<Direction> directions;

    public MultiMoveStrategy(List<Direction> directions) {
        this.directions = directions;
    }

    @Override
    public List<Position> possiblePositions(Position from, Board board) {
        ArrayList<Position> path = new ArrayList<>();

        Piece fromPiece = board.findPieceByPosition(from);

        for (Direction direction : directions) {
            Position nextPosition = from.updatePosition(direction);
            while (board.isValidPosition(nextPosition)) {
                Piece nextPiece = board.findPieceByPosition(nextPosition);

                if (fromPiece.isOtherTeam(nextPiece)) {
                    path.add(nextPosition);
                    break;
                }

                if (nextPiece.isSameTeam(fromPiece)) {
                    break;
                }

                path.add(nextPosition);

                nextPosition = nextPosition.updatePosition(direction);
            }
        }
        return path;
    }
}
