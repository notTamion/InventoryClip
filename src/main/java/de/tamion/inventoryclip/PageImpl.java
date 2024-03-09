package de.tamion.inventoryclip;

import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

class PageImpl implements Page {

    private final Inventory inventory;
    public final JavaPlugin plugin;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final HashMap<Integer, ButtonLogic> buttonLogics = new HashMap<>();

    public PageImpl(String name, int rows, JavaPlugin plugin) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(this, rows*9, miniMessage.deserialize(name));
        EventManager.registerHandler(plugin);
    }

    public PageImpl(String name, InventoryType type, JavaPlugin plugin) {
        this.plugin = plugin;
        this.inventory = Bukkit.createInventory(this, type, miniMessage.deserialize(name));
        EventManager.registerHandler(plugin);
    }

    @Override
    public Page button(ItemStack itemStack, ButtonLogic clickLogic, int position) {
        this.buttonLogics.put(position, clickLogic);
        this.inventory.setItem(position, itemStack);
        return this;
    }

    @Override
    public Page info(ItemStack itemStack, int position) {
        this.inventory.setItem(position, itemStack);
        return this;
    }

    @Override
    public Page show(Player player) {
        player.openInventory(this.inventory);
        return this;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public void handleClick(InventoryClickEvent e) {
        e.setCancelled(true);
        if (this.buttonLogics.containsKey(e.getSlot()))
            this.buttonLogics.get(e.getSlot()).onClick(e);
    }

    public void handleDrag(InventoryDragEvent e) {
        e.setCancelled(true);
    }

}
