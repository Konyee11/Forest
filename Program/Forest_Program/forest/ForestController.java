package forest;

import java.awt.event.MouseEvent;
import java.awt.Point;
import mvc.Controller;

/**
 * ForestControllerクラス
 */
public class ForestController extends Controller {

	/**
	 * コンストラクタ
	 */
	public ForestController() {
		super();
	}

	/**
	 * マウスクリックした位置のノードを出力する
	 */
	public void mouseClicked(MouseEvent aEvent) {
  	Point aPoint = aEvent.getPoint();
		aPoint.translate(view.scrollAmount().x, view.scrollAmount().y);
		ForestModel aForestModel = (ForestModel)(this.model);
		aForestModel.mouseClicked(aPoint, aEvent);
		return;
	}
}
