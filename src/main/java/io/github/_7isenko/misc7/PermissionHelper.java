package io.github._7isenko.misc7;

import org.bukkit.entity.Player;

public class PermissionHelper {
    public static boolean check(Player player, String permission) {
        return player.hasPermission(permission);
    }
}
