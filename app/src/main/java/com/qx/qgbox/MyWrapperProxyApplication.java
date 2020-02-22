package com.qx.qgbox;

import com.wrapper.proxyapplication.*;
import android.content.*;

public class MyWrapperProxyApplication extends WrapperProxyApplication
{
    @Override
    protected void initProxyApplication(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   android/content/Context.getApplicationInfo:()Landroid/content/pm/ApplicationInfo;
        //     4: getfield        android/content/pm/ApplicationInfo.sourceDir:Ljava/lang/String;
        //     7: astore_3       
        //     8: aconst_null    
        //     9: astore_2       
        //    10: new             Ljava/util/zip/ZipFile;
        //    13: dup            
        //    14: aload_3        
        //    15: invokespecial   java/util/zip/ZipFile.<init>:(Ljava/lang/String;)V
        //    18: astore_3       
        //    19: aload_3        
        //    20: astore_2       
        //    21: aload_2        
        //    22: ifnonnull       35
        //    25: invokestatic    android/os/Process.myPid:()I
        //    28: invokestatic    android/os/Process.killProcess:(I)V
        //    31: iconst_0       
        //    32: invokestatic    java/lang/System.exit:(I)V
        //    35: aload_1        
        //    36: aload_2        
        //    37: invokestatic    com/wrapper/proxyapplication/Util.PrepareSecurefiles:(Landroid/content/Context;Ljava/util/zip/ZipFile;)I
        //    40: pop            
        //    41: aload_2        
        //    42: invokevirtual   java/util/zip/ZipFile.close:()V
        //    45: getstatic       com/wrapper/proxyapplication/Util.CPUABI:Ljava/lang/String;
        //    48: ldc             "x86"
        //    50: if_acmpne       104
        //    53: new             Ljava/lang/StringBuilder;
        //    56: dup            
        //    57: aload_1        
        //    58: invokevirtual   android/content/Context.getFilesDir:()Ljava/io/File;
        //    61: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    64: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //    67: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
        //    70: ldc             "/prodexdir/"
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: getstatic       com/wrapper/proxyapplication/Util.libname:Ljava/lang/String;
        //    78: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    81: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    84: invokestatic    java/lang/System.load:(Ljava/lang/String;)V
        //    87: return         
        //    88: astore_3       
        //    89: aload_3        
        //    90: invokevirtual   java/io/IOException.printStackTrace:()V
        //    93: goto            21
        //    96: astore_2       
        //    97: aload_2        
        //    98: invokevirtual   java/io/IOException.printStackTrace:()V
        //   101: goto            45
        //   104: getstatic       com/wrapper/proxyapplication/Util.libname:Ljava/lang/String;
        //   107: invokestatic    java/lang/System.loadLibrary:(Ljava/lang/String;)V
        //   110: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  10     19     88     96     Ljava/io/IOException;
        //  41     45     96     104    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at us.deathmarine.luyten.FileSaver.doSaveJarDecompiled(FileSaver.java:192)
        //     at us.deathmarine.luyten.FileSaver.access$300(FileSaver.java:45)
        //     at us.deathmarine.luyten.FileSaver$4.run(FileSaver.java:112)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
    }
}
