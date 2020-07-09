package io.github._7isenko.misc7;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;

public class SignChestOpenListener implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onSignInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && block != null && checkSign(block)) {
            WallSign sign = (WallSign) block.getState().getBlockData();
            Block attached = block.getRelative(sign.getFacing().getOppositeFace());
            if (attached.getType() == Material.CHEST) {
                InventoryHolder chest = (InventoryHolder) attached.getState();
                event.getPlayer().openInventory(chest.getInventory());
                event.setCancelled(true);
            }

        }
    }

    private boolean checkSign(Block block) {
        String type = block.getType().toString();
        return type.contains("SIGN");
    }
}
