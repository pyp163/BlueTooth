package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Contacts;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class LocalContentUriFetchProducer extends LocalFetchProducer
{
  public static final String PRODUCER_NAME = "LocalContentUriFetchProducer";
  private static final String[] PROJECTION = { "_id", "_data" };
  private final ContentResolver mContentResolver;

  public LocalContentUriFetchProducer(Executor paramExecutor, PooledByteBufferFactory paramPooledByteBufferFactory, ContentResolver paramContentResolver)
  {
    super(paramExecutor, paramPooledByteBufferFactory);
    this.mContentResolver = paramContentResolver;
  }

  @Nullable
  private EncodedImage getCameraImage(Uri paramUri)
    throws IOException
  {
    paramUri = this.mContentResolver.query(paramUri, PROJECTION, null, null, null);
    if (paramUri == null)
      return null;
    try
    {
      int i = paramUri.getCount();
      if (i == 0)
        return null;
      paramUri.moveToFirst();
      Object localObject1 = paramUri.getString(paramUri.getColumnIndex("_data"));
      if (localObject1 != null)
      {
        localObject1 = getEncodedImage(new FileInputStream((String)localObject1), getLength((String)localObject1));
        return localObject1;
      }
      return null;
    }
    finally
    {
      paramUri.close();
    }
  }

  private static int getLength(String paramString)
  {
    if (paramString == null)
      return -1;
    return (int)new File(paramString).length();
  }

  protected EncodedImage getEncodedImage(ImageRequest paramImageRequest)
    throws IOException
  {
    Uri localUri = paramImageRequest.getSourceUri();
    if (UriUtil.isLocalContactUri(localUri))
      if (localUri.toString().endsWith("/photo"))
        paramImageRequest = this.mContentResolver.openInputStream(localUri);
      else if (!localUri.toString().endsWith("/display_photo"));
    try
    {
      paramImageRequest = this.mContentResolver.openAssetFileDescriptor(localUri, "r").createInputStream();
      break label146;
      label65: paramImageRequest = new StringBuilder();
      paramImageRequest.append("Contact photo does not exist: ");
      paramImageRequest.append(localUri);
      throw new IOException(paramImageRequest.toString());
      InputStream localInputStream = ContactsContract.Contacts.openContactPhotoInputStream(this.mContentResolver, localUri);
      paramImageRequest = localInputStream;
      if (localInputStream == null)
      {
        paramImageRequest = new StringBuilder();
        paramImageRequest.append("Contact photo does not exist: ");
        paramImageRequest.append(localUri);
        throw new IOException(paramImageRequest.toString());
      }
      label146: return getEncodedImage(paramImageRequest, -1);
      if (UriUtil.isLocalCameraUri(localUri))
      {
        paramImageRequest = getCameraImage(localUri);
        if (paramImageRequest != null)
          return paramImageRequest;
      }
      return getEncodedImage(this.mContentResolver.openInputStream(localUri), -1);
    }
    catch (IOException paramImageRequest)
    {
      break label65;
    }
  }

  protected String getProducerName()
  {
    return "LocalContentUriFetchProducer";
  }
}