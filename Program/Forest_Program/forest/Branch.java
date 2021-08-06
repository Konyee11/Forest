package forest;

/**
 * Branchクラス
 */
public class Branch extends Object {

    private Node parent;
    private Node child;

    /**
     * コンストラクタ
     * @param parent
     * @param child
     */
    Branch(Node parent, Node child) {
    this.parent = parent;
    this.child = child;
    }

    /**
     * 親ノードを応答する
     */
    public Node getParent() {
    return this.parent;
    }

    /**
     * 子ノードを応答する
     */
    public Node getChild() {
    return this.child;
    }
}
