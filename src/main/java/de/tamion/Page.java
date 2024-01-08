package de.tamion;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class Page {

    private Inventory inventory;

    /**
     * Creates a page with the passed variables.
     *
     * @param name name of the page
     * @param size size of the page
     */
    public Page(Component name, int size) {
        this.inventory = Bukkit.createInventory(null, size, name);
    }

    /**
     * Creates a page with the passed variables.
     *
     * @param name name of the page
     * @param type type of the page
     */
    public Page(Component name, InventoryType type) {
        this.inventory = Bukkit.createInventory(null, type, name);
    }
}
