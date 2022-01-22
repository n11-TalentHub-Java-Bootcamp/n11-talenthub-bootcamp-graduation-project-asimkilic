package tr.gov.nvi.tckimlik.WS;

import com.asimkilic.loan.application.gen.service.BaseTurkishRepublicIdNoVerificationService;
import com.squareup.okhttp.*;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tr.gov.nvi.tckimlik.WS.exception.KpsServiceUnavailableException;

import java.io.IOException;

@Service
@NoArgsConstructor
public class KpsPublicSoapService implements BaseTurkishRepublicIdNoVerificationService {

    @Override
    public boolean verifyTurkishRepublicIdNo(String turkishRepublicIdNo, String firstName, String lastName, String yearOfBirth) {
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/soap+xml; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType,
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n  " +
                        "<soap12:Body>\n    " +
                        "<TCKimlikNoDogrula xmlns=\"http://tckimlik.nvi.gov.tr/WS\">\n     " +
                        " <TCKimlikNo>" + turkishRepublicIdNo + "</TCKimlikNo>\n     " +
                        " <Ad>" + firstName + "</Ad>\n     " +
                        " <Soyad>" + lastName + "</Soyad>\n    " +
                        " <DogumYili>" + yearOfBirth + "</DogumYili>\n   " +
                        " </TCKimlikNoDogrula>\n  " +
                        "</soap12:Body>\n</soap12:Envelope>");
        Request request = new Request.Builder()
                .url("https://tckimlik.nvi.gov.tr/Service/KPSPublic.asmx")
                .method("POST", body)
                .addHeader("Content-Type", "application/soap+xml; charset=utf-8")
                .build();
        try{

            Response response = client.newCall(request).execute();
            return convertStringResultToBoolean(response);
        }
        catch(Exception e){
           throw new KpsServiceUnavailableException("Service unavailable, try again later.");
        }


    }

    private boolean convertStringResultToBoolean(Response response) throws IOException {
        String result = response.body().string();

        String firstTag = "<TCKimlikNoDogrulaResult>";
        String secondTag = "</TCKimlikNoDogrulaResult>";
        int firstTagEndIndex = result.indexOf(firstTag) + firstTag.length();
        int secondTagStartIndex = result.indexOf(secondTag);
        String resultString = result.substring(firstTagEndIndex, secondTagStartIndex);

        return resultString.equals("true");
    }


}
