import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class ViewportManager {

    JFrame frame = new JFrame("Tester");
    int lastTile = 0;

    HashMap<Integer, JButton> buttons = new HashMap<Integer, JButton>();
    void fillMap () {
        for (int i = 0; i < 9; i++) {
            buttons.put(i + 1, new JButton("" + (i + 1)));
        }
    }

    Color[] colors = {Color.blue, Color.green, Color.yellow, Color.orange, Color.pink, Color.magenta};

    void init () {
        frame.setSize(900, 900);
        frame.setResizable(false);
        frame.setLayout(new GridLayout(3, 3));
        frame.setDefaultCloseOperation(3);
        frame.setFont(new Font("Arial", Font.BOLD, 32));

    }

    void setObjects () {

        for (JButton bt : buttons.values()) {
            bt.addActionListener(this::actionPerformer);
            frame.add(bt);
        }
        frame.setVisible(true);
    }

    void actionPerformer(ActionEvent e) {
        scramble(keys(buttons, (JButton)e.getSource()));
    }

    void scramble (Integer n) {
        int x = (int) (Math.random() * buttons.size());
        int y = (int) (Math.random() * colors.length);

            if (n != lastTile) {
                buttons.get(n).setBackground(Color.red);
                for (int i = 1; i <= buttons.size(); i++) {
                    if (i != n) {
                        buttons.get(i).setBackground(colors[y]);
                    }
                }
            }
            lastTile = n;
    }

    public <K, V> K keys(Map<K, V> map, V value) {
        return map
                .entrySet()
                .stream()
                .filter(entry -> value.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst().get();
    }
}


