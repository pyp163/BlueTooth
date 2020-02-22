package org.apache.commons.net.ftp;

public abstract interface FTPFileFilter
{
  public abstract boolean accept(FTPFile paramFTPFile);
}