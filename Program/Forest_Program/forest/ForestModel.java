package forest;

import java.awt.event.MouseEvent;
import java.awt.Point;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import mvc.Model;

/**
 * ForestModelクラス
 */
public class ForestModel extends Model {

    private Forest aForest;

    /**
     * コンストラクタ
     * @param aFile
     */
	public ForestModel(File aFile) {
		super();
        this.aForest = new Forest();
        this.aForest.setModel(this);
        try {
            this.aForest.readFile(aFile);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        aForest.setRoot();

        int y=0;
        HashMap<Integer,Node> nodes = this.getForest().getNodes();
        for (Node aNode : nodes.values()) {
            aNode.setLocation(0,y);
            y += aNode.getSize().height+Const.VERTICAL_GAP;
        }
	}

    /**
     * Forestを応答する
     * @return
     */
    public Forest getForest() {
        return this.aForest;
    }

    /**
     * マウスクリックした位置を座標として受け取り，　その位置にNodeがあれば出力
     * @param aPoint
     * @param aEvent
     */
	public void mouseClicked(Point aPoint, MouseEvent aEvent) {
        HashMap<Integer,Node> nodes = aForest.getNodes();
        for (Node node : nodes.values()) {
            if(node.getLocation().x <= aPoint.x && node.getLocation().x+node.getWidth() >= aPoint.x) {
                if(node.getLocation().y <= aPoint.y && node.getLocation().y+node.getHeight() >= aPoint.y) {
                    System.out.println(node.getName());
                }
            }
        }
		return;
	}

    /**
     * アニメーション実行
     */
    public void animate() {
        ArrayList<Node> roots = this.aForest.getRoot();
        for(Node node : roots) {
            aForest.visit(node, new Point(0, Forest.underLine));
        }
        return;
    }
}
