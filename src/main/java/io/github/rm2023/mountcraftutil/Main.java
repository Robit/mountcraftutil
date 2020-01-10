package io.github.rm2023.mountcraftutil;

import java.util.LinkedHashMap;

import org.bukkit.command.ProxiedCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.jorelali.commandapi.api.CommandAPI;
import io.github.jorelali.commandapi.api.CommandPermission;
import io.github.jorelali.commandapi.api.arguments.Argument;
import io.github.jorelali.commandapi.api.arguments.EntitySelectorArgument;
import io.github.jorelali.commandapi.api.arguments.EntitySelectorArgument.EntitySelector;

public class Main extends JavaPlugin {
   
    @Override
    public void onLoad() {
    	LinkedHashMap<String, Argument> mountArgs = new LinkedHashMap<String, Argument>();
    	mountArgs.put("entity", new EntitySelectorArgument(EntitySelector.ONE_ENTITY));
    	CommandAPI.getInstance().register("mount", CommandPermission.fromString("mountcraftutil.mount"), mountArgs, (sender, args) -> {
    		Entity toMount = (Entity) args[0];
    		if(sender instanceof Entity && !((Entity) sender).equals(toMount))
    		{
    			toMount.addPassenger((Entity) sender);
    		}
    		if(sender instanceof ProxiedCommandSender && ((ProxiedCommandSender) sender).getCallee() instanceof Entity && !((Entity) ((ProxiedCommandSender) sender).getCallee()).equals(toMount))
    		{
    			toMount.addPassenger((Entity) ((ProxiedCommandSender) sender).getCallee());
    		}
    	});
    	
    	CommandAPI.getInstance().register("crafting", CommandPermission.fromString("mountcraftutil.crafting"), new LinkedHashMap<String, Argument>(), (sender, args) -> {
    		if(sender instanceof HumanEntity)
    		{
    			((HumanEntity) sender).openWorkbench(null, true);
    		}
    		if(sender instanceof ProxiedCommandSender && ((ProxiedCommandSender) sender).getCallee() instanceof HumanEntity)
    		{
    			((HumanEntity) ((ProxiedCommandSender) sender).getCallee()).openWorkbench(null, true);
    		}
    	});
    }
}

