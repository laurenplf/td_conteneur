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
    protected int father(int index){
        int position = index + 1;
        int loga = (int)Math.log(position);
        int parentPosition = 2^(loga - 1);
        parentPosition = parentPosition + (position-2^loga)/2 - 1;
        return parentPosition;
    }


    protected void exchange(int index1, int index2){
        Integer temp = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = temp;
    }


    protected void sortElementUp(int index){
        int parentIndex = father(index);
        if (queue[index] > queue[parentIndex]){
            exchange(index, parentIndex);
            sortElementUp(parentIndex);
        }
    }


    @Override
    public boolean insertElement(Integer e) {
        queue[size] = e;
        size++;
        if (size > 1){
            this.sortElementUp(size-1);
        }
        return true;
    }


    @Override
    public Integer element()
            throws NoSuchElementException{
        if (size != 0){
            return queue[0];
        }
        else{
            throw new NoSuchElementException();
        }
    }

    protected int biggestSonIndex(int index){
        int position = index + 1;
        int loga = (int)Math.log(position);
        int firstSonIndex = 2^(loga+1) + 2*(position-2^loga) - 1;
        int biggestSon = index;
        if (firstSonIndex < size){ //On vérifie l'existence du premier fils
            if(queue[firstSonIndex] > queue[index]) {
                biggestSon = firstSonIndex;
            }
            if(firstSonIndex + 1 < size){ //Si le premier fils existe, on vérifie l'existence du deuxième
                if(queue[biggestSon] > queue[firstSonIndex + 1]){
                    biggestSon = firstSonIndex + 1;
                }
            }
        }
        return biggestSon;
    }

    protected void sortElementDown(int index){
        int firstSon = biggestSonIndex(index);
        if (queue[firstSon] > queue[index]) {
            exchange(firstSon, index);
            sortElementDown(firstSon);
        }
    }

    @Override
    public Integer popElement()
            throws NoSuchElementException {
        if (size == 0){
            throw new NoSuchElementException();
        }
        else{
            Integer result = queue[0];
            size--;
            queue[0] = queue[size];
            sortElementDown(0);
            return result;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size(){
        return this.size;
    }
}
