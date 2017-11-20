package codesquad.repository;

import codesquad.model.Board;
import codesquad.model.User;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long>{

    public Board findByUser(User user);
}
