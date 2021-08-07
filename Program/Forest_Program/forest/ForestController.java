package forest;

import java.awt.event.MouseEvent;
import java.awt.Point;
import mvc.Controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

	@Test
    @DisplayName("暫定-9")
	public void mouseClicked(MouseEvent aEvent) {
  	Point aPoint = aEvent.getPoint();
		aPoint.translate(view.scrollAmount().x, view.scrollAmount().y);
		ForestModel aForestModel = (ForestModel)(this.model);
		aForestModel.mouseClicked(aPoint, aEvent);

		assertNotNull(aPoint);
		return;
	}
}
