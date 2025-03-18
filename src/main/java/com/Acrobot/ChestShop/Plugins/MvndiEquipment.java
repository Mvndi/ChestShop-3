package com.Acrobot.ChestShop.Plugins;

import com.Acrobot.ChestShop.ChestShop;
import com.Acrobot.ChestShop.Events.ItemParseEvent;
import com.Acrobot.ChestShop.Events.ItemStringQueryEvent;
import net.mvndicraft.mvndiequipment.ItemManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import static com.Acrobot.Breeze.Utils.StringUtil.getMinecraftStringWidth;

public class MvndiEquipment implements Listener {
    @EventHandler(priority = EventPriority.LOW)
    public void onItemParse(ItemParseEvent event) {
        if (event.getItem() == null) {
            ItemStack item = ItemManager.getInstance().create(event.getItemString(), 1);
            if (item != null) {
                event.setItem(item);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onItemStringQuery(ItemStringQueryEvent event) {
        ItemStack item = event.getItem();
        ItemManager itemManager = ItemManager.getInstance();
        if (!itemManager.isItem(item)) {
            return;
        }

        String id = itemManager.getId(item);
        if (event.getMaxWidth() > 0) {
            int width = getMinecraftStringWidth(id);
            if (width > event.getMaxWidth()) {
                ChestShop.logDebug("Can't use MvndiEquipment alias " + id + " as it's width (" + width + ") was wider than the allowed max width of " + event.getMaxWidth());
                return;
            }
        }
        event.setItemString(id);
    }
}
