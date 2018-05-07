class SplitBst{
	/*
		[0] -  >val
		[1] - <=val
	*/
	TreeNode[] split(TreeNode root, int val){
		if (root == null) return null;
		if (root.val > val){
			TreeNode[] split = split(root.left, val);
			if (split !=  null){
				root.left = split[0];
				return new TreeNode[]{root, split[1]};
			}
		} else if (root.val < val){
			TreeNode[] split = split(root.right, val);
			if (split != null){
				root.right = split[1];
				return new TreeNode[]{split[0], root};
			}
		} else {
			TreeNode[] split = new TreeNode[]{root.right, root};
			root.right = null;//finish loose ends
			return split;
		}
		return null;
	}
	static class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
	}
}
