package kazumy.project.maxbot.payment;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PaymentData {

    private String id, title, description;

    private BigDecimal price;
}
