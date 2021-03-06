package rsystems.commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import rsystems.SherlockBot;
import rsystems.handlers.LogChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static rsystems.SherlockBot.bot;
import static rsystems.SherlockBot.database;

public class Infraction extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) {
            //Ignore message.  BOT LAW #2 - DO NOT LISTEN TO OTHER BOTS
            return;
        }

        if (event.isFromGuild()) {

            // WRITE INFRACTION COMMAND
            if (SherlockBot.commands.get(1).checkCommandMod(event.getMessage())) {

                // Moved inside the condition to remove unnecessary work
                String[] args = event.getMessage().getContentRaw().split("\\s+");
                
                if (args.length >= 2) {
                    ArrayList<Member> qualifiedMembers = new ArrayList<>();
                    qualifiedMembers.addAll(getMentionables(event.getGuild(), event.getMessage()));

                    String infractionReason = null;
                    if (qualifiedMembers.size() == 1) {
                        infractionReason = event.getMessage().getContentDisplay().substring(event.getMessage().getContentDisplay().indexOf(args[2]));
                    } else {
                        String lastMember = qualifiedMembers.get(qualifiedMembers.size() - 1).getEffectiveName();
                        infractionReason = event.getMessage().getContentDisplay().substring(event.getMessage().getContentDisplay().lastIndexOf(lastMember) + lastMember.length() + 1);
                    }

                    if (infractionReason.length() > 90) {
                        event.getChannel().sendMessage("Reason too long").queue();
                        return;
                    }

                    for (Member m : qualifiedMembers) {
                        database.insertInfraction(event.getGuild().getId(), m.getIdLong(), infractionReason, event.getMember().getIdLong());
                    }
                    event.getMessage().addReaction("\uD83D\uDEA8 ").queue();
                    LogChannel logChannel = new LogChannel();
                    logChannel.logInfraction(event.getGuild(), infractionReason, qualifiedMembers, event.getMember());
                }
            }
        }
    }

    // AUTOMATIC INFRACTION
    public void automaticInfraction(Guild guild, Member member) {
        database.insertInfraction(guild.getId(), member.getIdLong(), "Auto BoT Infraction", bot.getIdLong());

        LogChannel logChannel = new LogChannel();
        logChannel.logAction(guild, "Automatic Infraction", member, bot);
    }

    private ArrayList<Member> getMentionables(Guild guild, Message message) {
        ArrayList<Member> mentionables = new ArrayList<>();

        List<Member> mentioned = message.getMentionedMembers();
        if (mentioned.size() > 0) {
            mentionables.addAll(mentioned);
        } else {
            // Try to mute user using USERID
            try {
                String[] args = message.getContentRaw().split("\\s+");

                if ((args.length > 1) && (guild.getSelfMember().canInteract(Objects.requireNonNull(guild.getMemberById(args[1]))))) {
                    mentionables.add(guild.getMemberById(args[1]));
                }
            } catch (NullPointerException | NumberFormatException e) {

            }
        }

        return mentionables;
    }

}
