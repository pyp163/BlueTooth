package bolts;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AggregateException extends Exception
{
  private static final String DEFAULT_MESSAGE = "There were multiple errors.";
  private static final long serialVersionUID = 1L;
  private List<Throwable> innerThrowables;

  public AggregateException(String paramString, List<? extends Throwable> paramList)
  {
    super(paramString, localThrowable);
    this.innerThrowables = Collections.unmodifiableList(paramList);
  }

  public AggregateException(String paramString, Throwable[] paramArrayOfThrowable)
  {
    this(paramString, Arrays.asList(paramArrayOfThrowable));
  }

  public AggregateException(List<? extends Throwable> paramList)
  {
    this("There were multiple errors.", paramList);
  }

  @Deprecated
  public Throwable[] getCauses()
  {
    return (Throwable[])this.innerThrowables.toArray(new Throwable[this.innerThrowables.size()]);
  }

  @Deprecated
  public List<Exception> getErrors()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.innerThrowables == null)
      return localArrayList;
    Iterator localIterator = this.innerThrowables.iterator();
    while (localIterator.hasNext())
    {
      Throwable localThrowable = (Throwable)localIterator.next();
      if ((localThrowable instanceof Exception))
        localArrayList.add((Exception)localThrowable);
      else
        localArrayList.add(new Exception(localThrowable));
    }
    return localArrayList;
  }

  public List<Throwable> getInnerThrowables()
  {
    return this.innerThrowables;
  }

  public void printStackTrace(PrintStream paramPrintStream)
  {
    super.printStackTrace(paramPrintStream);
    Iterator localIterator = this.innerThrowables.iterator();
    int i = -1;
    while (localIterator.hasNext())
    {
      Throwable localThrowable = (Throwable)localIterator.next();
      paramPrintStream.append("\n");
      paramPrintStream.append("  Inner throwable #");
      i += 1;
      paramPrintStream.append(Integer.toString(i));
      paramPrintStream.append(": ");
      localThrowable.printStackTrace(paramPrintStream);
      paramPrintStream.append("\n");
    }
  }

  public void printStackTrace(PrintWriter paramPrintWriter)
  {
    super.printStackTrace(paramPrintWriter);
    Iterator localIterator = this.innerThrowables.iterator();
    int i = -1;
    while (localIterator.hasNext())
    {
      Throwable localThrowable = (Throwable)localIterator.next();
      paramPrintWriter.append("\n");
      paramPrintWriter.append("  Inner throwable #");
      i += 1;
      paramPrintWriter.append(Integer.toString(i));
      paramPrintWriter.append(": ");
      localThrowable.printStackTrace(paramPrintWriter);
      paramPrintWriter.append("\n");
    }
  }
}