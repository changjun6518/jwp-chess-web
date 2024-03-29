package web.domain.board;

import web.domain.PositionRepository;
import web.domain.piece.*;
import web.domain.position.Position;

import java.util.HashMap;

public class BoardFactory {
    public static final int BOARD_START = 1;
    public static final int BOARD_END = 8;
    private static final int NONE_START = 3;
    private static final int NONE_END = 6;

    private final PositionRepository positionRepository;

    public BoardFactory(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public HashMap<Position, Piece> createBoard() {
        HashMap<Position, Piece> board = new HashMap<>();
        board.putAll(insertWhitePiece());
        board.putAll(insertNonePiece());
        board.putAll(insertBlackPiece());

        return board;
    }

    private HashMap<Position, Piece> insertWhitePiece() {
        HashMap<Position, Piece> whitePieces = new HashMap<>();

        whitePieces.put(positionRepository.save(Position.of(1,1)), Piece.of(PieceType.ROOK, Team.WHITE,'r'));
        whitePieces.put(positionRepository.save(Position.of(2,1)), Piece.of(PieceType.KNIGHT, Team.WHITE,'n'));
        whitePieces.put(positionRepository.save(Position.of(3,1)), Piece.of(PieceType.BISHOP, Team.WHITE,'b'));
        whitePieces.put(positionRepository.save(Position.of(4,1)), Piece.of(PieceType.QUEEN, Team.WHITE,'q'));
        whitePieces.put(positionRepository.save(Position.of(5,1)), Piece.of(PieceType.KING, Team.WHITE,'k'));
        whitePieces.put(positionRepository.save(Position.of(6,1)), Piece.of(PieceType.BISHOP, Team.WHITE,'b'));
        whitePieces.put(positionRepository.save(Position.of(7,1)), Piece.of(PieceType.KNIGHT, Team.WHITE,'n'));
        whitePieces.put(positionRepository.save(Position.of(8,1)), Piece.of(PieceType.ROOK, Team.WHITE,'r'));

        for (int i = BOARD_START; i <= BOARD_END; i++) {
            whitePieces.put(positionRepository.save(Position.of(i,2)),
                    Piece.of(PieceType.WHITE_PAWN, Team.WHITE, 'p'));
        }

        return whitePieces;
    }

    private HashMap<Position, Piece> insertBlackPiece() {
        HashMap<Position, Piece> blackPieces = new HashMap<>();
        for (int i = BOARD_START; i <= BOARD_END; i++) {
            blackPieces.put(positionRepository.save(Position.of(i,7)),
                    Piece.of(PieceType.BLACK_PAWN, Team.BLACK, 'P'));
        }

        blackPieces.put(positionRepository.save(Position.of(1,8)), Piece.of(PieceType.ROOK, Team.BLACK, 'R'));
        blackPieces.put(positionRepository.save(Position.of(2,8)), Piece.of(PieceType.KNIGHT, Team.BLACK, 'N'));
        blackPieces.put(positionRepository.save(Position.of(3,8)), Piece.of(PieceType.BISHOP, Team.BLACK, 'B'));
        blackPieces.put(positionRepository.save(Position.of(4,8)), Piece.of(PieceType.QUEEN, Team.BLACK, 'Q'));
        blackPieces.put(positionRepository.save(Position.of(5,8)), Piece.of(PieceType.KING, Team.BLACK, 'K'));
        blackPieces.put(positionRepository.save(Position.of(6,8)), Piece.of(PieceType.BISHOP, Team.BLACK, 'B'));
        blackPieces.put(positionRepository.save(Position.of(7,8)), Piece.of(PieceType.KNIGHT, Team.BLACK, 'N'));
        blackPieces.put(positionRepository.save(Position.of(8,8)), Piece.of(PieceType.ROOK, Team.BLACK, 'R'));

        return blackPieces;
    }

    private HashMap<Position, Piece> insertNonePiece() {
        HashMap<Position, Piece> nonePieces = new HashMap<>();
        for (int i = NONE_START; i <= NONE_END; i++) {
            for (int j = BOARD_START; j <= BOARD_END; j++) {
                nonePieces.put(positionRepository.save(Position.of(j, i)), Piece.of(PieceType.NONE, Team.NONE,'.'));
            }
        }

        return nonePieces;
    }

    public static Board createTestBoard() {
        HashMap<Position, Piece> testBoard = new HashMap<>();

        for (int i = BOARD_START; i <= BOARD_END; i++) {
            for (int j = BOARD_START; j < BOARD_END; j++) {
                testBoard.put(Position.of(j, i), Piece.of(PieceType.NONE, Team.NONE,'.'));
            }
        }

        return new Board(testBoard);
    }

}
