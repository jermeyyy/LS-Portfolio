package pl.looksoft.lsportfolio.api;

import pl.looksoft.lsportfolio.model.AppItem;
import pl.looksoft.lsportfolio.model.BaseResponse;
import retrofit.http.GET;

/**
 * Created by Jermey on 2015-08-18.
 */
public interface ApiService {

    @GET("/api/main")
    BaseResponse<AppItem> getAppsList();

//    @GET("/api/product/{id}")
//    BaseResponse<AppDetail> getAppDetails(@Path("id") long id);
}
