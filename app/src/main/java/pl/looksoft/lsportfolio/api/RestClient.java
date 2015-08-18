package pl.looksoft.lsportfolio.api;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.ConversionException;
import retrofit.converter.GsonConverter;

/**
 * Created by Jermey on 2015-08-18.
 */
public class RestClient {

    private static RestClient mInstance;

    private final static String API_ENDPOINT = "http://looksoft.pl";

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

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API_ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .setErrorHandler(new ApiErrorHandler(context))
                .build();

        mService = restAdapter.create(ApiService.class);
    }


}

