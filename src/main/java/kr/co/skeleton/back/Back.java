package kr.co.skeleton.back;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public final class Back extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        System.out.println("[Back] plugin Enabled");

    }

    @Override
    public void onDisable() {
        System.out.println("[Back] plugin Disabled");
    }

    HashMap<UUID, Integer> deathNum = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (label.equalsIgnoreCase("back")) {
            if (deathNum.containsKey(p.getUniqueId())) {
                Location spawn = p.getLastDeathLocation();
                p.teleport(spawn);
                deathNum.remove(p.getUniqueId());
            } else {
                p.sendMessage("사망한적이 없거나 이미 명령어를 사용했습니다.");
            }
        }
        return true;
    }

    @EventHandler
    public void ondeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        deathNum.put(p.getUniqueId(), 1);
    }
}
