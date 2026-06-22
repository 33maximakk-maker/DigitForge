// DigitalClock.java - Цифровые часы на Java (Swing)
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DigitalClock extends JFrame {
    private JLabel timeLabel, dateLabel;
    private boolean format24 = true;
    private boolean showSeconds = true;
    private Color textColor = Color.GREEN;
    private Color bgColor = Color.BLACK;
    private Timer timer;

    public DigitalClock() {
        setTitle("🕐 DigitForge - Java");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setResizable(false);
        setLocationRelativeTo(null);

        // Панель с фоном
        JPanel panel = new JPanel();
        panel.setBackground(bgColor);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        timeLabel = new JLabel();
        timeLabel.setFont(new Font("Courier New", Font.BOLD, 64));
        timeLabel.setForeground(textColor);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(timeLabel, gbc);

        gbc.gridy = 1;
        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        dateLabel.setForeground(textColor);
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(dateLabel, gbc);

        // Панель управления
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(bgColor);
        controlPanel.setLayout(new FlowLayout());
        gbc.gridy = 2;
        panel.add(controlPanel, gbc);

        JButton formatBtn = new JButton("12/24 ч");
        formatBtn.addActionListener(e -> { format24 = !format24; });
        controlPanel.add(formatBtn);

        JButton secondsBtn = new JButton("Секунды");
        secondsBtn.addActionListener(e -> { showSeconds = !showSeconds; });
        controlPanel.add(secondsBtn);

        JButton colorBtn = new JButton("Цвет текста");
        colorBtn.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Выберите цвет текста", textColor);
            if (c != null) { textColor = c; applyColors(); }
        });
        controlPanel.add(colorBtn);

        JButton bgBtn = new JButton("Цвет фона");
        bgBtn.addActionListener(e -> {
            Color c = JColorChooser.showDialog(this, "Выберите цвет фона", bgColor);
            if (c != null) { bgColor = c; applyColors(); }
        });
        controlPanel.add(bgBtn);

        add(panel);

        timer = new Timer(1000, e -> updateClock());
        timer.start();
        updateClock();
        setVisible(true);
    }

    private void updateClock() {
        LocalDateTime now = LocalDateTime.now();
        String timeStr;
        if (format24) {
            timeStr = now.format(DateTimeFormatter.ofPattern("HH:mm"));
        } else {
            timeStr = now.format(DateTimeFormatter.ofPattern("hh:mm"));
        }
        if (showSeconds) {
            timeStr += now.format(DateTimeFormatter.ofPattern(":ss"));
        }
        if (!format24) {
            timeStr += now.format(DateTimeFormatter.ofPattern(" a"));
        }
        timeLabel.setText(timeStr);

        String dateStr = now.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", new Locale("ru")));
        dateLabel.setText(dateStr);
    }

    private void applyColors() {
        timeLabel.setForeground(textColor);
        dateLabel.setForeground(textColor);
        Container parent = timeLabel.getParent();
        if (parent instanceof JPanel) {
            parent.setBackground(bgColor);
            parent.getComponent(2).setBackground(bgColor); // controlPanel
        }
        getContentPane().setBackground(bgColor);
        // обновить фон кнопок? не обязательно
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DigitalClock::new);
    }
}
