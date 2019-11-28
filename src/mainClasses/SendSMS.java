package mainClasses;

import com.telesign.MessagingClient;
import com.telesign.RestClient;

public class SendSMS {

    public static String GetMessage(String name, String address, String contact) {
        return "Имя: " + name + "\n" + "Адрес: " + address + "\n" + "Мобильный телефон: " + contact;
    }

    public static void main (String[]args){
            String customerId = "92FADF21-B2A5-42E8-8BAE-51F3DBC56310";
            String apiKey = "vYMsWyvRgy1NWWktZRRiklUigWZoEln/Uim1LqT54EfQv2zpS33o27KzxuEtKftLXCFBlFlPM/bL92JhUqkhvg==";
            String phoneNumber = "77761534476";
            String message = "1111";
            String messageType = "Заказ на дом";

            try {
                MessagingClient messagingClient = new MessagingClient(customerId, apiKey);
                RestClient.TelesignResponse telesignResponse = messagingClient.message(phoneNumber, message, messageType, null);
                System.out.println("Your reference id is: " + telesignResponse.json.get("reference_id"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}