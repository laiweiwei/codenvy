/*
 * Copyright (c) [2012] - [2017] Red Hat, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Red Hat, Inc. - initial API and implementation
 */
package com.codenvy.api.dao.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.inject.Singleton;

/** SSHA password encryptor. Returns prefixed and hashed string in LDAP appropriate format. */
@Singleton
public class SSHAPasswordEncryptor implements PasswordEncryptor {

  private static final String SSHA_PREFIX = "{SSHA}";
  private static final SecureRandom SECURE_RANDOM = new SecureRandom();

  public byte[] encrypt(byte[] password) {
    byte[] salt = new byte[6];
    SECURE_RANDOM.nextBytes(salt);
    try {
      byte[] buff = new byte[password.length + salt.length];
      System.arraycopy(password, 0, buff, 0, password.length);
      System.arraycopy(salt, 0, buff, password.length, salt.length);

      byte[] res = new byte[20 + salt.length];
      MessageDigest md = MessageDigest.getInstance("SHA");
      md.reset();
      System.arraycopy(md.digest(buff), 0, res, 0, 20);
      System.arraycopy(salt, 0, res, 20, salt.length);

      return (SSHA_PREFIX + Base64.getEncoder().encodeToString(res)).getBytes();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e.getLocalizedMessage());
    }
  }
}
