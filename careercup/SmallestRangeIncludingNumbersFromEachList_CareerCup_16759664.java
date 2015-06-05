import java.util.*;

/**

http://www.careercup.com/question?id=16759664

You have k lists of sorted integers. Find the smallest range that includes at least one number from each of the k lists. 

For example, 
List 1: [4, 10, 15, 24, 26] 
List 2: [0, 9, 12, 20] 
List 3: [5, 18, 22, 30] 

The smallest range here would be [20, 24] as it contains 24 from list 1, 20 from list 2, and 22 from list 3.

**/
class SmallestRangeIncludingNumbersFromEachList_CareerCup_16759664{
        public static void main(String[]args){
                System.out.println(
                        find(
                                new int[]{4, 10, 15, 24, 26}
                                , new int[]{0, 9, 12, 20}
                                , new int[]{5, 18, 22, 30}
                        )
                );
        }
        static Range find(int[]...arrays){
                ListScanner listScanner = new ListScanner(arrays);
                listScanner.intializeQueues();

                Range answer = null;
                for(;;){
                        listScanner.moveEndCursor();
                        if (!listScanner.isAllListBetweenCursors()){
                                break;
                        }
                        listScanner.moveStartCursor();
                        Range newRange = listScanner.getRangeBetweenCursors();
                        if (answer == null || newRange.compareTo(answer) < 0){
                                answer = newRange;
                        }
                }
                return answer;
        }

        static class ListScanner{
                int[][] arrays;
                int[] itemCountPerListInRange;
                int listCountInRange = 0;
                PriorityQueue<Node> startQueue;
                PriorityQueue<Node> endQueue;
                int listCount;
                Node startCursor;
                Node endCursor;

                ListScanner(int[][]arrays){
                        this.arrays = arrays;
                        this.listCount = arrays.length;
                        itemCountPerListInRange = new int[this.listCount];

                }
                void intializeQueues(){
                        startQueue = new PriorityQueue<Node>();
                        endQueue = new PriorityQueue<Node>();
                        for(int i = 0; i < listCount; ++i){
                                if (arrays[i] == null || arrays[i].length < 1) throw new IllegalArgumentException();
                                Node node = new Node(arrays[i][0], i, 0);
                                endQueue.add(node);
                                startQueue.add(node);
                        }
                        startCursor = null;
                        endCursor = null;
                }
                void moveEndCursor(){
                        while(listCountInRange < listCount && !endQueue.isEmpty()){
                                endCursor = endQueue.remove();
                                ++itemCountPerListInRange[endCursor.listIdx];
                                if(itemCountPerListInRange[endCursor.listIdx] == 1){
                                        ++listCountInRange;
                                }
                                Node nextNode = endCursor.nextNode(arrays);
                                if (nextNode != null){
                                        endQueue.add(nextNode);
                                        startQueue.add(nextNode);
                                }
                       }
                }
                void moveStartCursor(){
                        while(listCountInRange == listCount){
                                startCursor = startQueue.remove();
                                --itemCountPerListInRange[startCursor.listIdx];
                                if(itemCountPerListInRange[startCursor.listIdx] <= 0){
                                        --listCountInRange;
                                }
                        }
                }
                Range getRangeBetweenCursors(){
                        Range newRange = new Range(startCursor.value, endCursor.value);
                        return newRange;
                }
                boolean isAllListBetweenCursors(){
                        return (listCountInRange >= listCount);
                }
        }
        static class Node implements Comparable<Node>{
                final int value;
                final int listIdx;
                final int itemIdx;
                Node(int value, int listIdx, int itemIdx){
                        this.value = value;
                        this.listIdx = listIdx;
                        this.itemIdx = itemIdx;
                }
                public int compareTo(Node other){
                        return Integer.compare(this.value, other.value);
                }
                public Node nextNode(int[][] arrays){
                        int nextItemIdx = itemIdx+1;
                        if (listIdx < arrays.length && nextItemIdx < arrays[listIdx].length){
                                return new Node(arrays[listIdx][nextItemIdx], listIdx, nextItemIdx);
                        }
                        return null;
                }
        }
        static class Range implements Comparable<Range>{
                final int start;
                final int end;
                final int size;
                Range(int start, int end){
                        this.start = start;
                        this.end = end;
                        this.size = this.end-this.start+1;
                }
                public String toString(){
                        return String.format("[%d,%d]", start, end);
                }
                public int compareTo(Range other){
                        return Integer.compare(size, other.size);
                }
        }
}
                                                                                                                                   
