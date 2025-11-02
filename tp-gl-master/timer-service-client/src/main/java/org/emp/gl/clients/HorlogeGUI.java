package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * GUI version of the clock (Horloge)
 */
public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private String name;
    private TimerService timerService;
    private JLabel timeLabel;
    private JLabel titleLabel;

    public HorlogeGUI(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;

        // Register as a listener to the timer service
        if (timerService != null) {
            timerService.addTimeChangeListener(this);
        }

        initializeGUI();
        updateTimeDisplay();

        System.out.println("HorlogeGUI " + name + " initialized!");
    }

    private void initializeGUI() {
        setTitle("Horloge - " + name);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Set window size
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the window

        // Title label
        titleLabel = new JLabel(name, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(50, 50, 150));
        add(titleLabel, BorderLayout.NORTH);

        // Time label
        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
        timeLabel.setForeground(new Color(0, 150, 0));
        timeLabel.setBackground(new Color(0, 0, 0));
        timeLabel.setOpaque(true);
        timeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(timeLabel, BorderLayout.CENTER);

        // Handle window closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (timerService != null) {
                    timerService.removeTimeChangeListener(HorlogeGUI.this);
                }
                dispose();
            }
        });

        // Make the window visible
        setVisible(true);
    }

    private void updateTimeDisplay() {
        if (timerService != null && timeLabel != null) {
            String timeString = String.format("%02d:%02d:%02d",
                    timerService.getHeures(),
                    timerService.getMinutes(),
                    timerService.getSecondes());
            timeLabel.setText(timeString);
        }
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        // Update display when time changes
        if (SECONDE_PROP.equals(prop) || MINUTE_PROP.equals(prop) || HEURE_PROP.equals(prop)) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    updateTimeDisplay();
                }
            });
        }
    }

    public void afficherHeure() {
        updateTimeDisplay();
    }
}

