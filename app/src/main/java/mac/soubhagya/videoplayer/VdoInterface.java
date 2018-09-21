package mac.soubhagya.videoplayer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VdoInterface {

    @GET("/media.json?print=pretty")
    Call<List<Videos>> getVdoData();
}
