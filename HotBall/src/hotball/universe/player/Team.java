/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotball.universe.player;

import java.awt.Color;


/**
 *
 * @author Dromlius
 */
public enum Team {
    Red(Color.RED),Blue(Color.BLUE);
    
    private final Color color;

    private Team(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    
}
