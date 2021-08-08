package forest;

import java.awt.Point;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import utility.StringUtility;



/**
 * Forestクラス
 */

 
public class Forest extends Object {

    private ForestModel model;
    public static ArrayList<Node> rootNodes;
    private HashMap<Integer,Node> nodes;
    public static ArrayList<Branch> branches;
    public static int underLine;

	/**
	 * コンストラクタ
	 */
	public Forest() {
        this.model = null;
        this.rootNodes = new ArrayList<Node>();
        this.nodes = new HashMap<Integer,Node>();
        this.branches = new ArrayList<Branch>();
        this.underLine = 0;
	}

    /**
     * 指定されたテキストファイルの読み込む。
     * @param aFile
     */


    public void readFile(File aFile) throws IOException {
        ArrayList<String> textList = (ArrayList<String>) StringUtility.readTextFromFile(aFile);
        System.out.println(textList.size());

        int type = 0;
        for (String text : textList) {
            text = text.replaceAll(" ", "");
            System.out.println("> " + text);
            if ( text.equals("nodes:") ) {type = 1;}
            if ( text.equals("branches:") ) {type = 2;}
            this.setTypeData(type, text);
        }


        return;
    }

    /**
     * テキストデータからnodeとbranchを取り出して束縛する
     * @param type
     * @param text
     */
    private void setTypeData(int type, String text) {
        if ( text.equals("nodes:") ) {return;}
        if ( text.equals("branches:") ) {return;}

        if (type == 1)
        {
            String[] tmp = text.split(",");
            int nodeNumber = Integer.parseInt(tmp[0]);
            String nodeName = tmp[1];
            Node aNode = new Node(nodeNumber, nodeName);
            this.nodes.put(nodeNumber, aNode);
        }
        else if (type == 2)
        {
            String[] tmp = text.split(",");
            Node parentNode = this.nodes.get( Integer.parseInt(tmp[0]) );
            Node childNode = this.nodes.get( Integer.parseInt(tmp[1]) );

            parentNode.setChildren(childNode);
            childNode.setParent(parentNode);
            this.nodes.put(Integer.parseInt(tmp[0]), parentNode);
            this.nodes.put(Integer.parseInt(tmp[1]), childNode);

            Branch aBranch = new Branch(parentNode, childNode);
            this.branches.add(aBranch);
        }
        return;
    }

    /**
     * モデルを設定する
     * @param aModel
     */
    public void setModel(ForestModel aModel) {
        this.model = aModel;
        return;
    }

    /**
     * ルートノードのインスタンスを設定する
     */
    public void setRoot() {
        for (Node node : this.nodes.values()) {
            if(node.getParent() == null){ rootNodes.add(node); }
        }
        return;
    }

    /**
     * ノードのインスタンスを応答する
     * @return
     */
    public HashMap<Integer,Node> getNodes() {
        return this.nodes;
    }

    /**
     * ルートのインスタンスを応答する
     * @return
     */
    public ArrayList<Node> getRoot() {
        return this.rootNodes;
    }

    /**
     * ブランチのインスタンスを応答する
     * @return
     */
    public ArrayList<Branch> getBranches() {
        return this.branches;
    }

    /**
     * 木を再帰的に探索し，　下限の位置を元に位置を設定する．
     * モデルの内部状態が変化したので，自分の依存物へのupdateメッセージを送信
     * @param root
     * @param aPoint
     */
    public void visit(Node root, Point aPoint) {
        if(root.getVisit() == false){ root.setLocation(aPoint); }
        try {
            Thread.sleep(Const.SLEEP_TIME);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.model.changed();

        int childY = aPoint.y;
        int count=0;
        for(Node childNode : root.getChildren()) {
            while(childY <= this.underLine) {childY += 18;}
            this.visit(childNode,new Point(aPoint.x+root.getWidth()+Const.HORIZONTAL_GAP, childY)); //子Nodeを探索
            childNode.setVisit(true);
            count++;
            if(root.getChildren().size() > count) {
                childY += Const.VERTICAL_GAP + root.getHeight();
            }
        }

        if(this.underLine < childY) {this.underLine = childY;}
        if(root.getVisit() == false){root.setLocation( aPoint.x, (childY+aPoint.y+root.getHeight()) / 2 - (root.getHeight()/2) );}
        if(root.getParent()==null && root.getChildren().size()==1){ root.setLocation( aPoint.x, root.getChildren().get(0).getLocation().y);}

        return;
    }
}
