package kazumy.project.maxbot.discord.listener;

import kazumy.project.maxbot.discord.listener.interactions.button.TermsAgreeButtonInteraction;
import kazumy.project.maxbot.discord.listener.interactions.button.TermsDisagreeButtonInteraction;
import kazumy.project.maxbot.discord.listener.interactions.menu.TicketMenuInteraction;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class InteractionManager {

    @Getter private final Set<InteractionService<?>> interactionList = new HashSet<>();

    public final void initInteraction() {
        interactionList.add(new TicketMenuInteraction());
        interactionList.add(new TermsAgreeButtonInteraction());
        interactionList.add(new TermsDisagreeButtonInteraction());
    }
}
