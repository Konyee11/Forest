package forest;

import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Node extends JLabel {

	private int nodeNum;
    private String nodeName;
    private Node parent;
    private ArrayList<Node> children;
    private boolean visit;

    /**
     * コンストラクタ
     * @param num
     * @param name
     */
    Node(int num, String name) {
        this.nodeNum = num;
        this.nodeName = name;
        this.parent = null;
        this.children = new ArrayList<Node>();
        this.visit = false;

		this.setFont(new Font("Arial", Font.PLAIN, 12));
        this.setText(this.nodeName);
		this.setBorder(new LineBorder(Color.black, 1));
        this.setSize(this.getPreferredSize());
    }

    /**
     * ノード番号を応答する
     * @return
     */

    @Test
    @DisplayName("暫定-9")
    public int getNodeNum() {
        assertNotNull(this.nodeNum);
        return this.nodeNum;
    }

    /**
     * ノード名を応答する
     */
    @Test
    @DisplayName("暫定-9")
    public String getName() {
        assertNotNull(this.nodeName);
        return this.nodeName;
    }

    /**
     * 親ノードのインスタンスを応答する
     */
    @Test
    @DisplayName("暫定-9")
    public Node getParent() {
        assertNotNull(this.parent);
        return this.parent;
    }

    /**
     * 子ノードのインスタンスを応答する
     * @return
     */
    @Test
    @DisplayName("暫定-9")
    public ArrayList<Node> getChildren() {
        assertNotNull(this.children);
        return this.children;
    }

    /**
     * 親ノードのインスタンスを設定する。
     * @param aNode
     */
    @Test
    @DisplayName("暫定-9")
    public void setParent(Node node) {
        this.parent = node;
        assertNotNull(this.parent);
        return;
    }

    /**
     * 子ノードのインスタンスを設定する。
     * @param aNode
     */
    @Test
    @DisplayName("暫定-9")
    public void setChildren(Node aNode) {
        this.children.add(aNode);
        Collections.sort(this.children, new NodeComparator());
        assertNotNull(this.children);
        return;
    }

    /**
     * 探索状態を設定する。
     * @param flag
     */
    @Test
    @DisplayName("暫定-9")
    public void setVisit(boolean flag) {
        this.visit = flag;
        assertNotNull(this.visit);
        return;
    }

    /**
     * 探索済みか応答する。
     */
    @Test
    @DisplayName("暫定-9")
    public boolean getVisit() {
        assertNotNull(this.visit);
        return this.visit;
    }
}