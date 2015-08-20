package pl.looksoft.lsportfolio.api;

import android.content.Context;

import java.io.IOException;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.ConversionException;

/**
 * Created by Jermey on 2015-08-18.
 */
public class RestClient {

    private static RestClient mInstance;

    private final static String API_ENDPOINT = "http://www.looksoft.pl";

//    private final static String API_ENDPOINT = "http://test2.afterparty.pl/api/v1";

//    private final static String AUTHORIZATION = "Basic cGFydHlfbmV3OldVcy1mVDJBcFI3RnhoRDE=";

    public ApiService mService;

    private Context mContext;

    private static class ApiErrorHandler implements ErrorHandler {

        Context context;

        public ApiErrorHandler(Context ctx) {
            this.context = ctx;
        }

        @Override
        public Throwable handleError(RetrofitError cause) {
            switch (cause.getKind()) {

                case NETWORK:
                    return RetrofitError.networkError(cause.getUrl(), new IOException("Network error"));
                case CONVERSION:
                    return RetrofitError.conversionError(cause.getUrl(), cause.getResponse(), null, cause.getSuccessType(), new ConversionException(cause.getMessage()));
                case HTTP:
                    return RetrofitError.httpError(cause.getUrl(), cause.getResponse(), null, cause.getSuccessType());
                case UNEXPECTED:
                    return RetrofitError.unexpectedError(cause.getUrl(), new Throwable(cause.getMessage(), cause));
            }
            return RetrofitError.unexpectedError(cause.getUrl(), new Throwable(cause.getMessage(), cause));
        }
    }

    public static RestClient getInstance(Context context) {
        if (mInstance == null)
            mInstance = new RestClient(context);
        return mInstance;
    }

    public RestClient(final Context context) {
        mContext = context;

//        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
//        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
//        okHttpClient.setWriteTimeout(60, TimeUnit.SECONDS);
//        Gson gson = new Gson();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new ApiErrorHandler(context))
//                .setClient(new OkClient(okHttpClient))
//                .setConverter(new GsonConverter(gson))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("User-Agent", "Android");
//                        request.addHeader("Authorization", AUTHORIZATION);
                    }
                })
                .build();

        mService = restAdapter.create(ApiService.class);
    }


}

