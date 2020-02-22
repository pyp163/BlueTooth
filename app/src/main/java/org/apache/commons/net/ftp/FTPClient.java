package org.apache.commons.net.ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ServerSocketFactory;
import javax.net.SocketFactory;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.ftp.parser.DefaultFTPFileEntryParserFactory;
import org.apache.commons.net.ftp.parser.FTPFileEntryParserFactory;
import org.apache.commons.net.ftp.parser.MLSxEntryParser;
import org.apache.commons.net.io.CRLFLineReader;
import org.apache.commons.net.io.CopyStreamAdapter;
import org.apache.commons.net.io.CopyStreamEvent;
import org.apache.commons.net.io.CopyStreamListener;
import org.apache.commons.net.io.FromNetASCIIInputStream;
import org.apache.commons.net.io.SocketInputStream;
import org.apache.commons.net.io.SocketOutputStream;
import org.apache.commons.net.io.ToNetASCIIOutputStream;
import org.apache.commons.net.io.Util;

public class FTPClient extends FTP
  implements Configurable
{
  public static final int ACTIVE_LOCAL_DATA_CONNECTION_MODE = 0;
  public static final int ACTIVE_REMOTE_DATA_CONNECTION_MODE = 1;
  public static final String FTP_SYSTEM_TYPE = "org.apache.commons.net.ftp.systemType";
  public static final int PASSIVE_LOCAL_DATA_CONNECTION_MODE = 2;
  public static final int PASSIVE_REMOTE_DATA_CONNECTION_MODE = 3;
  public static final String SYSTEM_TYPE_PROPERTIES = "/systemType.properties";
  private static final Pattern __PARMS_PAT = Pattern.compile("(\\d{1,3},\\d{1,3},\\d{1,3},\\d{1,3}),(\\d{1,3}),(\\d{1,3})");
  private InetAddress __activeExternalHost;
  private int __activeMaxPort;
  private int __activeMinPort;
  private boolean __autodetectEncoding = false;
  private int __bufferSize;
  private FTPClientConfig __configuration;
  private int __controlKeepAliveReplyTimeout = 1000;
  private long __controlKeepAliveTimeout;
  private CopyStreamListener __copyStreamListener;
  private int __dataConnectionMode;
  private int __dataTimeout;
  private FTPFileEntryParser __entryParser;
  private String __entryParserKey;
  private HashMap<String, Set<String>> __featuresMap;
  private int __fileFormat;
  private int __fileStructure;
  private int __fileTransferMode;
  private int __fileType;
  private boolean __listHiddenFiles;
  private FTPFileEntryParserFactory __parserFactory;
  private String __passiveHost;
  private int __passivePort;
  private final Random __random;
  private boolean __remoteVerificationEnabled;
  private long __restartOffset;
  private String __systemName;
  private boolean __useEPSVwithIPv4;

  public FTPClient()
  {
    __initDefaults();
    this.__dataTimeout = -1;
    this.__remoteVerificationEnabled = true;
    this.__parserFactory = new DefaultFTPFileEntryParserFactory();
    this.__configuration = null;
    this.__listHiddenFiles = false;
    this.__useEPSVwithIPv4 = false;
    this.__random = new Random();
  }

  private void __initDefaults()
  {
    this.__dataConnectionMode = 0;
    this.__passiveHost = null;
    this.__passivePort = -1;
    this.__activeExternalHost = null;
    this.__activeMinPort = 0;
    this.__activeMaxPort = 0;
    this.__fileType = 0;
    this.__fileStructure = 7;
    this.__fileFormat = 4;
    this.__fileTransferMode = 10;
    this.__restartOffset = 0L;
    this.__systemName = null;
    this.__entryParser = null;
    this.__entryParserKey = "";
    this.__bufferSize = 1024;
    this.__featuresMap = null;
  }

  private CopyStreamListener __mergeListeners(CopyStreamListener paramCopyStreamListener)
  {
    if (paramCopyStreamListener == null)
      return this.__copyStreamListener;
    if (this.__copyStreamListener == null)
      return paramCopyStreamListener;
    CopyStreamAdapter localCopyStreamAdapter = new CopyStreamAdapter();
    localCopyStreamAdapter.addCopyStreamListener(paramCopyStreamListener);
    localCopyStreamAdapter.addCopyStreamListener(this.__copyStreamListener);
    return localCopyStreamAdapter;
  }

  private void __parseExtendedPassiveModeReply(String paramString)
    throws MalformedServerReplyException
  {
    paramString = paramString.substring(paramString.indexOf('(') + 1, paramString.indexOf(')')).trim();
    int i = paramString.charAt(0);
    int j = paramString.charAt(1);
    int k = paramString.charAt(2);
    int m = paramString.charAt(paramString.length() - 1);
    if ((i == j) && (j == k) && (k == m));
    try
    {
      i = Integer.parseInt(paramString.substring(3, paramString.length() - 1));
      this.__passiveHost = getRemoteAddress().getHostAddress();
      this.__passivePort = i;
      return;
      label106: StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Could not parse extended passive host information.\nServer Reply: ");
      localStringBuilder.append(paramString);
      throw new MalformedServerReplyException(localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Could not parse extended passive host information.\nServer Reply: ");
      localStringBuilder.append(paramString);
      throw new MalformedServerReplyException(localStringBuilder.toString());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label106;
    }
  }

  private void __parsePassiveModeReply(String paramString)
    throws MalformedServerReplyException
  {
    Object localObject = __PARMS_PAT.matcher(paramString);
    if (!((Matcher)localObject).find())
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Could not parse passive host information.\nServer Reply: ");
      ((StringBuilder)localObject).append(paramString);
      throw new MalformedServerReplyException(((StringBuilder)localObject).toString());
    }
    this.__passiveHost = ((Matcher)localObject).group(1).replace(',', '.');
    try
    {
      int i = Integer.parseInt(((Matcher)localObject).group(2));
      this.__passivePort = (Integer.parseInt(((Matcher)localObject).group(3)) | i << 8);
    }
    catch (NumberFormatException localNumberFormatException)
    {
      try
      {
        if ((InetAddress.getByName(this.__passiveHost).isSiteLocalAddress()) && (!getRemoteAddress().isSiteLocalAddress()))
        {
          localObject = getRemoteAddress().getHostAddress();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("[Replacing site local address ");
          localStringBuilder.append(this.__passiveHost);
          localStringBuilder.append(" with ");
          localStringBuilder.append((String)localObject);
          localStringBuilder.append("]\n");
          fireReplyReceived(0, localStringBuilder.toString());
          this.__passiveHost = ((String)localObject);
        }
        return;
        label187: localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Could not parse passive host information.\nServer Reply: ");
        ((StringBuilder)localObject).append(paramString);
        throw new MalformedServerReplyException(((StringBuilder)localObject).toString());
        while (true)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Could not parse passive port information.\nServer Reply: ");
          ((StringBuilder)localObject).append(paramString);
          throw new MalformedServerReplyException(((StringBuilder)localObject).toString());
          localNumberFormatException = localNumberFormatException;
        }
      }
      catch (UnknownHostException localUnknownHostException)
      {
        break label187;
      }
    }
  }

  private String __parsePathname(String paramString)
  {
    int i = paramString.indexOf('"') + 1;
    return paramString.substring(i, paramString.indexOf('"', i));
  }

  private boolean __storeFile(int paramInt, String paramString, InputStream paramInputStream)
    throws IOException
  {
    Socket localSocket = _openDataConnection_(paramInt, paramString);
    if (localSocket == null)
      return false;
    Object localObject = new BufferedOutputStream(localSocket.getOutputStream(), getBufferSize());
    paramString = (String)localObject;
    if (this.__fileType == 0)
      paramString = new ToNetASCIIOutputStream((OutputStream)localObject);
    localObject = null;
    if (this.__controlKeepAliveTimeout > 0L)
      localObject = new CSL(this, this.__controlKeepAliveTimeout, this.__controlKeepAliveReplyTimeout);
    try
    {
      Util.copyStream(paramInputStream, paramString, getBufferSize(), -1L, __mergeListeners((CopyStreamListener)localObject), false);
      paramString.close();
      localSocket.close();
      boolean bool = completePendingCommand();
      if (localObject != null)
        ((CSL)localObject).cleanUp();
      return bool;
    }
    catch (IOException paramString)
    {
      Util.closeQuietly(localSocket);
    }
    throw paramString;
  }

  private OutputStream __storeFileStream(int paramInt, String paramString)
    throws IOException
  {
    Socket localSocket = _openDataConnection_(paramInt, paramString);
    if (localSocket == null)
      return null;
    OutputStream localOutputStream = localSocket.getOutputStream();
    paramString = localOutputStream;
    if (this.__fileType == 0)
      paramString = new ToNetASCIIOutputStream(new BufferedOutputStream(localOutputStream, getBufferSize()));
    return new SocketOutputStream(localSocket, paramString);
  }

  private int getActivePort()
  {
    if ((this.__activeMinPort > 0) && (this.__activeMaxPort >= this.__activeMinPort))
    {
      if (this.__activeMaxPort == this.__activeMinPort)
        return this.__activeMaxPort;
      return this.__random.nextInt(this.__activeMaxPort - this.__activeMinPort + 1) + this.__activeMinPort;
    }
    return 0;
  }

  private InetAddress getHostAddress()
  {
    if (this.__activeExternalHost != null)
      return this.__activeExternalHost;
    return getLocalAddress();
  }

  private static Properties getOverrideProperties()
  {
    return PropertiesSingleton.PROPERTIES;
  }

  private boolean initFeatureMap()
    throws IOException
  {
    if (this.__featuresMap == null)
    {
      boolean bool = FTPReply.isPositiveCompletion(feat());
      this.__featuresMap = new HashMap();
      int i = 0;
      if (!bool)
        return false;
      String[] arrayOfString = getReplyStrings();
      int j = arrayOfString.length;
      while (i < j)
      {
        Object localObject2 = arrayOfString[i];
        if (((String)localObject2).startsWith(" "))
        {
          String str1 = "";
          int k = ((String)localObject2).indexOf(' ', 1);
          if (k > 0)
          {
            localObject1 = ((String)localObject2).substring(1, k);
            str1 = ((String)localObject2).substring(k + 1);
          }
          else
          {
            localObject1 = ((String)localObject2).substring(1);
          }
          String str2 = ((String)localObject1).toUpperCase(Locale.ENGLISH);
          localObject2 = (Set)this.__featuresMap.get(str2);
          Object localObject1 = localObject2;
          if (localObject2 == null)
          {
            localObject1 = new HashSet();
            this.__featuresMap.put(str2, localObject1);
          }
          ((Set)localObject1).add(str1);
        }
        i += 1;
      }
    }
    return true;
  }

  private FTPListParseEngine initiateListParsing(FTPFileEntryParser paramFTPFileEntryParser, String paramString)
    throws IOException
  {
    paramFTPFileEntryParser = new FTPListParseEngine(paramFTPFileEntryParser);
    paramString = _openDataConnection_(26, getListArguments(paramString));
    if (paramString == null)
      return paramFTPFileEntryParser;
    try
    {
      paramFTPFileEntryParser.readServerList(paramString.getInputStream(), getControlEncoding());
      Util.closeQuietly(paramString);
      completePendingCommand();
      return paramFTPFileEntryParser;
    }
    finally
    {
      Util.closeQuietly(paramString);
    }
    throw paramFTPFileEntryParser;
  }

  private FTPListParseEngine initiateMListParsing(String paramString)
    throws IOException
  {
    FTPListParseEngine localFTPListParseEngine = new FTPListParseEngine(MLSxEntryParser.getInstance());
    paramString = _openDataConnection_(38, paramString);
    if (paramString == null)
      return localFTPListParseEngine;
    try
    {
      localFTPListParseEngine.readServerList(paramString.getInputStream(), getControlEncoding());
      return localFTPListParseEngine;
    }
    finally
    {
      Util.closeQuietly(paramString);
      completePendingCommand();
    }
  }

  private boolean restart(long paramLong)
    throws IOException
  {
    this.__restartOffset = 0L;
    return FTPReply.isPositiveIntermediate(rest(Long.toString(paramLong)));
  }

  protected void _connectAction_()
    throws IOException
  {
    super._connectAction_();
    __initDefaults();
    if (this.__autodetectEncoding)
    {
      ArrayList localArrayList = new ArrayList(this._replyLines);
      int i = this._replyCode;
      if ((hasFeature("UTF8")) || (hasFeature("UTF-8")))
      {
        setControlEncoding("UTF-8");
        this._controlInput_ = new CRLFLineReader(new InputStreamReader(this._input_, getControlEncoding()));
        this._controlOutput_ = new BufferedWriter(new OutputStreamWriter(this._output_, getControlEncoding()));
      }
      this._replyLines.clear();
      this._replyLines.addAll(localArrayList);
      this._replyCode = i;
    }
  }

  protected Socket _openDataConnection_(int paramInt, String paramString)
    throws IOException
  {
    if ((this.__dataConnectionMode != 0) && (this.__dataConnectionMode != 2))
      return null;
    boolean bool = getRemoteAddress() instanceof Inet6Address;
    int i = this.__dataConnectionMode;
    int j = 1;
    if (i == 0)
    {
      localObject = this._serverSocketFactory_.createServerSocket(getActivePort(), 1, getHostAddress());
      if (bool)
      {
        if (!FTPReply.isPositiveCompletion(eprt(getHostAddress(), ((ServerSocket)localObject).getLocalPort())))
        {
          ((ServerSocket)localObject).close();
          return null;
        }
      }
      else if (!FTPReply.isPositiveCompletion(port(getHostAddress(), ((ServerSocket)localObject).getLocalPort())))
      {
        ((ServerSocket)localObject).close();
        return null;
      }
      if ((this.__restartOffset > 0L) && (!restart(this.__restartOffset)))
      {
        ((ServerSocket)localObject).close();
        return null;
      }
      if (!FTPReply.isPositivePreliminary(sendCommand(paramInt, paramString)))
      {
        ((ServerSocket)localObject).close();
        return null;
      }
      if (this.__dataTimeout >= 0)
        ((ServerSocket)localObject).setSoTimeout(this.__dataTimeout);
    }
    try
    {
      paramString = ((ServerSocket)localObject).accept();
      ((ServerSocket)localObject).close();
    }
    finally
    {
      ((ServerSocket)localObject).close();
    }
    if (!isUseEPSVwithIPv4())
      if (bool)
        i = j;
      else
        i = 0;
    if ((i != 0) && (epsv() == 229))
    {
      __parseExtendedPassiveModeReply((String)this._replyLines.get(0));
    }
    else
    {
      if (bool)
        return null;
      if (pasv() != 227)
        return null;
      __parsePassiveModeReply((String)this._replyLines.get(0));
    }
    Object localObject = this._socketFactory_.createSocket();
    ((Socket)localObject).connect(new InetSocketAddress(this.__passiveHost, this.__passivePort), this.connectTimeout);
    if ((this.__restartOffset > 0L) && (!restart(this.__restartOffset)))
    {
      ((Socket)localObject).close();
      return null;
    }
    if (!FTPReply.isPositivePreliminary(sendCommand(paramInt, paramString)))
    {
      ((Socket)localObject).close();
      return null;
    }
    paramString = (String)localObject;
    if ((this.__remoteVerificationEnabled) && (!verifyRemote(paramString)))
    {
      localObject = paramString.getInetAddress();
      InetAddress localInetAddress = getRemoteAddress();
      paramString.close();
      paramString = new StringBuilder();
      paramString.append("Host attempting data connection ");
      paramString.append(((InetAddress)localObject).getHostAddress());
      paramString.append(" is not same as server ");
      paramString.append(localInetAddress.getHostAddress());
      throw new IOException(paramString.toString());
    }
    if (this.__dataTimeout >= 0)
      paramString.setSoTimeout(this.__dataTimeout);
    return paramString;
  }

  public boolean abort()
    throws IOException
  {
    return FTPReply.isPositiveCompletion(abor());
  }

  public boolean allocate(int paramInt)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(allo(paramInt));
  }

  public boolean allocate(int paramInt1, int paramInt2)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(allo(paramInt1, paramInt2));
  }

  public boolean appendFile(String paramString, InputStream paramInputStream)
    throws IOException
  {
    return __storeFile(16, paramString, paramInputStream);
  }

  public OutputStream appendFileStream(String paramString)
    throws IOException
  {
    return __storeFileStream(16, paramString);
  }

  public boolean changeToParentDirectory()
    throws IOException
  {
    return FTPReply.isPositiveCompletion(cdup());
  }

  public boolean changeWorkingDirectory(String paramString)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(cwd(paramString));
  }

  public boolean completePendingCommand()
    throws IOException
  {
    return FTPReply.isPositiveCompletion(getReply());
  }

  public void configure(FTPClientConfig paramFTPClientConfig)
  {
    this.__configuration = paramFTPClientConfig;
  }

  public boolean deleteFile(String paramString)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(dele(paramString));
  }

  public void disconnect()
    throws IOException
  {
    super.disconnect();
    __initDefaults();
  }

  public boolean doCommand(String paramString1, String paramString2)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(sendCommand(paramString1, paramString2));
  }

  public String[] doCommandAsStrings(String paramString1, String paramString2)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(sendCommand(paramString1, paramString2)))
      return getReplyStrings();
    return null;
  }

  public void enterLocalActiveMode()
  {
    this.__dataConnectionMode = 0;
    this.__passiveHost = null;
    this.__passivePort = -1;
  }

  public void enterLocalPassiveMode()
  {
    this.__dataConnectionMode = 2;
    this.__passiveHost = null;
    this.__passivePort = -1;
  }

  public boolean enterRemoteActiveMode(InetAddress paramInetAddress, int paramInt)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(port(paramInetAddress, paramInt)))
    {
      this.__dataConnectionMode = 1;
      this.__passiveHost = null;
      this.__passivePort = -1;
      return true;
    }
    return false;
  }

  public boolean enterRemotePassiveMode()
    throws IOException
  {
    if (pasv() != 227)
      return false;
    this.__dataConnectionMode = 3;
    __parsePassiveModeReply((String)this._replyLines.get(0));
    return true;
  }

  public String featureValue(String paramString)
    throws IOException
  {
    paramString = featureValues(paramString);
    if (paramString != null)
      return paramString[0];
    return null;
  }

  public String[] featureValues(String paramString)
    throws IOException
  {
    if (!initFeatureMap())
      return null;
    paramString = (Set)this.__featuresMap.get(paramString.toUpperCase(Locale.ENGLISH));
    if (paramString != null)
      return (String[])paramString.toArray(new String[paramString.size()]);
    return null;
  }

  public boolean features()
    throws IOException
  {
    return FTPReply.isPositiveCompletion(feat());
  }

  public boolean getAutodetectUTF8()
  {
    return this.__autodetectEncoding;
  }

  public int getBufferSize()
  {
    return this.__bufferSize;
  }

  public int getControlKeepAliveReplyTimeout()
  {
    return this.__controlKeepAliveReplyTimeout;
  }

  public long getControlKeepAliveTimeout()
  {
    return this.__controlKeepAliveTimeout / 1000L;
  }

  public CopyStreamListener getCopyStreamListener()
  {
    return this.__copyStreamListener;
  }

  public int getDataConnectionMode()
  {
    return this.__dataConnectionMode;
  }

  protected String getListArguments(String paramString)
  {
    if (getListHiddenFiles())
    {
      if (paramString != null)
      {
        StringBuilder localStringBuilder = new StringBuilder(paramString.length() + 3);
        localStringBuilder.append("-a ");
        localStringBuilder.append(paramString);
        return localStringBuilder.toString();
      }
      return "-a";
    }
    return paramString;
  }

  public boolean getListHiddenFiles()
  {
    return this.__listHiddenFiles;
  }

  public String getModificationTime(String paramString)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(mdtm(paramString)))
      return getReplyString();
    return null;
  }

  public String getPassiveHost()
  {
    return this.__passiveHost;
  }

  public int getPassivePort()
  {
    return this.__passivePort;
  }

  public long getRestartOffset()
  {
    return this.__restartOffset;
  }

  public String getStatus()
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(stat()))
      return getReplyString();
    return null;
  }

  public String getStatus(String paramString)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(stat(paramString)))
      return getReplyString();
    return null;
  }

  @Deprecated
  public String getSystemName()
    throws IOException
  {
    if ((this.__systemName == null) && (FTPReply.isPositiveCompletion(syst())))
      this.__systemName = ((String)this._replyLines.get(this._replyLines.size() - 1)).substring(4);
    return this.__systemName;
  }

  public String getSystemType()
    throws IOException
  {
    if (this.__systemName == null)
      if (FTPReply.isPositiveCompletion(syst()))
      {
        this.__systemName = ((String)this._replyLines.get(this._replyLines.size() - 1)).substring(4);
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unable to determine system type - response: ");
        localStringBuilder.append(getReplyString());
        throw new IOException(localStringBuilder.toString());
      }
    return this.__systemName;
  }

  public boolean hasFeature(String paramString)
    throws IOException
  {
    if (!initFeatureMap())
      return false;
    return this.__featuresMap.containsKey(paramString.toUpperCase(Locale.ENGLISH));
  }

  public boolean hasFeature(String paramString1, String paramString2)
    throws IOException
  {
    if (!initFeatureMap())
      return false;
    paramString1 = (Set)this.__featuresMap.get(paramString1.toUpperCase(Locale.ENGLISH));
    if (paramString1 != null)
      return paramString1.contains(paramString2);
    return false;
  }

  public FTPListParseEngine initiateListParsing()
    throws IOException
  {
    return initiateListParsing((String)null);
  }

  public FTPListParseEngine initiateListParsing(String paramString)
    throws IOException
  {
    return initiateListParsing(null, paramString);
  }

  public FTPListParseEngine initiateListParsing(String paramString1, String paramString2)
    throws IOException
  {
    if ((this.__entryParser == null) || (!this.__entryParserKey.equals(paramString1)))
      if (paramString1 != null)
      {
        this.__entryParser = this.__parserFactory.createFileEntryParser(paramString1);
        this.__entryParserKey = paramString1;
      }
      else if (this.__configuration != null)
      {
        this.__entryParser = this.__parserFactory.createFileEntryParser(this.__configuration);
        this.__entryParserKey = this.__configuration.getServerSystemKey();
      }
      else
      {
        String str = System.getProperty("org.apache.commons.net.ftp.systemType");
        paramString1 = str;
        if (str == null)
        {
          str = getSystemType();
          Object localObject = getOverrideProperties();
          paramString1 = str;
          if (localObject != null)
          {
            localObject = ((Properties)localObject).getProperty(str);
            paramString1 = str;
            if (localObject != null)
              paramString1 = (String)localObject;
          }
        }
        this.__entryParser = this.__parserFactory.createFileEntryParser(paramString1);
        this.__entryParserKey = paramString1;
      }
    return initiateListParsing(this.__entryParser, paramString2);
  }

  public boolean isRemoteVerificationEnabled()
  {
    return this.__remoteVerificationEnabled;
  }

  public boolean isUseEPSVwithIPv4()
  {
    return this.__useEPSVwithIPv4;
  }

  public FTPFile[] listDirectories()
    throws IOException
  {
    return listDirectories((String)null);
  }

  public FTPFile[] listDirectories(String paramString)
    throws IOException
  {
    return listFiles(paramString, FTPFileFilters.DIRECTORIES);
  }

  public FTPFile[] listFiles()
    throws IOException
  {
    return listFiles((String)null);
  }

  public FTPFile[] listFiles(String paramString)
    throws IOException
  {
    return initiateListParsing((String)null, paramString).getFiles();
  }

  public FTPFile[] listFiles(String paramString, FTPFileFilter paramFTPFileFilter)
    throws IOException
  {
    return initiateListParsing((String)null, paramString).getFiles(paramFTPFileFilter);
  }

  public String listHelp()
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(help()))
      return getReplyString();
    return null;
  }

  public String listHelp(String paramString)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(help(paramString)))
      return getReplyString();
    return null;
  }

  public String[] listNames()
    throws IOException
  {
    return listNames(null);
  }

  public String[] listNames(String paramString)
    throws IOException
  {
    paramString = _openDataConnection_(27, getListArguments(paramString));
    if (paramString == null)
      return null;
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramString.getInputStream(), getControlEncoding()));
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      String str = localBufferedReader.readLine();
      if (str == null)
        break;
      localArrayList.add(str);
    }
    localBufferedReader.close();
    paramString.close();
    if (completePendingCommand())
      return (String[])localArrayList.toArray(new String[localArrayList.size()]);
    return null;
  }

  public boolean login(String paramString1, String paramString2)
    throws IOException
  {
    user(paramString1);
    if (FTPReply.isPositiveCompletion(this._replyCode))
      return true;
    if (!FTPReply.isPositiveIntermediate(this._replyCode))
      return false;
    return FTPReply.isPositiveCompletion(pass(paramString2));
  }

  public boolean login(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    user(paramString1);
    if (FTPReply.isPositiveCompletion(this._replyCode))
      return true;
    if (!FTPReply.isPositiveIntermediate(this._replyCode))
      return false;
    pass(paramString2);
    if (FTPReply.isPositiveCompletion(this._replyCode))
      return true;
    if (!FTPReply.isPositiveIntermediate(this._replyCode))
      return false;
    return FTPReply.isPositiveCompletion(acct(paramString3));
  }

  public boolean logout()
    throws IOException
  {
    return FTPReply.isPositiveCompletion(quit());
  }

  public boolean makeDirectory(String paramString)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(mkd(paramString));
  }

  public FTPFile[] mlistDir()
    throws IOException
  {
    return mlistDir(null);
  }

  public FTPFile[] mlistDir(String paramString)
    throws IOException
  {
    return initiateMListParsing(paramString).getFiles();
  }

  public FTPFile[] mlistDir(String paramString, FTPFileFilter paramFTPFileFilter)
    throws IOException
  {
    return initiateMListParsing(paramString).getFiles(paramFTPFileFilter);
  }

  public FTPFile mlistFile(String paramString)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(sendCommand(39, paramString)))
      return MLSxEntryParser.parseEntry(getReplyStrings()[1].substring(1));
    return null;
  }

  public String printWorkingDirectory()
    throws IOException
  {
    if (pwd() != 257)
      return null;
    return __parsePathname((String)this._replyLines.get(this._replyLines.size() - 1));
  }

  boolean reinitialize()
    throws IOException
  {
    rein();
    if ((!FTPReply.isPositiveCompletion(this._replyCode)) && ((!FTPReply.isPositivePreliminary(this._replyCode)) || (!FTPReply.isPositiveCompletion(getReply()))))
      return false;
    __initDefaults();
    return true;
  }

  public boolean remoteAppend(String paramString)
    throws IOException
  {
    if ((this.__dataConnectionMode != 1) && (this.__dataConnectionMode != 3))
      return false;
    return FTPReply.isPositivePreliminary(appe(paramString));
  }

  public boolean remoteRetrieve(String paramString)
    throws IOException
  {
    if ((this.__dataConnectionMode != 1) && (this.__dataConnectionMode != 3))
      return false;
    return FTPReply.isPositivePreliminary(retr(paramString));
  }

  public boolean remoteStore(String paramString)
    throws IOException
  {
    if ((this.__dataConnectionMode != 1) && (this.__dataConnectionMode != 3))
      return false;
    return FTPReply.isPositivePreliminary(stor(paramString));
  }

  public boolean remoteStoreUnique()
    throws IOException
  {
    if ((this.__dataConnectionMode != 1) && (this.__dataConnectionMode != 3))
      return false;
    return FTPReply.isPositivePreliminary(stou());
  }

  public boolean remoteStoreUnique(String paramString)
    throws IOException
  {
    if ((this.__dataConnectionMode != 1) && (this.__dataConnectionMode != 3))
      return false;
    return FTPReply.isPositivePreliminary(stou(paramString));
  }

  public boolean removeDirectory(String paramString)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(rmd(paramString));
  }

  public boolean rename(String paramString1, String paramString2)
    throws IOException
  {
    if (!FTPReply.isPositiveIntermediate(rnfr(paramString1)))
      return false;
    return FTPReply.isPositiveCompletion(rnto(paramString2));
  }

  public boolean retrieveFile(String paramString, OutputStream paramOutputStream)
    throws IOException
  {
    Socket localSocket = _openDataConnection_(13, paramString);
    if (localSocket == null)
      return false;
    paramString = new BufferedInputStream(localSocket.getInputStream(), getBufferSize());
    if (this.__fileType == 0)
      paramString = new FromNetASCIIInputStream(paramString);
    CSL localCSL = null;
    if (this.__controlKeepAliveTimeout > 0L)
      localCSL = new CSL(this, this.__controlKeepAliveTimeout, this.__controlKeepAliveReplyTimeout);
    try
    {
      Util.copyStream(paramString, paramOutputStream, getBufferSize(), -1L, __mergeListeners(localCSL), false);
      Util.closeQuietly(localSocket);
      boolean bool = completePendingCommand();
      if (localCSL != null)
        localCSL.cleanUp();
      return bool;
    }
    finally
    {
      Util.closeQuietly(localSocket);
    }
    throw paramString;
  }

  public InputStream retrieveFileStream(String paramString)
    throws IOException
  {
    Socket localSocket = _openDataConnection_(13, paramString);
    if (localSocket == null)
      return null;
    InputStream localInputStream = localSocket.getInputStream();
    paramString = localInputStream;
    if (this.__fileType == 0)
      paramString = new FromNetASCIIInputStream(new BufferedInputStream(localInputStream, getBufferSize()));
    return new SocketInputStream(localSocket, paramString);
  }

  public boolean sendNoOp()
    throws IOException
  {
    return FTPReply.isPositiveCompletion(noop());
  }

  public boolean sendSiteCommand(String paramString)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(site(paramString));
  }

  public void setActiveExternalIPAddress(String paramString)
    throws UnknownHostException
  {
    this.__activeExternalHost = InetAddress.getByName(paramString);
  }

  public void setActivePortRange(int paramInt1, int paramInt2)
  {
    this.__activeMinPort = paramInt1;
    this.__activeMaxPort = paramInt2;
  }

  public void setAutodetectUTF8(boolean paramBoolean)
  {
    this.__autodetectEncoding = paramBoolean;
  }

  public void setBufferSize(int paramInt)
  {
    this.__bufferSize = paramInt;
  }

  public void setControlKeepAliveReplyTimeout(int paramInt)
  {
    this.__controlKeepAliveReplyTimeout = paramInt;
  }

  public void setControlKeepAliveTimeout(long paramLong)
  {
    this.__controlKeepAliveTimeout = (paramLong * 1000L);
  }

  public void setCopyStreamListener(CopyStreamListener paramCopyStreamListener)
  {
    this.__copyStreamListener = paramCopyStreamListener;
  }

  public void setDataTimeout(int paramInt)
  {
    this.__dataTimeout = paramInt;
  }

  public boolean setFileStructure(int paramInt)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(stru(paramInt)))
    {
      this.__fileStructure = paramInt;
      return true;
    }
    return false;
  }

  public boolean setFileTransferMode(int paramInt)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(mode(paramInt)))
    {
      this.__fileTransferMode = paramInt;
      return true;
    }
    return false;
  }

  public boolean setFileType(int paramInt)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(type(paramInt)))
    {
      this.__fileType = paramInt;
      this.__fileFormat = 4;
      return true;
    }
    return false;
  }

  public boolean setFileType(int paramInt1, int paramInt2)
    throws IOException
  {
    if (FTPReply.isPositiveCompletion(type(paramInt1, paramInt2)))
    {
      this.__fileType = paramInt1;
      this.__fileFormat = paramInt2;
      return true;
    }
    return false;
  }

  public void setListHiddenFiles(boolean paramBoolean)
  {
    this.__listHiddenFiles = paramBoolean;
  }

  public boolean setModificationTime(String paramString1, String paramString2)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(mfmt(paramString1, paramString2));
  }

  public void setParserFactory(FTPFileEntryParserFactory paramFTPFileEntryParserFactory)
  {
    this.__parserFactory = paramFTPFileEntryParserFactory;
  }

  public void setRemoteVerificationEnabled(boolean paramBoolean)
  {
    this.__remoteVerificationEnabled = paramBoolean;
  }

  public void setRestartOffset(long paramLong)
  {
    if (paramLong >= 0L)
      this.__restartOffset = paramLong;
  }

  public void setUseEPSVwithIPv4(boolean paramBoolean)
  {
    this.__useEPSVwithIPv4 = paramBoolean;
  }

  public boolean storeFile(String paramString, InputStream paramInputStream)
    throws IOException
  {
    return __storeFile(14, paramString, paramInputStream);
  }

  public OutputStream storeFileStream(String paramString)
    throws IOException
  {
    return __storeFileStream(14, paramString);
  }

  public boolean storeUniqueFile(InputStream paramInputStream)
    throws IOException
  {
    return __storeFile(15, null, paramInputStream);
  }

  public boolean storeUniqueFile(String paramString, InputStream paramInputStream)
    throws IOException
  {
    return __storeFile(15, paramString, paramInputStream);
  }

  public OutputStream storeUniqueFileStream()
    throws IOException
  {
    return __storeFileStream(15, null);
  }

  public OutputStream storeUniqueFileStream(String paramString)
    throws IOException
  {
    return __storeFileStream(15, paramString);
  }

  public boolean structureMount(String paramString)
    throws IOException
  {
    return FTPReply.isPositiveCompletion(smnt(paramString));
  }

  private static class CSL
    implements CopyStreamListener
  {
    private final int currentSoTimeout;
    private final long idle;
    private int notAcked;
    private final FTPClient parent;
    private long time = System.currentTimeMillis();

    CSL(FTPClient paramFTPClient, long paramLong, int paramInt)
      throws SocketException
    {
      this.idle = paramLong;
      this.parent = paramFTPClient;
      this.currentSoTimeout = paramFTPClient.getSoTimeout();
      paramFTPClient.setSoTimeout(paramInt);
    }

    public void bytesTransferred(long paramLong1, int paramInt, long paramLong2)
    {
      paramLong1 = System.currentTimeMillis();
      if (paramLong1 - this.time > this.idle);
      try
      {
        this.parent.__noop();
        break label38;
        label28: this.notAcked += 1;
        label38: this.time = paramLong1;
        return;
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        break label28;
      }
      catch (IOException localIOException)
      {
        break label38;
      }
    }

    public void bytesTransferred(CopyStreamEvent paramCopyStreamEvent)
    {
      bytesTransferred(paramCopyStreamEvent.getTotalBytesTransferred(), paramCopyStreamEvent.getBytesTransferred(), paramCopyStreamEvent.getStreamSize());
    }

    void cleanUp()
      throws IOException
    {
      while (true)
      {
        int i = this.notAcked;
        this.notAcked = (i - 1);
        if (i <= 0)
          break;
        this.parent.__getReplyNoReport();
      }
      this.parent.setSoTimeout(this.currentSoTimeout);
    }
  }

  private static class PropertiesSingleton
  {
    static final Properties PROPERTIES;

    // ERROR //
    static
    {
      // Byte code:
      //   0: ldc 6
      //   2: ldc 15
      //   4: invokevirtual 21	java/lang/Class:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
      //   7: astore_1
      //   8: aload_1
      //   9: ifnull +30 -> 39
      //   12: new 23	java/util/Properties
      //   15: dup
      //   16: invokespecial 26	java/util/Properties:<init>	()V
      //   19: astore_0
      //   20: aload_0
      //   21: aload_1
      //   22: invokevirtual 30	java/util/Properties:load	(Ljava/io/InputStream;)V
      //   25: aload_1
      //   26: invokevirtual 35	java/io/InputStream:close	()V
      //   29: goto +12 -> 41
      //   32: astore_0
      //   33: aload_1
      //   34: invokevirtual 35	java/io/InputStream:close	()V
      //   37: aload_0
      //   38: athrow
      //   39: aconst_null
      //   40: astore_0
      //   41: aload_0
      //   42: putstatic 37	org/apache/commons/net/ftp/FTPClient$PropertiesSingleton:PROPERTIES	Ljava/util/Properties;
      //   45: return
      //   46: astore_2
      //   47: goto -22 -> 25
      //   50: astore_1
      //   51: goto -10 -> 41
      //   54: astore_1
      //   55: goto -18 -> 37
      //
      // Exception table:
      //   from	to	target	type
      //   20	25	32	finally
      //   20	25	46	java/io/IOException
      //   25	29	50	java/io/IOException
      //   33	37	54	java/io/IOException
    }
  }
}