package io.github._7isenko.misc7;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Openable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class DoorClickListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (event.getAction() == Action.LEFT_CLICK_BLOCK && block != null && checkDoor(block)) {
            Openable door = (Openable) block.getState().getBlockData();
            if (door.isOpen()) {
                door.setOpen(false);
                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_WOODEN_DOOR_CLOSE, 1, 1);
            } else {
                door.setOpen(true);
                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_WOODEN_DOOR_OPEN, 1, 1);
            }
            block.setBlockData(door);
            block.getState().update();
        }
    }
    private boolean checkDoor(Block block) {
        String type = block.getType().toString();
        return type.contains("DOOR") && !type.contains("TRAP") && !type.contains("IRON");
    }
}
