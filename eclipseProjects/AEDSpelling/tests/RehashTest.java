import aed.dataStructures.HashTable;
import aed.dataStructures.Iterator;
import aed.dataStructures.OpenHashTable;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Created by Andre on 25/11/2014.
 */
public class RehashTest {

    HashTable<Integer> hashTable;
    LinkedList<Integer> linkedList;

    @Before
    public void init(){
        hashTable = new OpenHashTable<Integer>(1);
        linkedList = new LinkedList<Integer>();
        populate(5000);
    }

    public void populate(int n){
        Random seed = new Random();

        for(int i = 0; i < n; i++) {
            int toAdd = seed.nextInt(5000);
            hashTable.insert(toAdd);
            linkedList.add(toAdd);
        }
    }

    @Test
    public void size(){
        assertEquals(hashTable.size(), linkedList.size());
    }

    @Test
    public void containsElems(){
        for(Integer i : linkedList)
            assert hashTable.contains(i);
    }

    @Test
    public void hasNoExtraElems(){
        Iterator<Integer> iterator = hashTable.iterator();

        while(iterator.hasNext())
            assert linkedList.contains(iterator.next());
    }
}
