package de.tamion.inventoryclip;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Contains the logic that gets executed when a button is clicked by a player.
 */
public interface ButtonLogic {

    /**
     * Gets executed when a player clicks a button.
     *
     * @param event the event caused by the players click
     */
    void onClick(InventoryClickEvent event);
}
