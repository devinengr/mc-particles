package onl.devin.mc_particles;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Trajectory {

    private Location location;
    private Vector direction;
    private Player player;
    private TrajectoryType trajectoryType;

    public Trajectory(Player player, TrajectoryType trajectoryType) {
        this.trajectoryType = trajectoryType;
        this.player = player;
        location = player.getLocation();
        direction = player.getLocation().getDirection();
    }

    public void update() {
        trajectoryType.update(this);
    }

    public Location getLocation() {
        return location.clone();
    }

    public Vector getDirection() {
        return direction.clone();
    }

    public Player getPlayer() {
        return player;
    }

    protected void setLocation(Location location) {
        this.location = location;
    }

    protected void setDirection(Vector direction) {
        this.direction = direction;
    }

}
