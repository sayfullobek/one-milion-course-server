package it.revo.onemilioncourse;

import com.squareup.okhttp.*;

import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"bulkId\":\"BULK-ID-123-xyz\",\"messages\":[{\"callbackData\":\"DLR callback data\",\"destinations\":[{\"messageId\":\"MESSAGE-ID-123-xyz\",\"to\":\"+998918103246\"},{\"to\":\"+998918103246\"}],\"flash\":false,\"from\":\"InfoSMS\",\"intermediateReport\":true,\"language\":{\"languageCode\":\"TR\"},\"notifyContentType\":\"application/json\",\"notifyUrl\":\"https://xlr9je.api.infobip.com/sms/2/text/advanced\",\"text\":\"Artık Ulusal Dil Tanımlayıcısı ile Türkçe karakterli smslerinizi rahatlıkla iletebilirsiniz.\",\"transliteration\":\"TURKISH\",\"validityPeriod\":720},{\"deliveryTimeWindow\":{\"days\":[\"MONDAY\",\"TUESDAY\",\"WEDNESDAY\",\"THURSDAY\",\"FRIDAY\",\"SATURDAY\",\"SUNDAY\"],\"from\":{\"hour\":6,\"minute\":0},\"to\":{\"hour\":15,\"minute\":30}},\"destinations\":[{\"to\":\"+998918103246\"}],\"from\":\"+998918103246\",\"sendAt\":\"2021-08-25T16:00:00.000+0000\",\"text\":\"A long time ago, in a galaxy far, far away... It is a period of civil war. Rebel spaceships, striking from a hidden base, have won their first victory against the evil Galactic Empire.\"}],\"tracking\":{\"track\":\"SMS\",\"type\":\"MY_CAMPAIGN\"}}");
        Request request = new Request.Builder()
                .url("https://xlr9je.api.infobip.com/sms/2/text/advanced")
                .method("POST", body)
                .addHeader("Authorization", "App" + "b23b9eb1e1345f21f15fee37ebc6e413-c3aa26d9-f23b-4cfb-875b-cb64b5ccf5e4")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println(response);
//        OkHttpClient client = new OkHttpClient();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"messages\":[{\"destinations\":[{\"to\":\"+998936381008\"}],\"from\":\"Group UTeam\",\"text\":\"salom\"}]}");
//        Request request = new Request.Builder()
//                .url("https://xlr9je.api.infobip.com/sms/2/text/advanced")
//                .method("POST", body)
//                .addHeader("Authorization", "App " + "b23b9eb1e1345f21f15fee37ebc6e413-c3aa26d9-f23b-4cfb-875b-cb64b5ccf5e4")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json")
//                .build();
//        Response response = client.newCall(request).execute();
//        System.out.println(response);
    }
}
