package onl.devin.mc_particles;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Trajectory {

    private Location locCurrent;
    private Vector dirCurrent;
    private Player player;
    private Location locPlayerInitial;
    private Vector dirPlayerInitial;
    private TrajectoryType trajectoryType;
    private double blocksTraveled;
    private double blocksPerTick;

    public Trajectory(Player player, TrajectoryType trajectoryType) {
        this.trajectoryType = trajectoryType;
        this.player = player;
        locCurrent = player.getLocation();
        dirCurrent = locCurrent.getDirection();
        locPlayerInitial = locCurrent.clone();
        dirPlayerInitial = locCurrent.getDirection().clone();
        blocksTraveled = 0;
        blocksPerTick = 0.1;
    }

    public Trajectory(Player player, TrajectoryType trajectoryType, double blocksPerTick) {
        this(player, trajectoryType);
        this.blocksPerTick = blocksPerTick;
    }

    public void update() {
        blocksTraveled += blocksPerTick;
        trajectoryType.update(this);
    }

    public double getBlocksTraveled() {
        return blocksTraveled;
    }

    public double getBlocksPerTick() {
        return blocksPerTick;
    }

    public Location getLocation() {
        return locCurrent.clone();
    }

    public Vector getDirection() {
        return dirCurrent.clone();
    }

    public Location getLocPlayerInitial() {
        return locPlayerInitial.clone();
    }

    public Vector getDirPlayerInitial() {
        return dirPlayerInitial.clone();
    }

    public Player getPlayer() {
        return player;
    }

    protected void setLocation(Location location) {
        this.locCurrent = location;
    }

    protected void setDirection(Vector direction) {
        this.dirCurrent = direction;
    }

}
