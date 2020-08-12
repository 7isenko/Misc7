package io.github._7isenko.misc7;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Sign;
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
    private String permission = "misc7.chestopener";

    // WallSign and ItemFrame implement different Directional interfaces

    @EventHandler(ignoreCancelled = true)
    public void onSignInteract(PlayerInteractEvent event) {
        if (!PermissionHelper.check(event.getPlayer(), permission)) return;
        if (event.getClickedBlock() != null && event.getAction() == Action.RIGHT_CLICK_BLOCK && !event.getPlayer().isSneaking()) {
            Block block = event.getClickedBlock();
            Block attached = null;
            if (block.getBlockData() instanceof WallSign) {
                WallSign sign = (WallSign) block.getBlockData();
                attached = block.getRelative(sign.getFacing().getOppositeFace());
            } else if (block.getBlockData() instanceof Sign) {
                attached = block.getRelative(BlockFace.DOWN);
            }
            if (openHolderToPlayer(event.getPlayer(), attached))
                event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onFrameInteract(PlayerInteractEntityEvent event) {
        if (!PermissionHelper.check(event.getPlayer(), permission)) return;
        if (event.getRightClicked() instanceof ItemFrame && !event.getPlayer().isSneaking()) {
            ItemFrame frame = (ItemFrame) event.getRightClicked();
            Block attached = frame.getLocation().getBlock().getRelative(frame.getAttachedFace());
            if (openHolderToPlayer(event.getPlayer(), attached))
                event.setCancelled(true);
        }
    }

    private boolean openHolderToPlayer(Player player, Block block) {
        if (block == null) return false;
        if (block.getState() instanceof InventoryHolder) {
            InventoryHolder holder = (InventoryHolder) block.getState();
            player.openInventory(holder.getInventory());
            return true;
        }
        return false;
    }
}
