package io.github._7isenko.misc7;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class CropsReplant implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null && event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;

        Block block = event.getClickedBlock();
        Material type = block.getType();
        if (Tag.CROPS.isTagged(type) && !type.toString().contains("STEM")) {
            Ageable crop = (Ageable) block.getBlockData();
            if (crop.getAge() == crop.getMaximumAge()) {
                block.breakNaturally();
                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_CROP_BREAK, 1, 1);
                // welp, it duplicates seeds
                block.setType(type);
            }
        }
    }
}
