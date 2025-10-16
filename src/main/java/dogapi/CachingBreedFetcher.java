package dogapi;

import java.util.*;

/**
 * This BreedFetcher caches fetch request results to improve performance and
 * lessen the load on the underlying data source. An implementation of BreedFetcher
 * must be provided. The number of calls to the underlying fetcher are recorded.
 *
 * If a call to getSubBreeds produces a BreedNotFoundException, then it is NOT cached
 * in this implementation. The provided tests check for this behaviour.
 *
 * The cache maps the name of a breed to its list of sub breed names.
 */
public class CachingBreedFetcher implements BreedFetcher {
    private final Map<String, List<String>> cache = new HashMap<>();
    private int callsMade = 0;

    @Override
    public List<String> getSubBreeds(String breed) {
        // return statement included so that the starter code can compile and run.

        List<String> cached = cache.get(breed);
        if (cached != null) {
            return new ArrayList<>(cached);
        }

        callsMade = callsMade + 1;
        List<String> subBreeds = new DogApiBreedFetcher().getSubBreeds(breed);
        cache.put(breed, new ArrayList<>());
        return new ArrayList<>(subBreeds);
    }

    public int getCallsMade() {
        return callsMade;
    }
}