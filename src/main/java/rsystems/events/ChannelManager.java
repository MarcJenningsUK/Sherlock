package rsystems.events;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.channel.text.TextChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.voice.VoiceChannelCreateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import rsystems.SherlockBot;

import java.util.ArrayList;

public class ChannelManager extends ListenerAdapter {

    /*

    AUTOMATICALLY APPLY MUTE ROLE TO NEW CHANNELS

     */
    public void onTextChannelCreate(TextChannelCreateEvent event){
        ArrayList<Permission> mutePerms = new ArrayList<>();
        mutePerms.add(Permission.MESSAGE_WRITE);
        mutePerms.add(Permission.MESSAGE_ADD_REACTION);

        try{
            Role muteRole = event.getGuild().getRoleById(SherlockBot.guildMap.get(event.getGuild().getId()).getMuteRoleID());
            try {
                assert muteRole != null;
                event.getChannel().createPermissionOverride(muteRole).setDeny(mutePerms).queue();
            } catch(NullPointerException e){
                //todo error
            }
        } catch(NullPointerException e){
            //todo error
        }
    }

    public void onVoiceChannelCreate(VoiceChannelCreateEvent event){
        ArrayList<Permission> mutePerms = new ArrayList<>();
        mutePerms.add(Permission.VOICE_STREAM);
        mutePerms.add(Permission.VOICE_SPEAK);

        try{
            Role muteRole = event.getGuild().getRoleById(SherlockBot.guildMap.get(event.getGuild().getId()).getMuteRoleID());
            try {
                assert muteRole != null;
                event.getChannel().createPermissionOverride(muteRole).setDeny(mutePerms).queue();
            } catch(NullPointerException e){
                //todo error
            }
        } catch(NullPointerException e){
            //todo error
        }
    }

}
