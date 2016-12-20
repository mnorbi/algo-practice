import java.util.*;

public class LFUCache {
    private final int capacity;
    private Map<Integer,Integer> map = new HashMap<>();
    private FreqTable freqTable = new FreqTableImpl();
    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)){
            freqTable.increaseFreq(key);
            return map.get(key);
        }
        return -1;
    }

    public void set(int key, int value) {
        if (capacity == 0) return;
        if (map.containsKey(key)){
            freqTable.increaseFreq(key);
            map.put(key, value);
        } else {
            if (capacity <= map.size()){
                map.remove(
                        freqTable.removeLfu());
            }
            map.put(key, value);
            freqTable.add(key);
        }
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.set(1, 1);
        lfuCache.set(2, 2);
        lfuCache.get(1);
        lfuCache.set(3, 3);
        lfuCache.get(2);
        lfuCache.get(3);
        lfuCache.set(4, 4);
        lfuCache.get(1);
        lfuCache.get(3);
        lfuCache.get(4);
    }
}
interface FreqTable{
    void increaseFreq(int key);
    int removeLfu();
    void add(int key);
}
class FreqTableImpl implements FreqTable{
    Map<Integer, MyNode<Bucket>> bucketInd = new HashMap<>();
    MyNode<Bucket> head;
    FreqTableImpl(){
        head = new MyNode<>(new Bucket(0));
        head.l = head;
        head.r = head;
    }
    public void increaseFreq(int key){
        MyNode<Bucket> node = bucketInd.get(key);
        if (node == null) return;
        int freq = (node.val.freq)+1;
        node.val.keys.remove(key);
        if (node.val.keys.size() == 0){
            bucketInd.remove(node);
            MyNode<Bucket> l = node.l;
            MyNode<Bucket> r = node.r;
            l.r = r;
            r.l = l;
            node = l;
        }
        if (node.r.val.freq != freq){
            MyNode<Bucket> next = new MyNode<>(new Bucket(freq));
            next.val.keys.add(key);
            MyNode<Bucket> tmp = node.r;
            node.r = next;
            next.l = node;
            next.r = tmp;
            tmp.l = next;
            bucketInd.put(key, next);
        } else {
            node.r.val.keys.add(key);
            bucketInd.put(key,node.r);
        }
    }
    public void add(int key){
        if (head.r.val.freq != 1){
            MyNode<Bucket> tmp = head.r;
            MyNode<Bucket> next = new MyNode<>(new Bucket(1));
            next.val.keys.add(key);
            head.r = next;
            next.l = head;
            next.r = tmp;
            tmp.l = next;
            bucketInd.put(key, next);
        } else{
            head.r.val.keys.add(key);
            bucketInd.put(key, head.r);
        }
    }
    public int removeLfu(){
        if (head.r != head){
            MyNode<Bucket> node = head.r;
            if (!node.val.keys.isEmpty()){
                int key = node.val.keys.iterator().next();
                bucketInd.remove(key);
                node.val.keys.remove(key);
                if (node.val.keys.size() == 0) {
                    MyNode<Bucket> l = node.l;
                    MyNode<Bucket> r = node.r;
                    l.r = r;
                    r.l = l;
                }
                return key;
            }
        }
        return -1;
    }
}
class MyNode<T>{
    T val;
    MyNode<T> l, r;
    MyNode(T val){
        this.val = val;
    }
}
class Bucket{
    int freq;
    Set<Integer> keys = new LinkedHashSet<>();
    Bucket(int freq){
        this.freq = freq;
    }
}
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.set(key,value);
 */
