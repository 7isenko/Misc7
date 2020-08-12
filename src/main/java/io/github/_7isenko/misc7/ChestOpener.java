package io.github._7isenko.misc7;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryHolder;

public class ChestOpener implements Listener {

    // WallSign and ItemFrame implement different Directional interfaces

    @EventHandler(ignoreCancelled = true)
    public void onSignInteract(PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && !event.getPlayer().isSneaking() && checkSign(block)) {
            WallSign sign = (WallSign) block.getState().getBlockData();
            Block attached = block.getRelative(sign.getFacing().getOppositeFace());
            if (openHolderToPlayer(event.getPlayer(), attached))
                event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onFrameInteract(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof ItemFrame && !event.getPlayer().isSneaking()) {
            ItemFrame frame = (ItemFrame) event.getRightClicked();
            Block attached = frame.getLocation().getBlock().getRelative(frame.getAttachedFace());
            if (openHolderToPlayer(event.getPlayer(), attached))
                event.setCancelled(true);
        }
    }

    private boolean openHolderToPlayer(Player player, Block block) {
        if (block.getState() instanceof InventoryHolder) {
            InventoryHolder holder = (InventoryHolder) block.getState();
            player.openInventory(holder.getInventory());
            return true;
        }
        return false;
    }

    private boolean checkSign(Block block) {
        if (block == null)
            return false;
        String type = block.getType().toString();
        return type.contains("SIGN");
    }
}
