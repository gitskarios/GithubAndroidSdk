package com.alorma.github.sdk.services.content;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import com.alorma.github.sdk.bean.info.RepoInfo;
import com.alorma.github.sdk.services.client.BaseClient;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bernat on 17/12/2014.
 */
public class GetArchiveLinkService extends BaseClient {

	private OnDownloadServiceListener onDownloadServiceListener;

	private RepoInfo repoInfo;
	private final String fileType;

	public GetArchiveLinkService(Context context, RepoInfo repoInfo, String fileType) {
		super(context);
		this.repoInfo = repoInfo;
		if (fileType== null) {
			fileType = "zipball";
		}
		this.fileType = fileType;
	}

	@Override
	protected void executeService(RestAdapter restAdapter) {
		restAdapter.create(ContentService.class).archiveLink(repoInfo.owner, repoInfo.name, fileType, repoInfo.branch, new Callback<Object>() {
			@Override
			public void success(Object o, Response r) {
				r.getHeaders();
			}

			@Override
			public void failure(RetrofitError error) {
				if (error.getResponse().getReason().equalsIgnoreCase("OK")) {
					String url = error.getResponse().getUrl();
					DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
					DownloadManager.Request request = new DownloadManager.Request(
							Uri.parse(url));
					String fileName = repoInfo.name + "_" + repoInfo.branch + "_" + "." + (fileType.equalsIgnoreCase("zipball") ? "zip" : "tar");
					request.setTitle(fileName);
					request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "gitskarios/" + fileName);
					request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
					request.allowScanningByMediaScanner();
					final long enqueue = dm.enqueue(request);

					handler.post(new Runnable() {
						@Override
						public void run() {
							if (onDownloadServiceListener != null) {
								onDownloadServiceListener.onDownloadEnqueded(enqueue);
							}
						}
					});
				}
			}
		});
	}

	public void setOnDownloadServiceListener(OnDownloadServiceListener onDownloadServiceListener) {
		this.onDownloadServiceListener = onDownloadServiceListener;
	}

	public interface OnDownloadServiceListener {
		void onDownloadEnqueded(long id);
	}
}
