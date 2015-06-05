import java.util.*;
class KMostFrequentItems{
  public static void main(String[]args){
    int k = 3;
    String[] items = new String[] {"Do", "Nguyen", "Nguyen", "Doan", "Ngo", "Ngo"};
    System.out.println("With min heap:");
    printKMostFrequentItemsWithMinHeap(k, items);
    System.out.println("\n\nWith max heap:");
    printKMostFrequentItemsWithMaxHeap(k, items);
  }

  static Comparator<Map.Entry<String, Integer>> MIN_HEAP_COMPARATOR =
    new Comparator<Map.Entry<String, Integer>>() {
      public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
        int ret = Integer.compare(e1.getValue(), e2.getValue());
        if (ret == 0){
          ret = e1.getKey().compareTo(e2.getKey());
        }
        return ret;
      }
    };

  static Comparator<Map.Entry<String, Integer>> MAX_HEAP_COMPARATOR =
    Collections.reverseOrder(MIN_HEAP_COMPARATOR);

  static void printKMostFrequentItemsWithMinHeap(int k, String[] stringArr) {
    Map<String, Integer> freqTable = buildFreqTableOf(stringArr);
    PriorityQueue<Map.Entry<String, Integer>> minHeap =
      new PriorityQueue<Map.Entry<String, Integer>>(k+1, MIN_HEAP_COMPARATOR);
    for(Map.Entry<String, Integer> entry : freqTable.entrySet()){
        minHeap.add(entry);
        while (minHeap.size() > k){
          minHeap.remove();
        }
    }
    Deque<Map.Entry<String, Integer>> deque = new LinkedList<Map.Entry<String, Integer>>();
    while(!minHeap.isEmpty()){
      deque.addFirst(minHeap.remove());
    }
    print(deque);
  }

  static void printKMostFrequentItemsWithMaxHeap(int k, String[] stringArr) {
    Map<String, Integer> freqTable = buildFreqTableOf(stringArr);
    PriorityQueue<Map.Entry<String, Integer>> maxHeap =
      new PriorityQueue<Map.Entry<String, Integer>>(freqTable.size(), MAX_HEAP_COMPARATOR);
    maxHeap.addAll(freqTable.entrySet());
    Deque<Map.Entry<String, Integer>> deque = new LinkedList<Map.Entry<String, Integer>>();
    while(k > 0 && !maxHeap.isEmpty()){
      deque.addLast(maxHeap.remove());
      --k;
    }
    print(deque);
  }
  static void print(Collection<Map.Entry<String, Integer>> coll){
    for(Map.Entry<String, Integer> entry : coll){
      print(entry);
    }
  }
  static void print(Map.Entry<String, Integer> entry) {
    System.out.println(String.format("%s %d", entry.getKey(), entry.getValue()));
  }

  static Map<String, Integer> buildFreqTableOf(String[] stringArr) {
    Map<String, Integer> freqTable = new HashMap<String, Integer>();
    for(String string: stringArr){
      Integer freq = 0;
      if (freqTable.containsKey(string)){
        freq = freqTable.get(string);
      }
      freqTable.put(string, ++freq);
    }
    return freqTable;
  }

}
