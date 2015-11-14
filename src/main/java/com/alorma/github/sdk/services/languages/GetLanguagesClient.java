package com.alorma.github.sdk.services.languages;

import android.content.Context;
import com.alorma.github.sdk.R;
import com.alorma.github.sdk.services.client.GithubClient;
import java.util.Arrays;
import java.util.List;
import retrofit.RestAdapter;
import rx.Observable;

/**
 * Created by Bernat on 24/09/2015.
 */
public class GetLanguagesClient extends GithubClient<List<String>> {
    private boolean allLanguages;

    public GetLanguagesClient(Context context, boolean allLanguages) {
        super(context);
        this.allLanguages = allLanguages;
    }

    @Override
    protected Observable<List<String>> getApiObservable(RestAdapter restAdapter) {
        String[] languages = getContext().getResources()
            .getStringArray(allLanguages ? R.array.available_languages : R.array.default_languages);

        return Observable.just(Arrays.asList(languages));
    }
}
