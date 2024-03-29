package web.domain.position;

import web.domain.direction.Direction;
import lombok.NoArgsConstructor;
import web.domain.piece.Piece;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "piece_id")
    private Piece piece;

    private static final int BOARD_START_INDEX = 0;
    private static final int BOARD_END_INDEX = 8;
    private static final int FILE_ASCII = 96;
    private static final int RANK_ASCII = 48;

    private int file;
    private int rank;

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    public Position(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(int file, int rank) {
        return new Position(file, rank);
    }

    public static Position of(String position) {
        int nextFile = position.charAt(0) - FILE_ASCII;
        int nextRank = position.charAt(1) - RANK_ASCII;
        return new Position(nextFile, nextRank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Position position = (Position) o;
        return file == position.file && rank == position.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    public Position updatePosition(Direction direction) {
        return new Position(file + direction.getCol(), rank + direction.getRow());
    }


    public static boolean checkValidatePosition(String position) {
        int file = position.charAt(0) - FILE_ASCII;
        int rank = position.charAt(1) - RANK_ASCII;

        return file >= BOARD_START_INDEX && file <= BOARD_END_INDEX &&
                rank >= BOARD_START_INDEX && rank <= BOARD_END_INDEX;
    }

    public String parseString() {
        Character a = (char) (file + FILE_ASCII);
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(rank);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
