import java.util.HashMap;
import java.util.Set;

public class BiMap<K, V> extends HashMap<K, V> {


    public BiMap<V, K> inverse()
    {
        Set<K> keys = this.keySet();
        BiMap<V, K> inversed = new BiMap<V, K>();

        for (K key : keys)
            inversed.put(this.get(key), key);

        return inversed;

    }

}
