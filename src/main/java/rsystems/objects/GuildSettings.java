package rsystems.objects;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;

public class GuildSettings {
    //Guild Uniques
    private Guild guild;
    private String prefix;
    private ArrayList<String> modRoles = new ArrayList<>();

    //Guild Objects
    public LogChannel logChannel;

    //Guild IDs
    private String muteRoleID;

    // CONSTRUCTOR
    public GuildSettings(String prefix, Guild guild){
        this.prefix = prefix;
        this.logChannel = new LogChannel(guild);
        this.guild = guild;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix(){
        return this.prefix;
    }

    public String getMuteRoleID() {
        return muteRoleID;
    }

    public void setMuteRoleID(String muteRoleID) {
        this.muteRoleID = muteRoleID;
    }

    public ArrayList<String> getModRoles() {
        return modRoles;
    }

    public void setModRoles(ArrayList<String> modRoles) {
        this.modRoles = modRoles;
    }

    public boolean addModRole(String roleID){
        try{
            Role roleCheck = this.guild.getRoleById(roleID);
            this.modRoles.add(roleID);
            return true;
        } catch(NullPointerException e){
            // do nothing...
        }
        return false;
    }

    public boolean removeModRole(String roleID){
        for(String modID:this.modRoles){
            if(modID.equalsIgnoreCase(roleID)){
               //todo complete this
                return true;
            }
        }
        return false;
    }

    public void logError(String message){
        System.out.println("Error from Guild: " + this.guild.getId() + "\n" + message + "\n");
    }
}