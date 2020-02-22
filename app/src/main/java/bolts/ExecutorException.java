package bolts;

public class ExecutorException extends RuntimeException
{
  public ExecutorException(Exception paramException)
  {
    super("An exception was thrown by an Executor", paramException);
  }
}