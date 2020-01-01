package xyz.fluxinc.fluxcore.inventory;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryChecker implements Listener {

    private JavaPlugin instance;
    private CheckExecutor executor;

    public InventoryChecker(JavaPlugin instance, CheckExecutor executor) {
        this.instance = instance;
        this.executor = executor;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryChange(InventoryClickEvent event) {
        instance.getServer().getScheduler().runTaskLater(instance, () -> executeCheck((Player) event.getWhoClicked()), 10L);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPickupEvent(EntityPickupItemEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) event.getEntity();
        instance.getServer().getScheduler().runTaskLater(instance, () -> executeCheck(player), 10L);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onDropEvent(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        instance.getServer().getScheduler().runTaskLater(instance, () -> executeCheck(player), 10L);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        executeCheck(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLeave(PlayerQuitEvent event) {
        executor.removeOnLeave(event.getPlayer());
    }

    private void executeCheck(Player whoClicked) {
        if (executor.getMaterial() == null || whoClicked.getInventory().contains(executor.getMaterial())) {
            for (ItemStack iStack : whoClicked.getInventory().getContents()) {
                if (executor.verifyItemStack(iStack)) {
                    executor.executeIfTrue(whoClicked);
                    return;
                }
            }
        }
        executor.executeIfFalse(whoClicked);
    }
}
