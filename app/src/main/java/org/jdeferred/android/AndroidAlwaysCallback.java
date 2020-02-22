package org.jdeferred.android;

import org.jdeferred.AlwaysCallback;

public abstract interface AndroidAlwaysCallback<D, R> extends AlwaysCallback<D, R>, AndroidExecutionScopeable
{
}