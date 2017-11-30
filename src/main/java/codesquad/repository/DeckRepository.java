package codesquad.repository;

import codesquad.model.Deck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeckRepository extends CrudRepository<Deck, Long>{
}
