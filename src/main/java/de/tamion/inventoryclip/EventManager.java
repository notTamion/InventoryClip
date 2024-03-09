package de.tamion.inventoryclip;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

class EventManager implements Listener {

    private static final List<JavaPlugin> registeredPlugins = new ArrayList<>();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getClickedInventory() != null && e.getClickedInventory().getHolder() instanceof PageImpl page)
            page.handleClick(e);
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof PageImpl page)
            page.handleDrag(e);
    }

    static void registerHandler(JavaPlugin plugin) {
        if (registeredPlugins.contains(plugin))
            return;
        Bukkit.getPluginManager().registerEvents(new EventManager(), plugin);
        registeredPlugins.add(plugin);
    }
}
