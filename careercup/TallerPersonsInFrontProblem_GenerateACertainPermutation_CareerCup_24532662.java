import java.util.*;
/**
You are given two array, first array contain integer which represent heights of persons and second array contain how many persons in front of him are standing who are greater than him in term of height and forming a queue. Ex 
A: 3 2 1 
B: 0 1 1 
It means in front of person of height 3 there is no person standing, person of height 2 there is one person in front of him who has greater height then he, similar to person of height 1. Your task to arrange them 
Ouput should be. 
3 1 2 
Here - 3 is at front, 1 has 3 in front ,2 has 1 and 3 in front.


**/
class TallerPersonsInFrontProblem_GenerateACertainPermutation_CareerCup_24532662{
        public static void main(String[]args){
                //print(inversionSortQuadratic(new int[] {3, 2, 1}, new int[] {0, 1, 1}));
                print(inversionSortQuadratic(new int[] {2,3,5,4,6,1}, new int[] {0,3,0,0,0,4}));
                print(inversionSortQuadratic(new int[]{6,1,11,5,10,4}, new int[] {2, 4, 0, 1, 0, 0}));
        }
        private static void print(Node[] arr){
                for(Node i : arr){
                        System.out.printf(" %s", i.toString());
                }
                System.out.println();
        }
        public static Node[] inversionSortQuadratic(int[] arr, int[] invs){
                Node[] nodes = parseNodes(arr, invs);
                print(nodes);
                Arrays.sort(nodes, new Comparator<Node>(){
                        public int compare(Node n1, Node n2){
                                return Integer.compare(n1.height, n2.height);
                        }
                });
                for(int i = nodes.length-2; i >= 0; --i){
                        insert(nodes, i, i+nodes[i].invCount);
                }
                return nodes;
        }
        private static void insert(Node[] nodes, int from, int to){
                for(int i = from; i < to; ++i){
                        swap(nodes, i, i+1);
                }
        }
        private static void swap(Node[] nodes, int from, int to){
                if (from == to) {
                        return;
                }
                Node tmp = nodes[from];
                nodes[from] = nodes[to];
                nodes[to] = tmp;
        }
        private static Node[] parseNodes(int[]arr,int[]invs){
                Node[] ret = new Node[arr.length];
                for(int i = 0; i < arr.length; ++i){
                        ret[i] = new Node(arr[i], invs[i]);
                }
                return ret;
        }
}
class Node{
        int height;
        int invCount;
        Node(int height, int invCount){
                this.height = height;
                this.invCount = invCount;
        }
        public String toString(){
                return String.format("[%d,%d]", this.height, this.invCount);
        }
}