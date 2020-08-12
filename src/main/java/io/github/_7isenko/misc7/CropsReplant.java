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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
                Bukkit.getPluginManager().callEvent(new CropBreakEvent(block, event.getPlayer()));
                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_CROP_BREAK, 1, 1);
                crop.setAge(0);
                block.setBlockData(crop);
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onCropBreak(CropBreakEvent event) {
            Block crop = event.getBlock();
            event.setDropItems(false);
            ArrayList<ItemStack> drops = new ArrayList<>(crop.getDrops());
            drops.get(drops.size() - 1).setAmount(drops.get(drops.size() - 1).getAmount() - 1);
            for (ItemStack drop : drops) {
                if (drop.getAmount() != 0)
                crop.getWorld().dropItemNaturally(crop.getLocation(), drop);
            }
        }
}
