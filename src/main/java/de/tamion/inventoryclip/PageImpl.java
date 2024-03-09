package de.tamion.inventoryclip;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

class PageImpl implements Page {

    private final Inventory inventory;
    public final JavaPlugin plugin;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    private final HashMap<ItemStack, ButtonLogic> buttonLogics = new HashMap<>();

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
        this.buttonLogics.put(itemStack, clickLogic);
        this.inventory.setItem(position, itemStack);
        return this;
    }

    @Override
    public Page info(ItemStack itemStack, int position) {
        this.inventory.setItem(position, itemStack);
        return this;
    }

    @Override
    public Page move(ItemStack element, int newPosition) {
        this.inventory.remove(element);
        this.inventory.setItem(newPosition, element);
        return this;
    }

    @Override
    public Page move(int oldPosition, int newPosition) {
        this.inventory.setItem(newPosition, this.inventory.getItem(oldPosition));
        this.inventory.setItem(oldPosition, null);
        return this;
    }

    @Override
    public Page remove(ItemStack element) {
        this.inventory.remove(element);
        this.buttonLogics.remove(element);
        return this;
    }

    @Override
    public Page remove(int position) {
        this.buttonLogics.remove(this.inventory.getItem(position));
        this.inventory.setItem(position, ItemStack.empty());
        return this;
    }

    @Override
    public Page show(Player player) {
        player.openInventory(this.inventory);
        return this;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void handleClick(InventoryClickEvent e) {
        e.setCancelled(true);
        if (this.buttonLogics.containsKey(e.getCurrentItem()))
            this.buttonLogics.get(e.getCurrentItem()).onClick(e);
    }

    public void handleDrag(InventoryDragEvent e) {
        e.setCancelled(true);
    }

}
