package mainClasses;

import com.telesign.MessagingClient;
import com.telesign.RestClient;

public class SendSMS {

    public static void SendSMSManager(String name, String address, String contact) {

        String customerId = "92FADF21-B2A5-42E8-8BAE-51F3DBC56310";
        String apiKey = "vYMsWyvRgy1NWWktZRRiklUigWZoEln/Uim1LqT54EfQv2zpS33o27KzxuEtKftLXCFBlFlPM/bL92JhUqkhvg==";
        String phoneNumber = "77761534476";
        String message = "Имя: " + name + "\n" + "Адресс: " + address + "\n" + "Контакт: " + address;
        String messageType = "ARN";

        try {
            MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
            RestClient.TelesignResponse telesignResponse = messagingClient.message(phoneNumber, message, messageType, null);
            System.out.println("Your reference id is: "+ "35BADF1E5DAC0D689190648F0B898FF4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
  