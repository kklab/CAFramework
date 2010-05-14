package org.kklab.ca.framework;

import java.util.Vector;

public abstract class Rule<E> {
	public abstract E adapt(final E value, final Vector<Site> neighbors);
}
