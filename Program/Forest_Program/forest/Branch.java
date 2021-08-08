package forest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    @DisplayName("暫定-2")
    public Node getParent() {
        assertNotNull(this.parent);
    return this.parent;
    }

    /**
     * 子ノードを応答する
     */
    @Test
    @DisplayName("暫定-3")
    public Node getChild() {
        assertNotNull(this.child);
    return this.child;
    }
}
