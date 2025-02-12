package com.squishyslime;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.squishyslime.commands.InstaDeathCommand;

public class Main extends JavaPlugin implements Listener {
	public HashSet<UUID> players = new HashSet<>();
	public String currentVersion = "1.0.0";
	@Override
	public void onEnable() {
		new Metrics(this,24763);
		getCommand("instadeath").setExecutor(new InstaDeathCommand(this));
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("Plugin initalized and ready!");
	}
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if(event.getDamager().getType() == EntityType.PLAYER) {
			Player p = (Player) event.getDamager();
			if(players.contains(p.getUniqueId())) {
				Entity entity = event.getEntity();
				LivingEntity livingEntity = (LivingEntity) entity;
				livingEntity.setHealth(0);
			}
		}
	}
}
