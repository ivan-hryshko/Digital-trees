package Test;
import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IvanEndorphin on 30.08.2020.
 */
public class TestSome {
        public static void main(String[] args) {
            List<String> list = Arrays.asList("Мама мыла раму.", "Долго и скучала.", "Вечер уж настал");
            SwingUtilities.invokeLater(() ->
            {
                StringBuilder stringBuilder = new StringBuilder();
                for (String s : list) {
                    stringBuilder.append(s).append("\n");
                }
                JTextArea textArea = new JTextArea(stringBuilder.toString());
                JFrame frame = new JFrame("_________");
                frame.add(new JScrollPane(textArea));
                frame.setSize(1000, 600);
                frame.setLocationRelativeTo(null);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            });
        }

}
