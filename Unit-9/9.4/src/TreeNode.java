

class TreeNode<T extends Comparable<?>> {
    TreeNode<T> left, right;
    T name;

    public TreeNode(T data) {
        this.name = data;
    }
}