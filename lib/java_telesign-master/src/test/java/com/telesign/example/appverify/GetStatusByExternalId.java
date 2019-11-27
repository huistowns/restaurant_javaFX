package com.telesign.example.appverify;

import com.telesign.AppVerifyClient;
import com.telesign.RestClient;

public class GetStatusByExternalId {

    public static void main(String[] args) {

        String customerId = "FFFFFFFF-EEEE-DDDD-1234-AB1234567890";
        String apiKey = "EXAMPLE----TE8sTgg45yusumoN6BYsBVkh+yRJ5czgsnCehZaOYldPJdmFh6NeX8kunZ2zU1YWaUw/0wV6xfw==";

        String externalId = "external_id";

        try {
            AppVerifyClient appVerifyClient = new AppVerifyClient(customerId, apiKey);
            RestClient.TelesignResponse telesignResponse = appVerifyClient.status(externalId, null);

            if (telesignResponse.ok) {
                System.out.println(String.format("AppVerify transaction with external_id %s has status code %s and status description %s.",
                        externalId,
                        telesignResponse.json.getAsJsonObject("status").get("code").getAsString(),
                        telesignResponse.json.getAsJsonObject("status").get("description").getAsString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}