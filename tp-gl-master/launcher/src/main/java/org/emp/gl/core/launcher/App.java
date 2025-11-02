package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.HorlogeGUI;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

import javax.swing.SwingUtilities;

/**
 * Application launcher for the timer service
 *
 */
public class App {

    public static void main(String[] args) {
        // Create timer service
        TimerService timerService = new DummyTimeServiceImpl();
        
        // Create console clock
        Horloge horloge = new Horloge("Num 1", timerService);
        
        // Create GUI clock
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                HorlogeGUI horlogeGUI = new HorlogeGUI("Horloge GUI", timerService);
            }
        });
        
        // Keep the application running
        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
