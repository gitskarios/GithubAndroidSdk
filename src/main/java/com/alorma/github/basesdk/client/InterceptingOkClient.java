package com.alorma.github.basesdk.client;

import com.alorma.github.sdk.bean.info.PaginationLink;
import com.alorma.github.sdk.bean.info.RelType;
import com.squareup.okhttp.OkHttpClient;

import java.io.IOException;
import java.util.Objects;

import retrofit.client.Header;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;

public class InterceptingOkClient extends OkClient {

    private BaseClient githubClient;

    public InterceptingOkClient(BaseClient client) {
        this.githubClient = client;
    }

    public InterceptingOkClient(OkHttpClient client) {
        super(client);
    }

    @Override
    public Response execute(Request request) throws IOException {

        Response response = super.execute(request);
        try {
            for (Header header : response.getHeaders()) {
                if (header.getName().equals("Link")) {
                    String[] parts = header.getValue().split(",");
                    for (String part : parts) {
                        PaginationLink bottomPaginationLink = new PaginationLink(part);
                        switch (bottomPaginationLink.rel){
                            case last:
                                githubClient.last = bottomPaginationLink.uri;
                                githubClient.lastPage = bottomPaginationLink.page;
                                break;
                            case next:
                                githubClient.next = bottomPaginationLink.uri;
                                githubClient.nextPage = bottomPaginationLink.page;
                                break;
                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

}
