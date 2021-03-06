/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import help.math.Position;
import help.ui.old.DoubleBufferedPanel;
import hotball.controller.AIController;
import hotball.controller.HumanController;
import hotball.logic.LogicCore;
import hotball.ui.KeyBinding;
import hotball.ui.UserInput;
import hotball.universe.GameObject;
import hotball.universe.player.Player;
import hotball.universe.ball.Ball;
import hotball.universe.player.Team;
import javax.swing.JFrame;

/**
 *
 * @author Dromlius
 */
public class TestFrame {

    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setSize(1024, 768);
        
        DoubleBufferedPanelImpl doubleBufferedPanelImpl = new DoubleBufferedPanelImpl(1024, 768);
        doubleBufferedPanelImpl.setFocusable(true);
        UserInput.create(doubleBufferedPanelImpl, KeyBinding.WASD,UserInput.ControlMode.ScreenRelational);
        HumanController.create();
        Player player = new Player(null, Team.Blue, HumanController.get(), new Position.DoublePosition(400, 400));
        Player ai = new Player(null, Team.Blue, new AIController(), new Position.DoublePosition(700, 200));
        Player ai2 = new Player(null, Team.Red, new AIController(), new Position.DoublePosition(500, 700));
        Ball.create(player);
        
        
        /*testFrame.addKeyListener( HumanController.get());
        testFrame.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    // System.out.println("pause");
                    GameLoop.get().pause();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    // System.out.println("resume");
                    GameLoop.get().resume();
                }
            }

        });*/
        testFrame.add(doubleBufferedPanelImpl);

        testFrame.setVisible(true);
        doubleBufferedPanelImpl.startDrawing();
        LogicCore.create();
        LogicCore.get().start();
    }

    private static class DoubleBufferedPanelImpl extends DoubleBufferedPanel {

        @SuppressWarnings("OverridableMethodCallInConstructor")
        public DoubleBufferedPanelImpl(int width, int height) {
            super(width, height);
          /*  addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    if (Ball.get().getState() instanceof Controlled) {
                        Player carrier = ((Controlled)Ball.get().getState()).getBallCarrier();
                        Ball.get().setState(new InAir(carrier, new Vector(1000, Ball.get().getPosition().angleBetween(e.getPoint()), null)));
                    }
                }

            });*/
        }

        @Override
        protected void internalDraw() {
            for(GameObject go:GameObject.ALL_GAMEOBJECTS){
                go.draw(graphics);
            }
        }
    }
}
