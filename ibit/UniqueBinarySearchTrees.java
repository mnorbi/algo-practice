/**

Given A, generate all structurally unique BST’s (binary search trees) that store values 1...A.

Example : 
Given A = 3, your program should return all 5 unique BST’s shown below.


   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3


**/
/**
 * Definition for binary tree
 * class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	public ArrayList<TreeNode> generateTrees(int a) {
	    if (a <= 0) { return new ArrayList<>(); }
	    ArrayList<TreeNode> acc = new ArrayList<>();
	    Deque<int[]> stack = new ArrayDeque<int[]>(); stack.push(new int[]{1,a});
	    generate(acc, new ArrayList<>(), stack);
		  return acc;
	}
	void generate(List<TreeNode> acc, List<Integer> preOrd, Deque<int[]> stack){
	    if (stack.isEmpty()){
	        acc.add(treeFrom(preOrd));
	        return;
	    }
	    int[] range = stack.pop();
	    for(int lo = range[0], hi = range[1], mid = lo; mid <= hi; ++mid){
	        preOrd.add(mid);
	        if (mid+1 <= hi) { stack.push(new int[]{mid+1,hi}); }
	        if (mid-1 >= lo) { stack.push(new int[]{lo,mid-1}); }
	        
	        generate(acc,preOrd,stack);
	        
	        preOrd.remove(preOrd.size()-1);
	        if (mid-1 >= lo) { stack.pop(); }
	        if (mid+1 <= hi) { stack.pop(); }
	    }
	    stack.push(range);
	}
	TreeNode treeFrom(List<Integer> preOrd){
	    if (preOrd.size() == 0) { return null; }
	    TreeNode ret = new TreeNode(preOrd.get(0));
	    int split = 1;
	    for(;split < preOrd.size() && ret.val > preOrd.get(split); ++split);
	    ret.left = treeFrom(preOrd.subList(1,split));
	    if (split <= preOrd.size()) { ret.right = treeFrom(preOrd.subList(split,preOrd.size())); }
	    return ret;
	}
}
