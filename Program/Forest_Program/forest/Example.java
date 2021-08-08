package forest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.io.File;
import javax.swing.JFrame;





/**
 * 例題プログラム。
 */
public class Example extends Object {

    /**
     * メインメソッド
     * @param arguments
     * @throws IOException
     */

	public static void main(String[] args) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File aFile = null;
        fileChooser.setLocation(0,0);

        while(aFile == null){
            aFile = fileChooser.getFile();
            System.out.print("");
        }

        ForestModel aModel = new ForestModel(aFile);
        ForestController aController = new ForestController();
		ForestView aView = new ForestView(aModel, aController);

        JFrame aWindow = new JFrame("Forest");
        aWindow.getContentPane().add(aView);
        aWindow.setLayout(null);
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int titleBarHeight = aWindow.getInsets().top;
		aWindow.getContentPane().setBackground(new Color(255,255,255));
		Point aPoint = new Point(Const.WINDOW_WIDTH, Const.WINDOW_HEIGHT);
		aWindow.setMinimumSize(new Dimension(aPoint.x, aPoint.y + titleBarHeight));
		aWindow.setResizable(true);
		aWindow.setSize(aPoint.x, aPoint.y + titleBarHeight);
		aWindow.setLocation(0, 0);
		aWindow.setVisible(true);
		aWindow.toFront();

        aModel.animate();


		return;
	}
}
