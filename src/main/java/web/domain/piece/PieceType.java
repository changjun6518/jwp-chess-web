package web.domain.piece;

import web.domain.board.Board;
import web.domain.direction.Direction;
import web.domain.moveStrategy.MoveStrategy;
import web.domain.moveStrategy.MultiMoveStrategy;
import web.domain.moveStrategy.PawnMoveStrategy;
import web.domain.moveStrategy.SingleMoveStrategy;
import web.domain.position.Position;

import java.util.List;

public enum PieceType {
    KING(new SingleMoveStrategy(Direction.getKingDirections()), 0),
    QUEEN(new MultiMoveStrategy(Direction.getQueenDirections()), 9),
    ROOK(new MultiMoveStrategy(Direction.getRookDirections()), 5),
    KNIGHT(new SingleMoveStrategy(Direction.getKnightDirections()), 2.5),
    BISHOP(new MultiMoveStrategy(Direction.getBishopDirections()), 3),
    WHITE_PAWN(new PawnMoveStrategy(Direction.getFirstWhitePawnDirections()), 1),
    BLACK_PAWN(new PawnMoveStrategy(Direction.getFirstBlackPawnDirections()), 1),
    NONE(new SingleMoveStrategy(Direction.getNoneDirections()), 0),;

    private final MoveStrategy moveStrategy;
    private double score;

    PieceType(MoveStrategy moveStrategy, double score) {
        this.moveStrategy = moveStrategy;
        this.score = score;
    }

    public List<Position> getPossiblePaths(Position from, Board board) {
        return moveStrategy.possiblePositions(from, board);
    }

    public double getScore() {
        return score;
    }
}
