/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotball.universe;

import help.math.Position;
import help.math.Vector;
import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Stack;

/**
 *
 * @author Dromlius
 */
public abstract class GameObject {
    public static final Collection<GameObject> ALL_GAMEOBJECTS;
    static{
        ALL_GAMEOBJECTS = new Stack<>();
    }
    
    
    private static int idCounter;
    
    private final int id;
    
    private final Position.DoublePosition position;
    private final double size;
    
    @SuppressWarnings("LeakingThisInConstructor")
    protected GameObject(Position.DoublePosition startingPos, double size){
        id = idCounter++;
        position = startingPos;
        this.size = size;
        ALL_GAMEOBJECTS.add(this);
    }

    public abstract void action(double timeDiff);
    
    public abstract void draw(Graphics2D g);
    
    public Position.DoublePosition getPosition() {
        return position;
    }

    public double getSize() {
        return size;
    }
    
    private Vector currentVelocity=new Vector();
    private final double DECAY_BASE = 30;

    public Vector getCurrentVelocity() {
        return currentVelocity;
    }

    public void setCurrentVelocity(Vector currentVelocity) {
        this.currentVelocity = currentVelocity;
    }
    protected abstract double getDECAY_FACTOR();
    
    public void accelerate(double timeDiff, Vector accDir){
        double decayRate = Math.pow(DECAY_BASE, getDECAY_FACTOR()*(-timeDiff));
        accDir.multiply(getMaxSpeed());
       currentVelocity.setdX(decayRate*(currentVelocity.getdX()-accDir.getdX())+accDir.getdX());
       currentVelocity.setdY(decayRate*(currentVelocity.getdY()-accDir.getdY())+accDir.getdY());
       getPosition().addVector(currentVelocity,timeDiff);
    }

    protected abstract double getMaxSpeed() ;
    
}
