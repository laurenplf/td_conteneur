package heap;

import java.util.NoSuchElementException;

public class SortedHeap implements Heap<Integer>{

    protected Integer queue[];
    protected int size;
    protected int capa;

    public SortedHeap(int capacity){
        size = 0;
        capa = capacity;
        queue = new Integer[capacity];
    }

    /** Find the position of the parent of the element on position "index"
     *
     * @return Index of the parent
     */
    protected int parent(int index){
        int loga = (int)Math.log(index+1)-1;
        int parentPosition = 2^loga;
        parentPosition = parentPosition + (index-parentPosition)/2 - 1;
        return parentPosition;
    }


    protected void echange(int index1, int index2){
        Integer temp = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = temp;
    }


    protected void sortElement(int position){
        int parentPosition = parent(position);
        if (queue[position] > queue[parentPosition]){
            echange(position, parentPosition);
            sortElement(parentPosition);
        }
    }


    @Override
    public boolean insertElement(Integer e) {
        queue[size] = e;
        size++;
        boolean result = true;
        if (size > 1){
            this.sortElement(size-1);
            result = true;
        }
        return result;
    }


    @Override
    public int size(){
        return this.size;
    }
}
