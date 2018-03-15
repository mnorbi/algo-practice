class LFUCache {
    Map<Integer, Integer> map = new HashMap<>();
    Map<Integer, Integer> freq = new HashMap<>();
    Map<Integer, LinkedHashSet<Integer>> lru = new LinkedHashMap<>();
    int min = 0;
    int free;
    int capacity;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.free = capacity;
    }
    
    public int get(int key) {
        if (map.containsKey(key)){
            updateFreq(key);
            return map.get(key);
        }
        return -1;
    }
    
    private void updateFreq(int key){
        int f = freq.getOrDefault(key, 0);
        if (lru.containsKey(f)){
            lru.get(f).remove(key);
            if (lru.get(f).isEmpty()){
                lru.remove(f);
                if (min == f){
                    ++min;
                }
            }
        }
        lru.computeIfAbsent(f+1, LinkedHashSet::new).add(key);
        freq.merge(key, 1, Integer::sum);
    }
    
    public void put(int key, int value) {
        if (capacity <= 0) return;

        if (!map.containsKey(key)){
            if (free == 0){
                evict();
            } else {
                --free;
            }
            min = 1;
        }
        map.put(key, value);
        updateFreq(key);
    }
    
    private void evict(){
        if (lru.containsKey(min) && lru.get(min).size() > 0){
            Iterator<Integer> it = lru.get(min).iterator();
            Integer key = it.next();
            it.remove();
            if (lru.get(min).isEmpty()){
                lru.remove(min);
            }
            freq.remove(key);
            map.remove(key);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
