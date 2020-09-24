package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;

import java.awt.*;
import java.util.List;

public class ChannelUnmute extends Command {

    public ChannelUnmute() {
        this.name = "unmute";
        this.aliases = new String[]{"u"};
        this.help = "Unmutes everyone in the voice channel.";
    }

    @Override
    protected void execute(CommandEvent event) {
        Guild guild = event.getGuild();
        Member author = guild.getMember(event.getMember().getUser());

        if(author.hasPermission(Permission.ADMINISTRATOR)) {

            VoiceChannel vc = event.getMember().getVoiceState().getChannel();
            if (vc == null) {
                event.getChannel().sendMessage("You are not connected to a voice channel!").queue();
                return;
            }

            List<Member> members = vc.getMembers();
            Member member = null;

            EmbedBuilder eb = new EmbedBuilder();
            eb.setColor(Color.CYAN);
            eb.setAuthor(event.getGuild().getName());
            //        eb.addField("Server Owner: ", event.getGuild().getOwner().getEffectiveName(), true);
            eb.setDescription("**Unmuting Voice Channel Users:** \n");
            for (int i = 0; i < members.size(); i++) {
                member = members.get(i);
                member.mute(false).queue();
                String name = member.getUser().getName();
                eb.appendDescription(name + "\n");
            }

            event.getChannel().sendMessage(eb.build()).queue();
        } else {
            event.getChannel().sendMessage(author.getEffectiveName() + " does not have the requirements for this command.").queue();
        }
    }
}
