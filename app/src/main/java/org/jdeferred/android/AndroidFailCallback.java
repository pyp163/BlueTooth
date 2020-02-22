package org.jdeferred.android;

import org.jdeferred.FailCallback;

public abstract interface AndroidFailCallback<F> extends FailCallback<F>, AndroidExecutionScopeable
{
}