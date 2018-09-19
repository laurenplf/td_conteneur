package heap;

public class SortedHeap implements Heap<Integer>{

    protected Integer queue[];
    protected int size;
    protected int capa;

    public SortedHeap(int capacity){
        size = 0;
        capa = capacity;
        queue = new Integer[capacity];
    }

    protected int[] parents(int index){
        int loga = (int)Math.log(index);
        int longueur = 2^loga;
        int parents_list[] = new int[longueur];
        for (int i = longueur ; i < 2*longueur ; i++){
            parents_list[i-longueur] = i;
        }
        return parents_list;
    }

    protected void sortLast(Integer e){
        if (size-1 != 0){

        }
    }
    @Override
    public boolean insertElement(Integer e) {
        queue[size] = e;
        size++;
        if (size-1 == 0){
            return true;
        }
        else{
            this.sortLast(size);
        }
    }

    @Override
    public int size(){
        return this.size;
    }
}
