package org.apache.commons.net.ftp.parser;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.net.ftp.Configurable;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFileEntryParser;

public class DefaultFTPFileEntryParserFactory
  implements FTPFileEntryParserFactory
{
  private static final String JAVA_IDENTIFIER = "\\p{javaJavaIdentifierStart}(\\p{javaJavaIdentifierPart})*";
  private static final String JAVA_QUALIFIED_NAME = "(\\p{javaJavaIdentifierStart}(\\p{javaJavaIdentifierPart})*\\.)+\\p{javaJavaIdentifierStart}(\\p{javaJavaIdentifierPart})*";
  private static final Pattern JAVA_QUALIFIED_NAME_PATTERN = Pattern.compile("(\\p{javaJavaIdentifierStart}(\\p{javaJavaIdentifierPart})*\\.)+\\p{javaJavaIdentifierStart}(\\p{javaJavaIdentifierPart})*");

  private FTPFileEntryParser createFileEntryParser(String paramString, FTPClientConfig paramFTPClientConfig)
  {
    if (JAVA_QUALIFIED_NAME_PATTERN.matcher(paramString).matches());
    try
    {
      Class localClass = Class.forName(paramString);
      try
      {
        FTPFileEntryParser localFTPFileEntryParser = (FTPFileEntryParser)localClass.newInstance();
      }
      catch (ExceptionInInitializerError localExceptionInInitializerError)
      {
        throw new ParserInitializationException("Error initializing parser", localExceptionInInitializerError);
      }
      catch (Exception localException)
      {
        throw new ParserInitializationException("Error initializing parser", localException);
      }
      catch (ClassCastException localClassCastException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localException.getName());
        localStringBuilder.append(" does not implement the interface ");
        localStringBuilder.append("org.apache.commons.net.ftp.FTPFileEntryParser.");
        throw new ParserInitializationException(localStringBuilder.toString(), localClassCastException);
      }
      label106: Object localObject2 = null;
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = paramString.toUpperCase(Locale.ENGLISH);
        if (((String)localObject1).indexOf("UNIX") >= 0)
          localObject1 = new UnixFTPEntryParser(paramFTPClientConfig);
        else if (((String)localObject1).indexOf("VMS") >= 0)
          localObject1 = new VMSVersioningFTPEntryParser(paramFTPClientConfig);
        else if (((String)localObject1).indexOf("WINDOWS") >= 0)
          localObject1 = createNTFTPEntryParser(paramFTPClientConfig);
        else if (((String)localObject1).indexOf("OS/2") >= 0)
          localObject1 = new OS2FTPEntryParser(paramFTPClientConfig);
        else if ((((String)localObject1).indexOf("OS/400") < 0) && (((String)localObject1).indexOf("AS/400") < 0))
        {
          if (((String)localObject1).indexOf("MVS") >= 0)
          {
            localObject1 = new MVSFTPEntryParser();
          }
          else if (((String)localObject1).indexOf("NETWARE") >= 0)
          {
            localObject1 = new NetwareFTPEntryParser(paramFTPClientConfig);
          }
          else if (((String)localObject1).indexOf("TYPE: L8") >= 0)
          {
            localObject1 = new UnixFTPEntryParser(paramFTPClientConfig);
          }
          else
          {
            paramFTPClientConfig = new StringBuilder();
            paramFTPClientConfig.append("Unknown parser type: ");
            paramFTPClientConfig.append(paramString);
            throw new ParserInitializationException(paramFTPClientConfig.toString());
          }
        }
        else
          localObject1 = createOS400FTPEntryParser(paramFTPClientConfig);
      }
      if ((localObject1 instanceof Configurable))
        ((Configurable)localObject1).configure(paramFTPClientConfig);
      return localObject1;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      break label106;
    }
  }

  private FTPFileEntryParser createNTFTPEntryParser(FTPClientConfig paramFTPClientConfig)
  {
    if ((paramFTPClientConfig != null) && ("WINDOWS".equals(paramFTPClientConfig.getServerSystemKey())))
      return new NTFTPEntryParser(paramFTPClientConfig);
    return new CompositeFileEntryParser(new FTPFileEntryParser[] { new NTFTPEntryParser(paramFTPClientConfig), new UnixFTPEntryParser(paramFTPClientConfig) });
  }

  private FTPFileEntryParser createOS400FTPEntryParser(FTPClientConfig paramFTPClientConfig)
  {
    if ((paramFTPClientConfig != null) && ("OS/400".equals(paramFTPClientConfig.getServerSystemKey())))
      return new OS400FTPEntryParser(paramFTPClientConfig);
    return new CompositeFileEntryParser(new FTPFileEntryParser[] { new OS400FTPEntryParser(paramFTPClientConfig), new UnixFTPEntryParser(paramFTPClientConfig) });
  }

  public FTPFileEntryParser createFileEntryParser(String paramString)
  {
    if (paramString == null)
      throw new ParserInitializationException("Parser key cannot be null");
    return createFileEntryParser(paramString, null);
  }

  public FTPFileEntryParser createFileEntryParser(FTPClientConfig paramFTPClientConfig)
    throws ParserInitializationException
  {
    return createFileEntryParser(paramFTPClientConfig.getServerSystemKey(), paramFTPClientConfig);
  }

  public FTPFileEntryParser createMVSEntryParser()
  {
    return new MVSFTPEntryParser();
  }

  public FTPFileEntryParser createNTFTPEntryParser()
  {
    return createNTFTPEntryParser(null);
  }

  public FTPFileEntryParser createNetwareFTPEntryParser()
  {
    return new NetwareFTPEntryParser();
  }

  public FTPFileEntryParser createOS2FTPEntryParser()
  {
    return new OS2FTPEntryParser();
  }

  public FTPFileEntryParser createOS400FTPEntryParser()
  {
    return createOS400FTPEntryParser(null);
  }

  public FTPFileEntryParser createUnixFTPEntryParser()
  {
    return new UnixFTPEntryParser();
  }

  public FTPFileEntryParser createVMSVersioningFTPEntryParser()
  {
    return new VMSVersioningFTPEntryParser();
  }
}