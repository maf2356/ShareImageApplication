package cys.share.image.api.custom;

import java.io.File;
import java.io.IOException;

import cys.share.image.api.custom.imp.ProgressListener;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by Administrator on 2016/11/2.
 */
public class UploadFileRequestBody extends RequestBody {

    private RequestBody mRequestBody;
    private ProgressListener mProgressListener;

    private BufferedSink bufferedSink;


    public UploadFileRequestBody(File file, ProgressListener progressListener) {
        this.mRequestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        this.mProgressListener = progressListener;
    }

    public UploadFileRequestBody(RequestBody requestBody, ProgressListener progressListener) {
        this.mRequestBody = requestBody;
        this.mProgressListener = progressListener;
    }

    @Override
    public MediaType contentType() {
        return mRequestBody.contentType();
    }

    @Override
    public long contentLength() throws IOException {
        return mRequestBody.contentLength();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            bufferedSink = Okio.buffer(sink(sink));
        }
        mRequestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            long bytesWritten = 0L;
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    contentLength = contentLength();
                }
                bytesWritten += byteCount;
                mProgressListener.onProgress(bytesWritten, contentLength, bytesWritten == contentLength);
            }
        };
    }
}