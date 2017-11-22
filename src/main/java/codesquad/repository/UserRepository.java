package codesquad.repository;

import codesquad.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long>{

    public User findByUserId(@Param("userid") String userId);

}
