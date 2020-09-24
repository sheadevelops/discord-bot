import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import commands.ChannelMute;
import commands.ChannelUnmute;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class Bot {
    public static void main(String args[]) throws Exception {
        // Add your developer token from Discord to connect
        JDA jda = JDABuilder.createDefault("token").build();

        CommandClientBuilder builder = new CommandClientBuilder();
        // Add the bot client id
        builder.setOwnerId("clientid");
        builder.setPrefix("!");
        builder.setHelpWord("help");
        builder.addCommand(new ChannelMute());
        builder.addCommand(new ChannelUnmute());

        CommandClient client = builder.build();
        jda.addEventListener(client);

//        jda.addEventListener(new ChannelMute());

    }
}
