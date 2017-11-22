package codesquad.repository;

import codesquad.model.Deck;
import org.springframework.data.repository.CrudRepository;

public interface DeckRepository extends CrudRepository<Deck, Long>{
}
