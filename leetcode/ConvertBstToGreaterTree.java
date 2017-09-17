/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode convertBST(TreeNode root) {
        convert(root, 0);
        return root;
    }
    private int convert(TreeNode root, int sum){
        if (root == null) return sum;
        root.val += convert(root.right, sum);
        return convert(root.left, root.val);
    }
}
