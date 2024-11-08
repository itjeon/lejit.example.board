package lejit.example.board.api.boards;

import lejit.example.board.domain.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
