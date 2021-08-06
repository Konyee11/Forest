package forest;

import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

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
    public int getNodeNum() {
        return this.nodeNum;
    }

    /**
     * ノード名を応答する
     */
    public String getName() {
        return this.nodeName;
    }

    /**
     * 親ノードのインスタンスを応答する
     */
    public Node getParent() {
        return this.parent;
    }

    /**
     * 子ノードのインスタンスを応答する
     * @return
     */
    public ArrayList<Node> getChildren() {
        return this.children;
    }

    /**
     * 親ノードのインスタンスを設定する。
     * @param aNode
     */
    public void setParent(Node node) {
        this.parent = node;
        return;
    }

    /**
     * 子ノードのインスタンスを設定する。
     * @param aNode
     */
    public void setChildren(Node aNode) {
        this.children.add(aNode);
        Collections.sort(this.children, new NodeComparator());
        return;
    }

    /**
     * 探索状態を設定する。
     * @param flag
     */
    public void setVisit(boolean flag) {
        this.visit = flag;
        return;
    }

    /**
     * 探索済みか応答する。
     */
    public boolean getVisit() {
        return this.visit;
    }
}