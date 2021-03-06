package rsystems.handlers;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.exceptions.PermissionException;
import rsystems.SherlockBot;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LogChannel {

    public void logAction(Guild guild, String action, Member violator, Member submitter){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(action)
                    .addField("Violator:",violator.getUser().getAsTag(),false)
                    .setFooter("Submitted by: " + submitter.getUser().getAsTag());

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logAction(Guild guild, String action, List<Member> violators, Member submitter){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            StringBuilder violatorNames = new StringBuilder();
            for(Member m:violators){
                violatorNames.append(m.getUser().getAsTag()).append(",");
            }

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(action)
                    .addField("Violators:",violatorNames.toString(),false)
                    .setFooter("Submitted by: " + submitter.getUser().getAsTag());

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logAction(Guild guild, String action, String note, List<Member> violators, Member submitter){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            StringBuilder violatorNames = new StringBuilder();
            for(Member m:violators){
                violatorNames.append(m.getUser().getAsTag()).append(",");
            }

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(action)
                    .setDescription(note)
                    .addField("Violators:",violatorNames.toString(),false)
                    .setFooter("Submitted by: " + submitter.getUser().getAsTag());

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logAction(Guild guild, String action, Member violator, User submitter){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(action)
                    .addField("Violators:",violator.getUser().getAsTag(),false)
                    .setFooter("Submitted by: " + submitter.getAsTag());

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logAction(Guild guild, String title, String description, Member violator, User submitter, int colorLevel){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(title)
                    .setDescription(description)
                    .addField("Violators:",violator.getUser().getAsTag(),false)
                    .setFooter("Submitted by: " + submitter.getAsTag());

            embedBuilder.setColor(getColor(colorLevel));

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            } finally{
                embedBuilder.clear();
            }
        }
    }

    public void logAction(Guild guild, String title, String description, Member violator){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(title)
                    .setDescription(description)
                    .addField("Violators:",violator.getUser().getAsTag(),false)
                    .setFooter("Submitted via BOT");

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logAction(Guild guild, String title, String description, Member violator, int colorLevel){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(title)
                    .setDescription(description)
                    .addField("Violators:",violator.getUser().getAsTag(),false)
                    .setFooter("Submitted via BOT");

            embedBuilder.setColor(getColor(colorLevel));

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            } finally {
                embedBuilder.clear();
            }
        }
    }

    public void logAction(Guild guild, String action, User violator){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(action)
                    .addField("Violators:",violator.getAsTag(),false);

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logAction(Guild guild, String action, User violator, int colorLevel){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle(action)
                    .addField("Violators:",violator.getAsTag(),false);
            embedBuilder.setColor(getColor(colorLevel));

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logInfraction(Guild guild, String reason, ArrayList<Member> violators, Member submitter){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            StringBuilder violatorName = new StringBuilder();
            StringBuilder violatorTag = new StringBuilder();
            StringBuilder violatorID = new StringBuilder();

            for(Member m: violators){
                violatorName.append(m.getEffectiveName()).append("\n");
                violatorTag.append(m.getUser().getAsTag()).append("\n");
                violatorID.append(m.getId()).append("\n");
            }

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Infraction Submitted")
                    .addField("Reason:",reason,false)
                    .addField("Violator Name:",violatorName.toString(),true)
                    .addField("Violator Tag",violatorTag.toString(), true)
                    .addField("Violator ID",violatorID.toString(),true)
                    .setFooter("Submitted by: " + submitter.getUser().getAsTag());
            embedBuilder.setColor(getColor(3));

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logMuteAction(Guild guild, String reason, User violator, Member submitter){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Muted User")
                    .addField("Reason:",reason, false)
                    .addField("Violators:",violator.getAsTag(),false)
                    .setFooter("Submitted by: " + submitter.getUser().getAsTag());
            embedBuilder.setColor(getColor(3));

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logMuteAction(Guild guild, String reason, User violator, Member submitter, int timeValue, String timeType){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Muted User")
                    .addField("Reason:",reason,false)
                    .addField("Violators:",violator.getAsTag(),true)
                    .addField("Time:", timeValue + " " + timeType, true)
                    .setFooter("Submitted by: " + submitter.getUser().getAsTag());
            embedBuilder.setColor(getColor(3));

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    public void logLanguageFilterAction(Guild guild, Message message, String triggerWord, Member violator){
        String channelLogID = SherlockBot.guildMap.get(guild.getId()).getLogChannelID();
        if(channelLogID != null){

            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Language filter infraction")
                    .setDescription("Inappropriate language was detected on this message: [Message Link]("+message.getJumpUrl() + ")")
                    .addField("User:",violator.getEffectiveName(),true)
                    .addField("User ID:",violator.getId(),true)
                    .addField("Trigger Word:",triggerWord,true);
            embedBuilder.setColor(getColor(1));

            try{
                guild.getTextChannelById(channelLogID).sendMessage(embedBuilder.build()).queue();
            } catch(NullPointerException | PermissionException e){

            }
        }
    }

    private Color getColor(int colorCode){
        switch(colorCode){
            case 0:
                return Color.GREEN;
            case 1:
                return Color.YELLOW;
            case 2:
                return Color.RED;
            case 3:
                return Color.CYAN;
            case 4:
                return Color.BLUE;
            default:
                break;
        }
        return null;
    }
}
