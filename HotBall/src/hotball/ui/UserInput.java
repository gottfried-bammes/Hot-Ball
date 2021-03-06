/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotball.ui;

import help.math.Position;
import help.math.Vector;
import hotball.universe.ball.Ball;
import hotball.universe.ball.Controlled;
import hotball.universe.player.Player;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Dromlius
 */
public class UserInput implements MouseListener, KeyListener, MouseMotionListener {

    private static UserInput singleton;
    private final ControlMode controlMode;

    public static enum ControlMode {

        MouseRelational, ScreenRelational;
    }

    public static void create(Component c, KeyBinding keyBinding, ControlMode controlMode) {
        if (singleton != null) {
            throw new RuntimeException("UserInput already created!");
        }
        singleton = new UserInput(keyBinding, controlMode);
        c.addMouseListener(singleton);
        c.addMouseMotionListener(singleton);
        c.addKeyListener(singleton);
    }

    public static UserInput get() {
        if (singleton == null) {
            throw new RuntimeException("UserInput not yet created!");
        }
        return singleton;
    }

    private final KeyBinding keyBinding;
    private final boolean[] pressedKeys;
    private final Position.DoublePosition mousePosition;

    private UserInput(KeyBinding keyBinding, ControlMode controlMode) {
        this.keyBinding = keyBinding;
        this.controlMode = controlMode;
        pressedKeys = new boolean[512];
        mousePosition = new Position.DoublePosition(0, 0);
    }

    public boolean isPressed(int keyCode) {
        return pressedKeys[keyCode];
    }

    public Vector getMovementVector() {
         Vector movement = new Vector((pressedKeys[keyBinding.getLeft()] ? (-1) : 0) + (pressedKeys[keyBinding.getRight()] ? (1) : 0),
                        (pressedKeys[keyBinding.getUp()] ? (-1) : 0) + (pressedKeys[keyBinding.getDown()] ? (1) : 0));
         movement.setLength(1);
         
         
        if(controlMode == ControlMode.MouseRelational){
            movement.rotate(Player.humanPlayer.getPosition().angleBetween(mousePosition)+Math.PI/2);
        }
        return movement;
    }

    public Position.DoublePosition getMousePosition() {
        return mousePosition;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!isPressed(KeyEvent.VK_SPACE)) {
            if (Ball.get().getState() instanceof Controlled) {
                Ball.get().throwBall(mousePosition);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys[e.getKeyCode()] = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePosition.set(e.getPoint());
    }
}
