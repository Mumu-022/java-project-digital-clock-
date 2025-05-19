// DigitalClock.java
import java.awt.*;
import javax.swing.*;

public class DigitalClock extends BaseClockFrame {

    public DigitalClock() {
        super();

        JPanel timePanel = new JPanel(new GridLayout(3, 1));
        timePanel.setOpaque(false);

        localTimeLabel = createTimeLabel();
        foreignTimeLabel = createTimeLabel();
        dateLabel = createTimeLabel();

        timePanel.add(localTimeLabel);
        timePanel.add(foreignTimeLabel);
        timePanel.add(dateLabel);

        panel.add(timePanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setBackground(Color.DARK_GRAY);

        JButton toggleButton = new JButton("Toggle Format");
        toggleButton.addActionListener(e -> is24HourFormat = !is24HourFormat);

        JButton colorButton = new JButton("Change Background");
        colorButton.addActionListener(e -> changeBackgroundColor());

        JButton textColorButton = new JButton("Change Text Color");
        textColorButton.addActionListener(e -> changeTextColor());

        timeZoneComboBox = new JComboBox<>(timeZones);
        timeZoneComboBox.setSelectedItem("Asia/Kolkata");

        fontComboBox = new JComboBox<>(fontStyles);
        fontComboBox.setSelectedItem("Arial");
        fontComboBox.addActionListener(this::changeFontStyle);

        backgroundFormatComboBox = new JComboBox<>(backgroundModes);
        backgroundFormatComboBox.setSelectedItem("Random Colors");

        controlPanel.add(toggleButton);
        controlPanel.add(new JLabel("Time Zone:"));
        controlPanel.add(timeZoneComboBox);
        controlPanel.add(new JLabel("Watch Face:"));
        controlPanel.add(fontComboBox);
        controlPanel.add(new JLabel("Background Mode:"));
        controlPanel.add(backgroundFormatComboBox);
        controlPanel.add(colorButton);
        controlPanel.add(textColorButton);

        panel.add(controlPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DigitalClock clock = new DigitalClock();
            clock.setVisible(true);
        });
    }
}
