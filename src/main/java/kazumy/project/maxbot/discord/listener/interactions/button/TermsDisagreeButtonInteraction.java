package kazumy.project.maxbot.discord.listener.interactions.button;

import kazumy.project.maxbot.discord.listener.InteractionService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class TermsDisagreeButtonInteraction extends InteractionService<ButtonInteractionEvent> {

    public TermsDisagreeButtonInteraction() {
        super("terms-disagree");
    }

    @Override
    public void execute(ButtonInteractionEvent event) {
        event.deferReply(true).addEmbeds(new EmbedBuilder()
                        .setColor(Color.GREEN)
                        .setDescription(":x: **|** Você não concordou com os termos, o ticket será fechado em `3` segundos.")
                .build()).queue();
        event.getChannel().delete().queueAfter(3, TimeUnit.SECONDS);
    }
}
