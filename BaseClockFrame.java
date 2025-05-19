// BaseClockFrame.java
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.*;

public class BaseClockFrame extends JFrame {
    protected JLabel localTimeLabel;
    protected JLabel foreignTimeLabel;
    protected JLabel dateLabel;

    protected boolean is24HourFormat = true;
    protected JPanel panel;
    protected JComboBox<String> timeZoneComboBox;
    protected JComboBox<String> fontComboBox;
    protected JComboBox<String> backgroundFormatComboBox;

    protected Color userBackgroundColor = Color.BLACK;
    protected Color userTextColor = Color.WHITE;

    protected final String[] timeZones = {
        "Asia/Kolkata", "America/New_York", "Europe/London",
        "Asia/Tokyo", "Australia/Sydney", "Europe/Paris"
    };

    protected final String[] fontStyles = {
        "Arial", "Courier New", "Times New Roman", "Verdana", "Consolas"
    };

    protected final String[] backgroundModes = {
        "Random Colors", "Custom Picker"
    };

    public BaseClockFrame() {
        setTitle("Digital Clock");
        setSize(650, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(userBackgroundColor);
        add(panel);

        Timer timer = new Timer(1000, e -> updateClock());
        timer.start();
    }

    protected JLabel createTimeLabel() {
        JLabel label = new JLabel();
        label.setFont(new Font("Arial", Font.BOLD, 36));
        label.setForeground(userTextColor);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    protected void updateClock() {
        String timeFormat = is24HourFormat ? "HH:mm:ss" : "hh:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        String localTime = sdf.format(new Date());
        localTimeLabel.setText("Local Time: " + localTime);

        sdf.setTimeZone(TimeZone.getTimeZone((String) timeZoneComboBox.getSelectedItem()));
        String foreignTime = sdf.format(new Date());
        foreignTimeLabel.setText("Selected Time Zone: " + foreignTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy");
        dateLabel.setText("Date: " + dateFormat.format(new Date()));
    }

    protected void changeFontStyle(ActionEvent e) {
        String fontName = (String) fontComboBox.getSelectedItem();
        localTimeLabel.setFont(new Font(fontName, Font.BOLD, 36));
        foreignTimeLabel.setFont(new Font(fontName, Font.BOLD, 36));
        dateLabel.setFont(new Font(fontName, Font.BOLD, 24));
    }

    protected void changeBackgroundColor() {
        String selectedMode = (String) backgroundFormatComboBox.getSelectedItem();
        if ("Random Colors".equals(selectedMode)) {
            Color[] colors = {
                Color.BLACK, Color.RED, Color.BLUE, Color.GREEN,
                Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE
            };
            int randomIndex = (int) (Math.random() * colors.length);
            userBackgroundColor = colors[randomIndex];
        } else if ("Custom Picker".equals(selectedMode)) {
            Color pickedColor = JColorChooser.showDialog(this, "Choose Background Color", userBackgroundColor);
            if (pickedColor != null) {
                userBackgroundColor = pickedColor;
            }
        }
        panel.setBackground(userBackgroundColor);
        adjustTextColorForBackground(userBackgroundColor);
    }

    protected void changeTextColor() {
        Color pickedColor = JColorChooser.showDialog(this, "Choose Text Color", userTextColor);
        if (pickedColor != null) {
            userTextColor = pickedColor;
        }
        localTimeLabel.setForeground(userTextColor);
        foreignTimeLabel.setForeground(userTextColor);
        dateLabel.setForeground(userTextColor);
    }

    protected void adjustTextColorForBackground(Color bg) {
        int brightness = (int) Math.sqrt(
            bg.getRed() * bg.getRed() * 0.241 +
            bg.getGreen() * bg.getGreen() * 0.691 +
            bg.getBlue() * bg.getBlue() * 0.068
        );

        Color textColor = (brightness < 130) ? Color.WHITE : Color.BLACK;

        localTimeLabel.setForeground(textColor);
        foreignTimeLabel.setForeground(textColor);
        dateLabel.setForeground(textColor);
    }
}
