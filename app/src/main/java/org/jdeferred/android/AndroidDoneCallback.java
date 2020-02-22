package org.jdeferred.android;

import org.jdeferred.DoneCallback;

public abstract interface AndroidDoneCallback<D> extends DoneCallback<D>, AndroidExecutionScopeable
{
}