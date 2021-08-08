package forest;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * FileChooserクラス
 */
public class FileChooser extends JFrame implements ActionListener {

    File aFile;
    JLabel label;

    /**
     * コンストラクタ
     */
    FileChooser() {
        this.aFile = null;
        JButton aButton = new JButton("テキストファイル選択");
        aButton.addActionListener(this);
        JPanel aPanel = new JPanel();
        aPanel.add(aButton);

        label = new JLabel();
        JPanel labelPanel = new JPanel();
        labelPanel.add(label);

        this.setSize(300,200);
        this.setTitle("Forest");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(labelPanel, BorderLayout.CENTER);
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
            this.label.setText("ファイルが選択されました = "+this.aFile.getName());
        }
        else if (selected == JFileChooser.CANCEL_OPTION) {
            this.label.setText("キャンセルされました。");
        }
        else if (selected == JFileChooser.ERROR_OPTION) {
            this.label.setText("エラー又は取り消しがありました。");
        }
    }

    /**
     * 読み込んだファイルのインスタンスを応答する
     */
    public File getFile() {
        return this.aFile;
    }
}
