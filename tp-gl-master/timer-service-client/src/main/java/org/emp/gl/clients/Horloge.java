package org.emp.gl.clients ; 

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService ; 


public class Horloge implements TimerChangeListener {

    String name; 
    TimerService timerService ; 


    public Horloge (String name, TimerService timerService) {
        this.name = name ; 
        this.timerService = timerService;
        
        // Register as a listener to the timer service
        if (timerService != null) {
            timerService.addTimeChangeListener(this);
        }

        System.out.println ("Horloge "+name+" initialized!") ;
    }

    public void afficherHeure () {
        if (timerService != null)
            System.out.println (name + " affiche " + 
                                String.format("%02d", timerService.getHeures()) +":"+
                                String.format("%02d", timerService.getMinutes())+":"+
                                String.format("%02d", timerService.getSecondes())) ;
    }
    
    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        // Update display when time changes
        if (SECONDE_PROP.equals(prop) || MINUTE_PROP.equals(prop) || HEURE_PROP.equals(prop)) {
            afficherHeure();
        }
    }

}
