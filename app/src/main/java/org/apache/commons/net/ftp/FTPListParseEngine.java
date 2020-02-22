package org.apache.commons.net.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class FTPListParseEngine
{
  private ListIterator<String> _internalIterator = this.entries.listIterator();
  private List<String> entries = new LinkedList();
  private final FTPFileEntryParser parser;

  public FTPListParseEngine(FTPFileEntryParser paramFTPFileEntryParser)
  {
    this.parser = paramFTPFileEntryParser;
  }

  private void readStream(InputStream paramInputStream, String paramString)
    throws IOException
  {
    if (paramString == null)
      paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream));
    else
      paramInputStream = new BufferedReader(new InputStreamReader(paramInputStream, paramString));
    for (paramString = this.parser.readNextEntry(paramInputStream); paramString != null; paramString = this.parser.readNextEntry(paramInputStream))
      this.entries.add(paramString);
    paramInputStream.close();
  }

  public FTPFile[] getFiles()
    throws IOException
  {
    return getFiles(FTPFileFilters.NON_NULL);
  }

  public FTPFile[] getFiles(FTPFileFilter paramFTPFileFilter)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.entries.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (String)localIterator.next();
      localObject = this.parser.parseFTPEntry((String)localObject);
      if (paramFTPFileFilter.accept((FTPFile)localObject))
        localArrayList.add(localObject);
    }
    return (FTPFile[])localArrayList.toArray(new FTPFile[localArrayList.size()]);
  }

  public FTPFile[] getNext(int paramInt)
  {
    LinkedList localLinkedList = new LinkedList();
    while ((paramInt > 0) && (this._internalIterator.hasNext()))
    {
      String str = (String)this._internalIterator.next();
      localLinkedList.add(this.parser.parseFTPEntry(str));
      paramInt -= 1;
    }
    return (FTPFile[])localLinkedList.toArray(new FTPFile[localLinkedList.size()]);
  }

  public FTPFile[] getPrevious(int paramInt)
  {
    LinkedList localLinkedList = new LinkedList();
    while ((paramInt > 0) && (this._internalIterator.hasPrevious()))
    {
      String str = (String)this._internalIterator.previous();
      localLinkedList.add(0, this.parser.parseFTPEntry(str));
      paramInt -= 1;
    }
    return (FTPFile[])localLinkedList.toArray(new FTPFile[localLinkedList.size()]);
  }

  public boolean hasNext()
  {
    return this._internalIterator.hasNext();
  }

  public boolean hasPrevious()
  {
    return this._internalIterator.hasPrevious();
  }

  @Deprecated
  public void readServerList(InputStream paramInputStream)
    throws IOException
  {
    readServerList(paramInputStream, null);
  }

  public void readServerList(InputStream paramInputStream, String paramString)
    throws IOException
  {
    this.entries = new LinkedList();
    readStream(paramInputStream, paramString);
    this.parser.preParse(this.entries);
    resetIterator();
  }

  public void resetIterator()
  {
    this._internalIterator = this.entries.listIterator();
  }
}