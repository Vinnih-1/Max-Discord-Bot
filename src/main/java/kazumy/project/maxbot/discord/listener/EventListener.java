package kazumy.project.maxbot.discord.listener;

import kazumy.project.maxbot.configuration.embed.TicketEmbedValue;
import kazumy.project.maxbot.configuration.menu.TicketMenuValue;
import kazumy.project.maxbot.discord.DiscordMain;
import lombok.val;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    private final DiscordMain instance;

    public EventListener(DiscordMain instance) {
        this.instance = instance;
    }

    @Override
    public void onSelectMenuInteraction(@NotNull SelectMenuInteractionEvent event) {
        instance.getInteractionManager().getInteractionList()
                .stream()
                .filter(interaction -> interaction.getId().equals(event.getSelectMenu().getId()))
                .findAny()
                .ifPresent(interaction -> ((InteractionService<SelectMenuInteractionEvent>)interaction).execute(event));
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        instance.getInteractionManager().getInteractionList()
                .stream()
                .filter(interaction -> event.getComponentId().contains(interaction.getId()))
                .findAny()
                .ifPresent(interaction -> ((InteractionService<ButtonInteractionEvent>)interaction).execute(event));
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        instance.getInteractionManager().getInteractionList()
                .stream()
                .filter(interaction -> event.getModalId().contains(interaction.getId()))
                .findAny()
                .ifPresent(interaction -> ((InteractionService<ModalInteractionEvent>)interaction).execute(event));
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        val message = event.getMessage().getContentRaw();
        if (!event.getMember().hasPermission(Permission.ADMINISTRATOR)) return;
        if (!message.equals("@ticketmenu")) return;
        event.getMessage().delete().queue();
        event.getChannel().sendMessageEmbeds(TicketEmbedValue.instance().toEmbed())
                .addActionRow(TicketMenuValue.instance().toMenu("ticket"))
                .queue();
    }
}
