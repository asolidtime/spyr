package spyr;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LivestreamWindow {
    private JTextField pasteYouTubeLivestreamURLTextField;
    private JButton startButton;
    private JPanel livestreamWindow;
    private JPanel videoPane;
    private JFrame frame;

    private void close() {
        // stop audio player, but don't release
        App.embeddedMediaPlayerComponent.mediaPlayer().controls().stop();
        frame.setVisible(false);
        frame.dispose();
    }

    public LivestreamWindow() {
        $$$setupUI$$$();
        frame = new JFrame("Play livestream");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close();
            }
        });
        frame.pack();
        frame.setBounds(100, 100, 400, 350);
        frame.setContentPane(livestreamWindow);
        frame.setVisible(true);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startButton.getText().equals("Start")) {
                    App.embeddedMediaPlayerComponent.mediaPlayer().media().play(pasteYouTubeLivestreamURLTextField.getText());
                    startButton.setText("Stop");
                } else {
                    close();
                }
            }
        });
    }
    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        livestreamWindow = new JPanel();
        livestreamWindow.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("<html>You can play audio from a livestream here. <br>Note that this is still a bit experimental, so <br>you might have to restart the app if any weirdness occurs.</html>");
        livestreamWindow.add(label1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pasteYouTubeLivestreamURLTextField = new JTextField();
        pasteYouTubeLivestreamURLTextField.setText("Paste YouTube livestream URL here...");
        livestreamWindow.add(pasteYouTubeLivestreamURLTextField, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        startButton = new JButton();
        startButton.setText("Start");
        livestreamWindow.add(startButton, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        livestreamWindow.add(videoPane, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return livestreamWindow;
    }

    private void createUIComponents() {
        videoPane = new JPanel(new BorderLayout());
        videoPane.add(App.embeddedMediaPlayerComponent, BorderLayout.CENTER);
    }
}
