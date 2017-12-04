package codesquad.repository;

import codesquad.model.Deck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ROLE_USER')")
public interface DeckRepository extends CrudRepository<Deck, Long>{
}
