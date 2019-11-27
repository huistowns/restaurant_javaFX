package mainClasses;

import com.telesign.MessagingClient;
import com.telesign.RestClient;

public class SendSMS {

    public static void main(String[] args) {

        String customerId = "4D246DE9-8943-4D4D-8192-E2BFD42E98B2";
        String apiKey = "xR6EFdy5TCRvU4W+Hb/SSZnfgkQKERYp/+SAu9l6r0D/ZhyElKbk8ufXpw3zR743Eaf6JBfvNkTvJFjudduOkA==";
        String phoneNumber = "77762884955";
        String message = "1111";
        String messageType = "ARN";

        try {
            MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
            RestClient.TelesignResponse telesignResponse = messagingClient.message(phoneNumber, message, messageType, null);
            System.out.println("Your reference id is: "+telesignResponse.json.get("reference_id"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}