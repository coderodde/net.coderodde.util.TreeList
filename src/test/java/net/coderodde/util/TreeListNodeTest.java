package net.coderodde.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeListNodeTest {
    
    private static Random random;
    
    @BeforeClass
    public static void setUpClass() {
        long seed = System.nanoTime();
        random = new Random(seed);
        
        System.out.println("Seed = " + seed);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isFull method, of class TreeListNode.
     */
    @Test
    public void testIsFull() {
        
    }

    /**
     * Test of append method, of class TreeListNode.
     */
    @Test
    public void testAppend() {
        
    }

    /**
     * Test of insert method, of class TreeListNode.
     */
    @Test
    public void testInsert() {
        List<Integer> list = new ArrayList<>();
        TreeListNode<Integer> node = new TreeListNode<>(8);
        
        for (int i = 0; i < 2_000; ++i) {
            if (list.isEmpty()) {
                Integer num = random.nextInt();
                list.add(num);
                node.append(num);
                assertTrue(eq(list, node));
                continue;
            } 
            
            if (node.size() == 8) {
                int index = random.nextInt(node.size());
                node.remove(index);
                list.remove(index);
                assertTrue(eq(list, node));
                continue;
            }
            
            if (random.nextFloat() < 0.45f) {
                int index = random.nextInt(node.size() + 1);
                int num = random.nextInt();
                node.insert(num, index);
                list.add(index, num);
                assertTrue(eq(list, node));
            } else {
                int index = random.nextInt(node.size());
                node.remove(index);
                list.remove(index);
                assertTrue(eq(list, node));
            }
        }
    }
    
    private boolean eq(List<Integer> list, TreeListNode<Integer> node) {
        if (list.size() != node.size()) {
            return false;
        }
        
        for (int i = 0; i != list.size(); ++i) {
            if (!list.get(i).equals(node.get(i))) {
                return false;
            }
        }
        
        return true;
    }

    /**
     * Test of insertShiftLeft method, of class TreeListNode.
     */
    @Test
    public void testInsertShiftLeft() {
        
    }

    /**
     * Test of insertShiftRight method, of class TreeListNode.
     */
    @Test
    public void testInsertShiftRight() {
        
    }

    /**
     * Test of toString method, of class TreeListNode.
     */
    @Test
    public void testToString() {
        
    }

    /**
     * Test of main method, of class TreeListNode.
     */
    @Test
    public void testMain() {
        
    }
}
