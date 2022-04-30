package Lincey.NeverMind.console;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Log {
    public static void Send(Player p, String st) {
        FileWriter fw = null;

        try {
            fw = new FileWriter(Main.log, true);
            BufferedWriter bw = new BufferedWriter(fw);
            Calendar c = Calendar.getInstance();
            int month = c.get(Calendar.MONTH) + 1;
            int day = c.get(Calendar.DATE);
            int minute = c.get(Calendar.MINUTE);
            int hour = c.get(Calendar.HOUR_OF_DAY);
            String date = "[" + day + "." + month + " | " + hour + ":" + minute + "]";
            bw.write(date + " " + p.getName() + " used: /" + st);
            bw.newLine();
            fw.flush();
            bw.close();
        } catch (IOException var18) {
            Bukkit.getLogger().info("§c|ERROR|§f - " + var18);
        } finally {
            try {
                assert fw != null;
                fw.close();
            } catch (IOException var17) {
                Bukkit.getLogger().info("§c|ERROR|§f - " + var17);
            }

        }

    }
}
