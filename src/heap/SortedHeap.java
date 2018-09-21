package heap;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class SortedHeap implements Heap<Integer>, Comparator {

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

    /** Exchange the two elements whose indices are given as arguments
     *
     * @param index1
     * @param index2
     */
    protected void exchange(int index1, int index2){
        Integer temp = queue[index1];
        queue[index1] = queue[index2];
        queue[index2] = temp;
    }

    /** Put the considered element at the right location in the heap by forcing to go up while possible
     *
     * @param index
     */
    protected void sortElementUp(int index){
        int parentIndex = father(index);
        if (queue[index] > queue[parentIndex]){
            exchange(index, parentIndex);
            sortElementUp(parentIndex);
        }
    }

    /** Insert the considered element at the right location in the heap and update its size
     *
     * @param e
     * @return true if insertion was realized
     */
    @Override
    public boolean insertElement(Integer e) {
        queue[size] = e;
        size++;
        if (size > 1){
            this.sortElementUp(size-1);
        }
        return true;
    }

    /** Allows user to get the value of the element of the heap with the highest priority
     *
     * @return first element of the heap
     * @throws NoSuchElementException
     */
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

    /** Find the value of the index of the son with the highest priority (in case of existence)
     *
     * @param index
     * @return index of highest priority son or index given as argument
     */
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

    /** Put the considered element at the right location in the heap by forcing to go down while possible
     *
     * @param index
     */
    protected void sortElementDown(int index){
        int firstSon = biggestSonIndex(index);
        if (queue[firstSon] > queue[index]) {
            exchange(firstSon, index);
            sortElementDown(firstSon);
        }
    }

    /** Remove the first (highest priority) element in the heap, update the heap and its size
     *
     * @return first element of the heap
     * @throws NoSuchElementException
     */
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

    /** Allows user to know if the heap is empty or not
     *
     * @return true if the heap is empty
     */
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Allows user to know the size of the heap
     *
     * @return size of the heap
     */
    @Override
    public int size(){
        return this.size;
    }
}
