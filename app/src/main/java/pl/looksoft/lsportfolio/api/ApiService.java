package pl.looksoft.lsportfolio.api;

import pl.looksoft.lsportfolio.model.AppDetail;
import pl.looksoft.lsportfolio.model.AppItem;
import pl.looksoft.lsportfolio.model.BaseResponse;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Jermey on 2015-08-18.
 */
public interface ApiService {

    @GET("/api/main")
    BaseResponse<AppItem> getApps();

    @GET("/api/product/{id}")
    BaseResponse<AppDetail> getAppDetails(@Path("id") long id);
}
