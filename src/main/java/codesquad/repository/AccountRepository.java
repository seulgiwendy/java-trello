package codesquad.repository;

import codesquad.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long>{

    public Optional<Account> findByUserId(@Param("userid") String userId);

}
