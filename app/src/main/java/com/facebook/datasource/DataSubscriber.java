package com.facebook.datasource;

public abstract interface DataSubscriber<T>
{
  public abstract void onCancellation(DataSource<T> paramDataSource);

  public abstract void onFailure(DataSource<T> paramDataSource);

  public abstract void onNewResult(DataSource<T> paramDataSource);

  public abstract void onProgressUpdate(DataSource<T> paramDataSource);
}