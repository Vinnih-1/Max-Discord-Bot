package kazumy.project.maxbot.discord.listener.interactions.modal;

import kazumy.project.maxbot.configuration.basic.CommandValue;
import kazumy.project.maxbot.discord.listener.InteractionService;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import org.bukkit.Bukkit;

import java.awt.*;

public class NicknameModalInteraction extends InteractionService<ModalInteractionEvent> {

    public NicknameModalInteraction() {
        super("nickname");
    }

    @Override
    public void execute(ModalInteractionEvent event) {
        val values = event.getInteraction().getValues().get(0);
        val nickname = values.getAsString();
        val item = values.getId();
        val isOnline = Bukkit.getServer().getOnlinePlayers().stream()
                .anyMatch(player -> player.getName().equals(nickname));

        if (!isOnline && !item.equals("unban")) {
            event.deferReply().addEmbeds(new EmbedBuilder()
                            .setColor(Color.RED)
                            .setDescription(":warning: **|** Você precisa estar online no servidor para fazer isto!")
                            .build())
                    .queue();
            return;
        }
        switch (item) {
            case "basic":
                Bukkit.getConsoleSender().sendMessage(String.format(CommandValue.get(CommandValue::vip),
                        nickname, item));
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format(CommandValue.get(CommandValue::vip),
                        nickname, item));
                break;
            case "advanced":
                Bukkit.getConsoleSender().sendMessage(String.format(CommandValue.get(CommandValue::vip),
                        nickname, item));
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format(CommandValue.get(CommandValue::vip),
                        nickname, item));
                break;
            case "spectral":
                Bukkit.getConsoleSender().sendMessage(String.format(CommandValue.get(CommandValue::vip),
                        nickname, item));
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format(CommandValue.get(CommandValue::vip),
                        nickname, item));
                break;
            case "unban":
                Bukkit.getConsoleSender().sendMessage(String.format(CommandValue.get(CommandValue::unban),
                        nickname));
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format(CommandValue.get(CommandValue::unban),
                        nickname));
                break;
            case "cash":
                MessageHistory.getHistoryFromBeginning(event.getChannel()).queue(success -> success.getRetrievedHistory().stream()
                        .filter(message -> !message.getEmbeds().isEmpty())
                        .filter(message -> message.getAuthor().getId().equals(event.getJDA().getSelfUser().getId()))
                        .map(message -> message.getEmbeds().get(0).getDescription())
                        .filter(message -> message.contains("Você está adquirindo"))
                        .map(message -> message.split(" ")[5])
                        .findFirst().ifPresent(cash -> {
                            Bukkit.getConsoleSender().sendMessage(String.format(CommandValue.get(CommandValue::cash),
                                    nickname, cash));
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), String.format(CommandValue.get(CommandValue::cash),
                                    nickname, cash));
                        }));
                break;
        }
        event.getMessage().delete().queue();
        event.deferReply().addEmbeds(new EmbedBuilder()
                        .setColor(Color.GREEN)
                        .setDescription(String.format(":white_check_mark: **|** Perfeito! Você efetuou esta compra para o jogador **%s**", nickname))
                        .build())
                .queue();
    }
}
