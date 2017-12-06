package codesquad.utils;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class ListUtils<T> {

    public Optional<T> update(List<T> originalList, T object) {
        for (int i = 0; i < originalList.size(); i++) {
            T item = originalList.get(i);
            if (item.equals(object)) return Optional.of(originalList.set(i, object));
        }
        return Optional.empty();
    }
}
