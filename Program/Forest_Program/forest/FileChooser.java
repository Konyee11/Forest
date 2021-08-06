package forest;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * FileChooserクラス
 */
public class FileChooser extends JFrame implements ActionListener {

    File aFile;

    /**
     * コンストラクタ
     */
    FileChooser() {
        this.aFile = null;
        JButton aButton = new JButton("ファイル選択");
        aButton.addActionListener(this);
        JPanel aPanel = new JPanel();
        aPanel.add(aButton);

        this.setSize(300,200);
        this.setTitle("ファイル名を選択");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(aPanel, BorderLayout.PAGE_END);
        this.setVisible(true);
    }

    /**
     * ファイル選択時の処理
     */
    public void actionPerformed(ActionEvent aEvent) {
        JFileChooser filechooser = new JFileChooser();
        int selected = filechooser.showOpenDialog(this);

        if (selected == JFileChooser.APPROVE_OPTION) {
            this.aFile = filechooser.getSelectedFile();
            System.out.println("ファイルが選択されました = "+this.aFile.getName());
        }
        else if (selected == JFileChooser.CANCEL_OPTION) {
            System.out.println("キャンセルされました。");
        }
        else if (selected == JFileChooser.ERROR_OPTION) {
            System.out.println("エラー又は取り消しがありました。");
        }
    }

    /**
     * 読み込んだファイルのインスタンスを応答する
     */
    public File getFile() {
        return this.aFile;
    }
}
