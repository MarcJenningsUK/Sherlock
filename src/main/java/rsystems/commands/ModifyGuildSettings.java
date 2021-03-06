package rsystems.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.PermissionException;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import rsystems.SherlockBot;

import static rsystems.SherlockBot.database;

public class ModifyGuildSettings extends ListenerAdapter {

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            //Ignore message.  BOT LAW #2 - DO NOT LISTEN TO OTHER BOTS
            return;
        }
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        //CHANGE PREFIX
        if (SherlockBot.commands.get(8).checkCommandMod(event.getMessage())) {
            try {
                database.putValue("GuildTable","Prefix","GuildID",event.getGuild().getIdLong(),args[1]);
                SherlockBot.guildMap.get(event.getGuild().getId()).setPrefix(args[1]);
                event.getMessage().addReaction("✅").queue();
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                try {
                    event.getMessage().addReaction("⚠").queue();
                } catch (Exception global_exception) {
                    global_exception.printStackTrace();
                }
            }
        }

        //CHANGE LOG CHANNEL ID
        if (SherlockBot.commands.get(7).checkCommandMod(event.getMessage())) {
            try {
                if(event.getGuild().getTextChannelById(args[1]).getId() != null){
                    database.putValue("GuildTable","LogChannelID","GuildID",event.getGuild().getIdLong(),Long.valueOf(args[1]));
                    SherlockBot.guildMap.get(event.getGuild().getId()).setLogChannelID(args[1]);
                    event.getMessage().addReaction("✅").queue();
                } else {
                    event.getMessage().addReaction("⚠").queue();
                }
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                try {
                    event.getMessage().addReaction("⚠").queue();
                } catch (Exception global_exception) {
                    global_exception.printStackTrace();
                }
            }
        }

        //CHANGE Embed filter level
        if (SherlockBot.commands.get(20).checkCommandMod(event.getMessage())) {
            try {
                SherlockBot.guildMap.get(event.getGuild().getId()).setEmbedFilter(Integer.parseInt(args[1]));
                event.getMessage().addReaction("✅").queue();
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                try {
                    event.getMessage().addReaction("⚠").queue();
                } catch (Exception global_exception) {
                    global_exception.printStackTrace();
                }
            }
        }

        //GET LOG CHANNEL ID
            if (SherlockBot.commands.get(6).checkCommandMod(event.getMessage())) {
            try{
                System.out.println("command called");
                event.getChannel().sendMessage("LogChannelID: " + SherlockBot.guildMap.get(event.getGuild().getId()).getLogChannelID());
            } catch(PermissionException e){

            }
        }
        /*
                        ASSIGNABLE ROLES CONFIGURATION
         */

        /*
        ADD ROLE TO ASSIGNABLE ROLES OF GUILDMAP
         */
        if (SherlockBot.commands.get(5).checkCommandMod(event.getMessage())) {
            try {
                if ((args.length == 3) && (event.getGuild().getRoleById(args[2]) != null)) {
                    // Add role to assignable roles of guildmap
                    SherlockBot.guildMap.get(event.getGuild().getId()).addAssignableRole(args[1], Long.valueOf(args[2]));

                    int rowUpdateCount = database.insertAssignableRole(event.getGuild().getIdLong(), args[1], Long.valueOf(args[2]));

                    if (rowUpdateCount > 0) {
                        // Success (1+ updated)
                        event.getMessage().addReaction("\uD83D\uDC4D").queue();
                    } else {
                        // Unsuccessful (0 rows updated)
                        event.getMessage().addReaction("\uD83D\uDC4E").queue();
                        database.logError(event.getGuild().getId(), "Failed to add assignable role");
                    }
                } else if (args.length < 3) {
                    event.getChannel().sendMessage("Not enough arguments supplied.").queue();
                }
            }catch (NullPointerException e) {
                event.getMessage().addReaction("⚠").queue();
            } catch (NumberFormatException e) {
                event.getChannel().sendMessage("What in tarnation is that>?").queue();
                event.getMessage().addReaction("⚠").queue();
            }
        }

        /*
        REMOVE ROLE FROM ASSIGNABLE ROLES OF GUILDMAP
         */
        if (SherlockBot.commands.get(4).checkCommandMod(event.getMessage())) {
            if (args.length >= 1) {
                if (SherlockBot.guildMap.get(event.getGuild().getId()).assignableRoleMap.containsKey(args[1])) {
                    SherlockBot.guildMap.get(event.getGuild().getId()).removeAssignableRole(args[1]);

                    int rowUpdateCount = database.removeAssignableRole(event.getGuild().getIdLong(),args[1]);

                    // Success (1+ updated)
                    if(rowUpdateCount > 0){
                        System.out.println(String.format("Guild: %s | Role CMD: %s | Count: %d",event.getGuild().getId(),args[1],rowUpdateCount));
                        event.getMessage().addReaction("\uD83D\uDC4D").queue();
                    } else {
                    // Unsuccessful (0 rows updated)
                        event.getMessage().addReaction("\uD83D\uDC4E").queue();
                        database.logError(event.getGuild().getId(),"Failed to remove assignable role");
                    }
                }
            }
        }

    }
}
