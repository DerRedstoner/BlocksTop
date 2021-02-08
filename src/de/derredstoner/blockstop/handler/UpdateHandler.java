package de.derredstoner.blockstop.handler;

import de.derredstoner.blockstop.BlocksTop;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateHandler {

    public static void startUpdating() {
        new BukkitRunnable() {
            @Override
            public void run() {
                BlockHandler.calculateTopBroken();
            }
        }.runTaskTimer(BlocksTop.getInstance(), 60L, 6000L);
    }

}
