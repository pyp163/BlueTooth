package jonathanfinerty.once;

public class Amount
{
  public static CountChecker exactly(int paramInt)
  {
    return new CountChecker()
    {
      public boolean check(int paramAnonymousInt)
      {
        return this.val$numberOfTimes == paramAnonymousInt;
      }
    };
  }

  public static CountChecker lessThan(int paramInt)
  {
    return new CountChecker()
    {
      public boolean check(int paramAnonymousInt)
      {
        return paramAnonymousInt < this.val$numberOfTimes;
      }
    };
  }

  public static CountChecker moreThan(int paramInt)
  {
    return new CountChecker()
    {
      public boolean check(int paramAnonymousInt)
      {
        return paramAnonymousInt > this.val$numberOfTimes;
      }
    };
  }
}