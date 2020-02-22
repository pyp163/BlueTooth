package com.qx.qgbox.gamemouse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IniFile
{
  private String charSet = "UTF-8";
  private File file = null;
  private String line_separator = "\n";
  private Map<String, Section> sections = new LinkedHashMap();

  public IniFile()
  {
  }

  public IniFile(File paramFile)
  {
    this.file = paramFile;
    initFromFile(paramFile);
  }

  public IniFile(InputStream paramInputStream)
  {
    initFromInputStream(paramInputStream);
  }

  private void initFromFile(File paramFile)
  {
    try
    {
      paramFile = new BufferedReader(new FileReader(paramFile));
      if (paramFile != null)
        toIniFile(paramFile);
      return;
    }
    catch (FileNotFoundException paramFile)
    {
    }
  }

  private void initFromInputStream(InputStream paramInputStream)
  {
    try
    {
      toIniFile(new BufferedReader(new InputStreamReader(paramInputStream, this.charSet)));
      return;
    }
    catch (UnsupportedEncodingException paramInputStream)
    {
      paramInputStream.printStackTrace();
    }
  }

  private void saveConfig(BufferedWriter paramBufferedWriter)
  {
    for (int i = 0; ; i = 1)
      try
      {
        if ((this.line_separator != null) && (!this.line_separator.trim().equals("")))
        {
          Iterator localIterator = this.sections.values().iterator();
          while (localIterator.hasNext())
          {
            Object localObject1 = (Section)localIterator.next();
            Object localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("[");
            ((StringBuilder)localObject2).append(((Section)localObject1).getName());
            ((StringBuilder)localObject2).append("]");
            paramBufferedWriter.write(((StringBuilder)localObject2).toString());
            if (i != 0)
              paramBufferedWriter.write(this.line_separator);
            else
              paramBufferedWriter.newLine();
            localObject1 = ((Section)localObject1).getValues().entrySet().iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject2 = (Map.Entry)((Iterator)localObject1).next();
              paramBufferedWriter.write((String)((Map.Entry)localObject2).getKey());
              paramBufferedWriter.write("=");
              paramBufferedWriter.write(((Map.Entry)localObject2).getValue().toString());
              if (i != 0)
                paramBufferedWriter.write(this.line_separator);
              else
                paramBufferedWriter.newLine();
            }
          }
          paramBufferedWriter.close();
          return;
        }
      }
      catch (IOException paramBufferedWriter)
      {
        paramBufferedWriter.printStackTrace();
        return;
      }
  }

  private void toIniFile(BufferedReader paramBufferedReader)
  {
    Pattern localPattern = Pattern.compile("^\\[.*\\]$");
    Section localSection = null;
    try
    {
      while (true)
      {
        Object localObject = paramBufferedReader.readLine();
        if (localObject == null)
          break;
        if (localPattern.matcher((CharSequence)localObject).matches())
        {
          localObject = ((String)localObject).trim();
          localSection = new Section();
          Section.access$002(localSection, ((String)localObject).substring(1, ((String)localObject).length() - 1));
          this.sections.put(localSection.name, localSection);
        }
        else
        {
          localObject = ((String)localObject).split("=");
          if ((localObject.length == 2) && (localSection != null))
            localSection.set(localObject[0], localObject[1]);
        }
      }
      paramBufferedReader.close();
      return;
    }
    catch (IOException paramBufferedReader)
    {
      paramBufferedReader.printStackTrace();
    }
  }

  public boolean exists()
  {
    return this.file.exists();
  }

  public Section get(String paramString)
  {
    return (Section)this.sections.get(paramString);
  }

  public Object get(String paramString1, String paramString2)
  {
    return get(paramString1, paramString2, null);
  }

  public Object get(String paramString1, String paramString2, String paramString3)
  {
    paramString1 = (Section)this.sections.get(paramString1);
    if (paramString1 != null)
    {
      paramString1 = paramString1.get(paramString2);
      if (paramString1 != null)
      {
        if (paramString1.toString().trim().equals(""))
          return paramString3;
        return paramString1;
      }
      return paramString3;
    }
    return null;
  }

  public void load(File paramFile)
  {
    this.file = paramFile;
    initFromFile(paramFile);
  }

  public void load(InputStream paramInputStream)
  {
    initFromInputStream(paramInputStream);
  }

  public void remove(String paramString)
  {
    this.sections.remove(paramString);
  }

  public void remove(String paramString1, String paramString2)
  {
    paramString1 = (Section)this.sections.get(paramString1);
    if (paramString1 != null)
      paramString1.getValues().remove(paramString2);
  }

  public void save()
  {
    save(this.file);
  }

  public void save(File paramFile)
  {
    try
    {
      saveConfig(new BufferedWriter(new FileWriter(paramFile)));
      return;
    }
    catch (IOException paramFile)
    {
      paramFile.printStackTrace();
    }
  }

  public void save(OutputStream paramOutputStream)
  {
    try
    {
      saveConfig(new BufferedWriter(new OutputStreamWriter(paramOutputStream, this.charSet)));
      return;
    }
    catch (UnsupportedEncodingException paramOutputStream)
    {
      paramOutputStream.printStackTrace();
    }
  }

  public void set(String paramString1, String paramString2, Object paramObject)
  {
    Section localSection2 = (Section)this.sections.get(paramString1);
    Section localSection1 = localSection2;
    if (localSection2 == null)
      localSection1 = new Section();
    Section.access$002(localSection1, paramString1);
    localSection1.set(paramString2, paramObject);
    this.sections.put(paramString1, localSection1);
  }

  public void setCharSet(String paramString)
  {
    this.charSet = paramString;
  }

  public void setLineSeparator(String paramString)
  {
    this.line_separator = paramString;
  }

  public class Section
  {
    private String name;
    private Map<String, Object> values = new LinkedHashMap();

    public Section()
    {
    }

    public Object get(String paramString)
    {
      return this.values.get(paramString);
    }

    public String getName()
    {
      return this.name;
    }

    public Map<String, Object> getValues()
    {
      return this.values;
    }

    public void set(String paramString, Object paramObject)
    {
      this.values.put(paramString, paramObject);
    }

    public void setName(String paramString)
    {
      this.name = paramString;
    }
  }
}
