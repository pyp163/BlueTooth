package org.apache.commons.net.ftp;

public class FTPFileFilters
{
  public static final FTPFileFilter ALL = new FTPFileFilter()
  {
    public boolean accept(FTPFile paramAnonymousFTPFile)
    {
      return true;
    }
  };
  public static final FTPFileFilter DIRECTORIES = new FTPFileFilter()
  {
    public boolean accept(FTPFile paramAnonymousFTPFile)
    {
      return (paramAnonymousFTPFile != null) && (paramAnonymousFTPFile.isDirectory());
    }
  };
  public static final FTPFileFilter NON_NULL = new FTPFileFilter()
  {
    public boolean accept(FTPFile paramAnonymousFTPFile)
    {
      return paramAnonymousFTPFile != null;
    }
  };
}