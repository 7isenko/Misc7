package io.github._7isenko.misc7;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

public class CropBreakEvent extends BlockBreakEvent {

    public CropBreakEvent(Block theBlock, Player player) {
        super(theBlock, player);
    }
}
