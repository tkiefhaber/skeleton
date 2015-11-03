package tv.rustychicken.skeleton;

import junit.framework.Test;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface ApiService {

    @GET("/api/v1/tests")
    Observable<List<Test>> getTests();

    @GET("api/v1/tests/{id}")
    Observable<Test> getTest(@Path("id") int id);

}
