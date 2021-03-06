/*
 *
 * Copyright (C) 2007-2014 Licensed to the Comunes Association (CA) under
 * one or more contributor license agreements (see COPYRIGHT for details).
 * The CA licenses this file to you under the GNU Affero General Public
 * License version 3, (the "License"); you may not use this file except in
 * compliance with the License. This file is part of kune.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package cc.kune.common.client.errors;

import cc.kune.common.client.log.Log;

// TODO: Auto-generated Javadoc
/**
 * The Class NotImplementedException.
 *
 * @author vjrj@ourproject.org (Vicente J. Ruiz Jurado)
 */
public class NotImplementedException extends RuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1327164226202923181L;

  /**
   * Instantiates a new not implemented exception.
   */
  public NotImplementedException() {
    super();
    Log.error("NotImplementedException");
  }

  /**
   * Instantiates a new not implemented exception.
   *
   * @param text the text
   */
  public NotImplementedException(final String text) {
    super(text);
    Log.error(text);
  }

  /**
   * Instantiates a new not implemented exception.
   *
   * @param text the text
   * @param cause the cause
   */
  public NotImplementedException(final String text, final Throwable cause) {
    super(text, cause);
    Log.error(text, cause);
  }

  /**
   * Instantiates a new not implemented exception.
   *
   * @param cause the cause
   */
  public NotImplementedException(final Throwable cause) {
    super(cause);
    Log.error("NotImplementedException", cause);
  }

}
