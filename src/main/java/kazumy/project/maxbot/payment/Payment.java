package kazumy.project.maxbot.payment;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.net.MPSearchRequest;
import com.sendgrid.Client;
import com.sendgrid.Method;
import com.sendgrid.Request;
import io.nayuki.qrcodegen.QrCode;
import kazumy.project.maxbot.configuration.basic.PaymentValue;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.val;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Consumer;

@Getter
public class Payment {

    private static final Integer SEARCH_OFFSET = 0;
    private static final Integer SEARCH_LIMIT = 900;
    private PaymentData data;
    private String qrData;

    @SneakyThrows
    public Payment request(PaymentData data) {
        this.data = data;
        MercadoPagoConfig.setAccessToken(PaymentValue.get(PaymentValue::token));
        val client = new Client();
        val request = new Request();

        request.setMethod(Method.POST);
        request.setBaseUri("api.mercadopago.com");
        request.addHeader("Authorization", "Bearer " + PaymentValue.get(PaymentValue::token));
        request.addHeader("Content-Type", "application/json");
        request.setBody("{\n" +
                "  \"external_reference\": \"" + data.getId() + "\",\n" +
                "  \"title\": \" " + data.getTitle() + " \",\n" +
                "  \"total_amount\": " + data.getPrice().toString() + ",\n" +
                "  \"description\": \"" + data.getDescription() + "\",\n" +
                "  \"expiration_date\": \"" + LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).toString().split("\\.")[0] + ".042-04:00" + "\",\n" +
                "  \"items\": [\n" +
                "    {\n" +
                "      \"sku_number\": \"" + 1 + "\",\n" +
                "      \"category\": \"outros\",\n" +
                "      \"title\": \"" + data.getTitle() + "\",\n" +
                "      \"description\": \"" + data.getDescription() + "\",\n" +
                "      \"unit_price\": " + data.getPrice().toString() + ",\n" +
                "      \"quantity\": 1,\n" +
                "      \"unit_measure\": \"unit\",\n" +
                "      \"total_amount\": " + data.getPrice().toString() + "\n" +
                "    }\n" +
                "  ]\n" +
                "}");
        request.setEndpoint(String.format("/instore/orders/qr/seller/collectors/%s/pos/%s/qrs", PaymentValue.get(PaymentValue::user), PaymentValue.get(PaymentValue::pos)));
        System.out.println(request.getBody());
        val response = client.api(request);
        this.qrData = String.valueOf(((JSONObject) new JSONParser().parse(response.getBody())).get("qr_data"));
        return this;
    }

    public void wait(Consumer<PaymentData> success) {
        val timer = new Timer();
        val limit = LocalDateTime.now(ZoneId.of("America/Sao_Paulo")).plusMinutes(30L);
        timer.schedule(new TimerTask() {
            @Override
            @SneakyThrows
            public void run() {
                if (limit.isBefore(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")))) this.cancel();
                val client = new PaymentClient();
                val filters = (Map) new HashMap<String, Object>();
                filters.put("external_reference", data.getId());
                val search = MPSearchRequest.builder().offset(SEARCH_OFFSET).limit(SEARCH_LIMIT).filters(filters).build();
                val result = client.search(search);
                result.getResults().stream()
                        .filter(payment -> payment.getExternalReference().equals(data.getId()))
                        .findFirst().ifPresent(payment -> {
                            success.accept(data);
                            this.cancel();
                        });
            }
        }, 0L, 1000L * 10L);
    }

    @SneakyThrows
    public File getAsImage(String userId) {
        val qr = QrCode.encodeText(qrData, QrCode.Ecc.MEDIUM);
        val img = toImage(qr, 10, 4);
        val qrCodeImage = new File( userId + ".png");
        ImageIO.write(img, "png", qrCodeImage);

        return qrCodeImage;
    }

    public static BufferedImage toImage(QrCode qr, int scale, int border) {
        return toImage(qr, scale, border, 0xFFFFFF, 0x000000);
    }

    public static BufferedImage toImage(QrCode qr, int scale, int border, int lightColor, int darkColor) {
        Objects.requireNonNull(qr);
        if (scale <= 0 || border < 0)
            throw new IllegalArgumentException("Value out of range");
        if (border > Integer.MAX_VALUE / 2 || qr.size + border * 2L > Integer.MAX_VALUE / scale)
            throw new IllegalArgumentException("Scale or border too large");

        BufferedImage result = new BufferedImage((qr.size + border * 2) * scale, (qr.size + border * 2) * scale, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < result.getHeight(); y++) {
            for (int x = 0; x < result.getWidth(); x++) {
                boolean color = qr.getModule(x / scale - border, y / scale - border);
                result.setRGB(x, y, color ? darkColor : lightColor);
            }
        }
        return result;
    }
}
