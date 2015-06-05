class Result{
	boolean balanced;
	int height;
	Result(...)
}

public Result isBalanced(TreeNode root){
	if (root == null) return new Result(true,0);

	Result left = isBalanced(root.left);
	if (!left.balanced) return left;

	Result right = isBalanced(root.right);
	if (!right.balanced) return right;

	boolean balanced = Math.abs(left.height-right.height) < 2;
	return new Result(balanced, balanced ? Math.max(left.height, right.height)+1 : -1); 
}
