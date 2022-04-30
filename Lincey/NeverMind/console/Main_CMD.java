package Lincey.NeverMind.console;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Main_CMD implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String lebal, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + " §cPlayers only.");
            return true;
        } else {
            Player p = (Player) sender;
            if (!Main.config.getStringList("Players").contains(p.getName())) {
                p.sendMessage(Main.getPrefix() + " " + Main.config.getString("Messages.NoPermission").replace("&", "§"));
                return true;
            } else if (args.length == 0) {
                p.sendMessage(Main.getPrefix() + " " + Main.config.getString("Messages.Syntax").replace("&", "§"));
                return true;
            } else {
                StringBuilder st = new StringBuilder();

                for (String arg : args) {
                    st.append(arg).append(" ");
                }

                String command = st.toString().replace("/", "");
                Iterator<String> var8 = Main.config.getStringList("LockedCommands").iterator();

                String str;
                do {
                    if (!var8.hasNext()) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                        p.sendMessage(Main.getPrefix() + " " + Main.config.getString("Messages.Complete").replace("%cmd%", command).replace("&", "§"));
                        Log.Send(p, command);
                        return false;
                    }

                    str = (String) var8.next();
                } while (!command.toLowerCase().contains(str.toLowerCase()));

                p.sendMessage(Main.getPrefix() + " " + Main.config.getString("Messages.LockedCommand").replace("&", "§"));
                return true;
            }
        }
    }
}
