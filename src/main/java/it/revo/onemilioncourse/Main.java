//package it.revo.onemilioncourse;
//
//import com.infobip.ApiClient;
//import com.infobip.ApiException;
//import com.infobip.ApiKey;
//import com.infobip.BaseUrl;
//import com.infobip.api.SmsApi;
//import com.infobip.model.SmsAdvancedTextualRequest;
//import com.infobip.model.SmsDestination;
//import com.infobip.model.SmsTextualMessage;
//
//import java.util.Collections;
//
///**
// * Send an SMS message by using Infobip API Java Client.
// * <p>
// * THIS CODE EXAMPLE IS READY BY DEFAULT. HIT RUN TO SEND THE MESSAGE!
// * <p>
// * Send SMS API reference: https://www.infobip.com/docs/api#channels/sms/send-sms-message
// * See Readme file for details.
// */
//public class Main {
//
//    private static final String BASE_URL = "https://mm2rvw.api.infobip.com";
//    private static final String API_KEY = "b158ba219bec56c1c091386698ccb06e-168a12ac-e947-45d2-82b9-a3443ce2c612";
//    private static final String RECIPIENT = "998912636571";
//
//    public static void main(String[] args) {
//        // Create the API client and the Send SMS API instances.
//        var apiClient = ApiClient.forApiKey(ApiKey.from(API_KEY))
//                .withBaseUrl(BaseUrl.from(BASE_URL))
//                .build();
//        var sendSmsApi = new SmsApi(apiClient);
//
//        // Create a message to send.
//        var smsMessage = new SmsTextualMessage()
//                .addDestinationsItem(new SmsDestination().to(RECIPIENT))
//                .text("salom gadaybachcha mashallah!");
//
//        // Create a send message request.
//        var smsMessageRequest = new SmsAdvancedTextualRequest()
//                .messages(Collections.singletonList(smsMessage));
//
//        try {
//            // Send the message.
//            var smsResponse = sendSmsApi.sendSmsMessage(smsMessageRequest).execute();
//            System.out.println("Response body: " + smsResponse);
//
//            // Get delivery reports. It may take a few seconds to show the above-sent message.
//            var reportsResponse = sendSmsApi.getOutboundSmsMessageDeliveryReports().execute();
//            System.out.println(reportsResponse.getResults());
//        } catch (ApiException e) {
//            System.out.println("HTTP status code: " + e.responseStatusCode());
//            System.out.println("Response body: " + e.rawResponseBody());
//        }
//    }
//}
