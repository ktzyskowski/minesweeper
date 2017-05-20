import java.util.HashMap;
import java.util.Set;

/**
 * Class to implement a Bi-direction map
 */
public class BiMap<K, V> extends HashMap<K, V> {

    /**
     * Method to return inverse map
     * @return Inverse BiMap
     */
    public BiMap<V, K> inverse()
    {
        Set<K> keys = this.keySet();
        BiMap<V, K> inverse = new BiMap<V, K>();

        for (K key : keys)
            inverse.put(this.get(key), key);

        return inverse;

    }

}
