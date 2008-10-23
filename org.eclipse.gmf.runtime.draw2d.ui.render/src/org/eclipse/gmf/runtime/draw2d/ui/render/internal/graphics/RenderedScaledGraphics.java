/******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.draw2d.ui.render.internal.graphics;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.DrawableRenderedImage;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.RenderHelper;
import org.eclipse.gmf.runtime.draw2d.ui.render.internal.RenderingListener;

/**
 * @author sshaw
 * @canBeSeenBy org.eclipse.gmf.runtime.draw2d.ui.render.*
 * 
 * Subclass to allow implementation of the DrawableRenderedImage interface
 */
public class RenderedScaledGraphics
	extends org.eclipse.gmf.runtime.draw2d.ui.internal.graphics.ScaledGraphics
	implements DrawableRenderedImage {

	boolean allowDelayRender = false;
	Dimension maximumRenderSize = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.draw2d.ui.render.internal.DrawableRenderedImage#allowDelayRender()
	 */
	public boolean shouldAllowDelayRender() {
		return allowDelayRender;
	}

	/**
	 * Constructs a new ScaledGraphics based on the given Graphics object.
	 * 
	 * @param g
	 *            the base graphics object
	 */
	public RenderedScaledGraphics(Graphics g) {
		this(g, false, null);
	}

	/**
	 * Constructs a new ScaledGraphics based on the given Graphics object.
	 * 
	 * @param g
	 *            the base graphics object
	 * @param allowDelayRender
	 */
	public RenderedScaledGraphics(Graphics g, boolean allowDelayRender, Dimension maximumRenderSize) {
		super(g);
		this.allowDelayRender = allowDelayRender;
		this.maximumRenderSize = maximumRenderSize;
	}

	private double scale = 1.0;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.Graphics#scale(double)
	 */
	public void scale(double amount) {
		scale = amount;
		super.scale(amount);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gmf.runtime.draw2d.ui.render.internal.DrawableRenderedImage#drawRenderedImage(org.eclipse.gmf.runtime.draw2d.ui.render.RenderedImage,
	 *      org.eclipse.draw2d.geometry.Rectangle,
	 *      org.eclipse.gmf.runtime.draw2d.ui.render.RenderingListener)
	 */
	public RenderedImage drawRenderedImage(RenderedImage srcImage,
			Rectangle rect, RenderingListener listener) {
		return RenderHelper.getInstance(scale, true, shouldAllowDelayRender(), getMaximumRenderSize())
			.drawRenderedImage(getGraphics(), srcImage, rect, listener);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.draw2d.ui.render.internal.DrawableRenderedImage#getMaximumRenderSize()
	 */
	public Dimension getMaximumRenderSize() {
		// TODO Auto-generated method stub
		return maximumRenderSize;
	}
}