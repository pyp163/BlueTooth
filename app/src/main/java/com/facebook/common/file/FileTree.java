package com.facebook.common.file;

import java.io.File;

public class FileTree
{
  public static boolean deleteContents(File paramFile)
  {
    paramFile = paramFile.listFiles();
    boolean bool2 = true;
    boolean bool1 = true;
    if (paramFile != null)
    {
      int j = paramFile.length;
      int i = 0;
      while (true)
      {
        bool2 = bool1;
        if (i >= j)
          break;
        bool1 &= deleteRecursively(paramFile[i]);
        i += 1;
      }
    }
    return bool2;
  }

  public static boolean deleteRecursively(File paramFile)
  {
    if (paramFile.isDirectory())
      deleteContents(paramFile);
    return paramFile.delete();
  }

  public static void walkFileTree(File paramFile, FileTreeVisitor paramFileTreeVisitor)
  {
    paramFileTreeVisitor.preVisitDirectory(paramFile);
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile != null)
    {
      int j = arrayOfFile.length;
      int i = 0;
      while (i < j)
      {
        File localFile = arrayOfFile[i];
        if (localFile.isDirectory())
          walkFileTree(localFile, paramFileTreeVisitor);
        else
          paramFileTreeVisitor.visitFile(localFile);
        i += 1;
      }
    }
    paramFileTreeVisitor.postVisitDirectory(paramFile);
  }
}