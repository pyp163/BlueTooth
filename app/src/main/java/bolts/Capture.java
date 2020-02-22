package bolts;

public class Capture<T>
{
  private T value;

  public Capture()
  {
  }

  public Capture(T paramT)
  {
    this.value = paramT;
  }

  public T get()
  {
    return this.value;
  }

  public void set(T paramT)
  {
    this.value = paramT;
  }
}