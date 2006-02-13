/******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/

package org.eclipse.gmf.runtime.common.core.command;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.gmf.runtime.common.core.internal.command.BaseModificationValidator;

/**
 * Static utility that approves the modification of files using an
 * {@link IModificationValidator}.
 * <P>
 * The modification validator can be set exactly once using
 * {@link #setModificationValidator(IModificationValidator)} before the first
 * request to validate files.
 * 
 * @author ldamus
 */
public class FileModificationValidator {

	private static IModificationValidator validator;

	private FileModificationValidator() {
		// not to be instantiated
	}

	/**
	 * Set the modification validator to be used to check that files can be
	 * modified.
	 * <P>
	 * This method may only be called once, and must be called before any
	 * request to validate files. Attempts to set the validator will be ignored
	 * after it has been already set, or after a default one has been created.
	 * 
	 * @param validator
	 *            the modification validator
	 */
	public static void setModificationValidator(IModificationValidator v) {
		if (validator == null) {
			validator = v;
		}
	}

	/**
	 * Gets the validator.
	 * 
	 * @return the validator
	 */
	private static IModificationValidator getValidator() {
		if (validator == null) {
			validator = new BaseModificationValidator();
		}
		return validator;
	}

	/**
	 * Checks that the <code>files</code> may be modified.
	 * 
	 * @return the approval status
	 */
	public static IStatus approveFileModification(IFile[] files) {
		return getValidator().validateEdit(files);
	}

}
