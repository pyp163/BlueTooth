package no.nordicsemi.android.dfu;

public abstract interface DfuController
{
  public abstract void abort();

  public abstract void pause();

  public abstract void resume();
}