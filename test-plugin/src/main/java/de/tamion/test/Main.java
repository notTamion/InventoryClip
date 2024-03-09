package de.tamion.test;

import de.tamion.inventoryclip.Page;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Bukkit.getPluginManager().registerEvents(this, this);
        Page.create("Hello", 1, this)
                .button(new ItemStack(Material.BREAD), event -> System.out.println("Hello"), 4)
                .show(Bukkit.getPlayer("Tamion"));
    }
}