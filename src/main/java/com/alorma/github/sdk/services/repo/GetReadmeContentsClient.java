package com.alorma.github.sdk.services.repo;

import android.content.Context;
import android.util.Base64;
import com.alorma.github.sdk.bean.dto.request.RequestMarkdownDTO;
import com.alorma.github.sdk.bean.dto.response.Content;
import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.content.GetMarkdownClient;
import java.io.UnsupportedEncodingException;
import retrofit.RestAdapter;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Bernat on 20/07/2014.
 */
public class GetReadmeContentsClient extends GithubRepoClient<String> {

    public GetReadmeContentsClient(Context context, RepoInfo info) {
        super(context, info);
    }

    @Override
    protected Observable<String> getApiObservable(RestAdapter restAdapter) {
        RepoService repoService = restAdapter.create(RepoService.class);

        Observable<Content> contentObservable;

        if (getBranch() == null) {
            contentObservable = repoService.readme(getOwner(), getRepo());
        } else {
            contentObservable = repoService.readme(getOwner(), getRepo(), getBranch());
        }

        return contentObservable.flatMap(new Func1<Content, Observable<String>>() {
            @Override
            public Observable<String> call(Content content) {
                RequestMarkdownDTO requestMarkdownDTO = new RequestMarkdownDTO();
                if ("base64".equals(content.encoding)) {
                    byte[] data = Base64.decode(content.content, Base64.DEFAULT);
                    try {
                        content.content = new String(data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
            }
                GetMarkdownClient markdownClient =
                    new GetMarkdownClient(context, requestMarkdownDTO);
                return markdownClient.observable();
        }
        });
    }
}
