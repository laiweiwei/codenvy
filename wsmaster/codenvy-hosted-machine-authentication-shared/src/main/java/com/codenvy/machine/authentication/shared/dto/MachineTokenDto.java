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
package com.codenvy.machine.authentication.shared.dto;

import org.eclipse.che.dto.shared.DTO;

/**
 * Representation of machine token, that needed for communication between workspace master and
 * workspace agents.
 *
 * @author Max Shaposhnik (mshaposhnik@codenvy.com)
 */
@DTO
public interface MachineTokenDto {

  String getUserId();

  void setUserId(String userId);

  MachineTokenDto withUserId(String userId);

  String getWorkspaceId();

  void setWorkspaceId(String workspaceId);

  MachineTokenDto withWorkspaceId(String workspaceId);

  String getMachineToken();

  void setMachineToken(String machineToken);

  MachineTokenDto withMachineToken(String machineToken);
}
