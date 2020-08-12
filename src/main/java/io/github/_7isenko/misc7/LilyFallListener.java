package io.github._7isenko.misc7;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class LilyFallListener implements Listener {
    private String permission = "misc7.lilyfall";

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerFall(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();
        if (!PermissionHelper.check(p, permission)) return;
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Location location = p.getLocation();
            if (location.getBlock().getType() == Material.LILY_PAD) {
                location.getBlock().breakNaturally();
                location.getWorld().playSound(location, Sound.BLOCK_GRASS_BREAK, 1, 1);
                e.setCancelled(true);
            }
        }
    }
}