package kazumy.project.maxbot.discord.listener;

import kazumy.project.maxbot.discord.DiscordMain;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
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
}
