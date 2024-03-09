package de.tamion.inventoryclip;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * A Page that contains an Inventory.
 */
public interface Page extends InventoryHolder {

    /**
     * Creates a new page.
     *
     * @param name name of the page
     * @param rows amount of rows of the page
     * @param plugin your plugin instance
     * @return a reference to the newly created page
     */
    @NotNull
    @Contract("_, _, _ -> new")
    static Page create(@NotNull String name, int rows, @NotNull JavaPlugin plugin) {
        return new PageImpl(name, rows, plugin);
    }

    /**
     * Creates a new page.
     *
     * @param name name of the page
     * @param type type of the page
     * @param plugin your plugin instance
     * @return a reference to the newly created page
     */
    @NotNull
    @Contract("_, _, _ -> new")
    static Page create(@NotNull String name, InventoryType type, @NotNull JavaPlugin plugin) {
        return new PageImpl(name, type, plugin);
    }

    /**
     * Creates a new button in this page.
     *
     * @param itemStack the itemstack that will be used
     * @param clickLogic the logic executed when a player clicks the item
     * @param position the position of the button in the page
     * @return a reference to this object
     */
    @NotNull
    @Contract("_, _, _ -> this")
    Page button(@NotNull ItemStack itemStack, @NotNull ButtonLogic clickLogic, int position);

    /**
     *
     * @param itemStack the itemstack that will be used
     * @param position the position of the info in the page
     * @return a reference to this object
     */
    @NotNull
    @Contract("_, _ -> this")
    Page info(@NotNull ItemStack itemStack, int position);

    /**
     * Shows the page to the specified player.
     *
     * @param player the player to show the page to
     * @return a reference to this object
     */
    @NotNull
    @Contract("_, -> this")
    Page show(@NotNull Player player);
}