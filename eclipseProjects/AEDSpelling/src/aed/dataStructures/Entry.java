package aed.dataStructures;

import java.io.Serializable;

/**
 * Created by Andre on 23/11/2014.
 */
public interface Entry<K, V> extends Serializable {
    //TODO comment
    K getKey();
    V getValue();
}
