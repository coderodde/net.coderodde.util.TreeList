package net.coderodde.util;

final class TreeListNode<E> {

    private static final int MINIMUM_DEGREE = 4;
    
    private final Object[] storage;
    private final int moduloMask;
    private int size;
    private int headIndex;
    private int tailIndex;
    
    // TreeListNode expects 'degree' that is a power of 2 in order to be able to
    // compute modulo using only bit masking.
    TreeListNode(int degree) {
        this.storage = new Object[degree];
        this.moduloMask = degree - 1;
    }
    
    boolean isFull() {
        return size == storage.length;
    }
    
    void append(E element) {
        storage[tailIndex] = element;
        tailIndex = (tailIndex + 1) & moduloMask;
        ++size;
    }
    
    void insert(E element, int index) {
        int leftPartLength = index;
        int rightPartLength = size - index;
        
        if (leftPartLength < rightPartLength) {
            insertShiftLeft(element, index);
        } else {
            insertShiftRight(element, index);
        }
        
        ++size;
    }
    
    void insertShiftLeft(E element, int index) {
        if (index == 0) {
            headIndex = (headIndex - 1) & moduloMask;
            storage[headIndex] = element;
        } else {
            for (int i = 0; i != index; ++i) {
                storage[(headIndex - 1 + i) & moduloMask] = 
                        storage[(headIndex + i) & moduloMask];
            }
            
            storage[(headIndex + index - 1) & moduloMask] = element;
            headIndex = (headIndex - 1) & moduloMask;
        }
    }
    
    void insertShiftRight(E element, int index) {
        if (index == size) {
            storage[tailIndex] = element;
        } else {
            for (int i = size; i != index; --i) {
                storage[(headIndex + i) & moduloMask] = 
                        storage[(headIndex + i - 1) & moduloMask]; 
            }
            
            storage[(headIndex + index) & moduloMask] = element;
        }
            
        tailIndex = (tailIndex + 1) & moduloMask;
    }
    
    void remove(int index) {
        int leftPartLength = index;
        int rightPartLength = size - index - 1;
        
        if (leftPartLength < rightPartLength) {
            removeShiftRight(index);
        } else {
            removeShiftLeft(index);
        }
        
        --size;
    }
    
    void removeShiftRight(int index) {
        for (int i = index; index != 0; --index) {
            storage[(headIndex + index) & moduloMask] =
                    storage[(headIndex + index - 1) & moduloMask];
        }
        
        headIndex = (headIndex + 1) & moduloMask;
    }
    
    void removeShiftLeft(int index) {
        for (int i = index + 1; i != size; ++i) {
            storage[(headIndex + i - 1) & moduloMask] =
                    storage[(headIndex + i) & moduloMask];
        }
        
        tailIndex = (tailIndex - 1) & moduloMask;
    }
    
    int size() {
        return size;
    }
    
    E get(int index) {
        return (E) storage[(headIndex + index) & moduloMask];
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        String sep = "";
        
        for (int i = 0; i < storage.length; ++i) {
            sb.append(sep).append(storage[i]);
            sep = ", ";
        }
        
        sb.append("] headIndex=")
          .append(headIndex)
          .append(", tailIndex=")
          .append(tailIndex)
          .append(", size=")
          .append(size);
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        TreeListNode<Integer> node = new TreeListNode<>(8);
        
        for (int i = 0; i < 6; ++i) {
            node.append(i);
        }
        
        System.out.println(node);
        node.insert(10, 2);
        System.out.println(node);
        node.insert(11, 5);
        System.out.println(node);
    }
}
