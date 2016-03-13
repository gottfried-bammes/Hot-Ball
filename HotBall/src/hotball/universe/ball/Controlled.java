/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotball.universe.ball;

import hotball.universe.player.Player;

/**
 *
 * @author Inga
 */
public class Controlled implements BallState{
    
    private final Player ballCarrier;

    public Controlled(Player ballCarrier) {
        this.ballCarrier = ballCarrier;
    }

    public Player getBallCarrier() {
        return ballCarrier;
    }
   
    
    @Override
    public void action(double timeDiff) {
        Ball.get().getPosition().set(ballCarrier.getPosition());
    }
    
}
