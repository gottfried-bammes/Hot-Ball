/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotball.universe.ball;

import help.math.Vector;
import hotball.universe.player.Player;

/**
 *
 * @author Dromlius
 */
public class InAir implements BallState{
    private final Player thrower;
   // private double power;

    public InAir(Player thrower, Vector direction) {
        this.thrower = thrower;
        Ball.get().setCurrentVelocity(direction);
    }
    
    @Override
    public void action(double timeDiff) {
        Ball.get().accelerate(timeDiff, Vector.NULL_VECTOR);
    }

    public Player getThrower() {
        return thrower;
    }
}
