package kazumy.project.maxbot.discord.listener;

import kazumy.project.maxbot.discord.listener.interactions.button.NicknameButtonInteraction;
import kazumy.project.maxbot.discord.listener.interactions.button.TermsAgreeButtonInteraction;
import kazumy.project.maxbot.discord.listener.interactions.button.TermsDisagreeButtonInteraction;
import kazumy.project.maxbot.discord.listener.interactions.menu.*;
import kazumy.project.maxbot.discord.listener.interactions.modal.NicknameModalInteraction;
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
        interactionList.add(new ServicesMenuInteraction());
        interactionList.add(new VipMenuInteraction());
        interactionList.add(new CashMenuInteraction());
        interactionList.add(new UnbanMenuInteraction());
        interactionList.add(new NicknameButtonInteraction());
        interactionList.add(new NicknameModalInteraction());
    }
}
