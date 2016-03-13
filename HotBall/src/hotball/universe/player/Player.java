/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotball.universe.player;

import help.math.Position;
import hotball.controller.Controller;
import hotball.controller.HumanController;
import hotball.universe.GameObject;
import hotball.universe.ball.Ball;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Dromlius
 */
public class Player extends GameObject {

    public static Player humanPlayer;

    //  private final String name;
    private final Team team;
    
    private Controller controller;
    
    private double facing;
    
    private TackleZone tackleZone;
    
    public Player(String name, Team team, Controller controller, Position.DoublePosition startingPos) {
        super(startingPos, 24);
        // this.name = name;
        this.team = team;
        // this.facing = facing;
        setController(controller);
    }
    
    public Team getTeam() {
        return team;
    }
    
    public final void setController(Controller controller) {
        this.controller = controller;
        if (controller instanceof HumanController) {
            humanPlayer = this;
        }
    }
    
    public boolean isHuman() {
        return controller instanceof HumanController;
    }
    
    private static final BufferedImage[][] TEXTURES;

    static {
        TEXTURES = new BufferedImage[3][2];
        try {
            TEXTURES[0][0] = ImageIO.read(new File("res/player_B_N.png"));
            TEXTURES[0][1] = ImageIO.read(new File("res/player_B_W.png"));
            TEXTURES[1][0] = ImageIO.read(new File("res/player_R_N.png"));
            TEXTURES[1][1] = ImageIO.read(new File("res/player_R_W.png"));
            TEXTURES[2][0] = ImageIO.read(new File("res/player_Y_N.png"));
            TEXTURES[2][1] = ImageIO.read(new File("res/player_Y_W.png"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        int spriteColor = isHuman() ? 2 : ((team == Team.Blue) ? 0 : 1);
        BufferedImage texture = TEXTURES[spriteColor][Ball.get().isControlledBy(this) ? 1 : 0];
        AffineTransform tx = AffineTransform.getRotateInstance(facing+Math.PI/2, texture.getWidth() / 2, texture.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        g.drawImage(op.filter(texture, null), (int) (getPosition().getX() - getSize()), (int) (getPosition().getY() - getSize()), null);

        //   g.setColor(isHuman()?Color.GREEN:team.getColor());
        //  g.fillOval((int) (getPosition().getX() - getSize()), (int) (getPosition().getY() - getSize()), (int) (2 * getSize()), (int) (2 * getSize()));
        // g.setColor(Color.WHITE);
     //   g.drawLine(getPosition().getRoundX(), getPosition().getRoundY(),
            //    (int) (getPosition().getX() + getSize() * Math.cos(controller.getFacing(this))), (int) (getPosition().getY() + getSize() * Math.sin(controller.getFacing(this))));
    }
    
    @Override
    public void action(double timeDiff) {
        accelerate(timeDiff, controller.getMoveVector(this));
        facing = controller.getFacing(this);
    }

    @Override
    protected double getDECAY_FACTOR() {
        return 0.05;
    }

    @Override
    protected double getMaxSpeed() {
        return 200;
    }
    
}
