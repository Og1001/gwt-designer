/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.gdt.eclipse.designer.uibinder.gefTree.policy;

import com.google.gdt.eclipse.designer.uibinder.model.widgets.GridInfo.Cell;
import com.google.gdt.eclipse.designer.uibinder.model.widgets.GridInfo.Row;
import com.google.gdt.eclipse.designer.uibinder.model.widgets.WidgetInfo;

import org.eclipse.wb.core.gef.policy.validator.LayoutRequestValidators;
import org.eclipse.wb.core.gefTree.policy.ObjectLayoutEditPolicy;
import org.eclipse.wb.gef.core.EditPart;
import org.eclipse.wb.gef.core.policies.ILayoutRequestValidator;
import org.eclipse.wb.gef.core.requests.Request;
import org.eclipse.wb.gef.tree.policies.LayoutEditPolicy;

/**
 * {@link LayoutEditPolicy} for {@link Cell} operations in {@link Row}.
 * 
 * @author scheglov_ke
 * @coverage GWT.UiBinder.gefTree
 */
public final class GridRowLayoutEditPolicy extends ObjectLayoutEditPolicy<Object> {
  public static final ILayoutRequestValidator VALIDATOR = LayoutRequestValidators.or(
      LayoutRequestValidators.modelType(Cell.class),
      LayoutRequestValidators.modelType(WidgetInfo.class));
  private final Row m_row;

  ////////////////////////////////////////////////////////////////////////////
  //
  // Constructor
  //
  ////////////////////////////////////////////////////////////////////////////
  public GridRowLayoutEditPolicy(Row row) {
    super(row);
    m_row = row;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Requests
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected boolean isGoodReferenceChild(Request request, EditPart editPart) {
    return editPart.getModel() instanceof Cell;
  }

  @Override
  protected ILayoutRequestValidator getRequestValidator() {
    return VALIDATOR;
  }

  ////////////////////////////////////////////////////////////////////////////
  //
  // Commands
  //
  ////////////////////////////////////////////////////////////////////////////
  @Override
  protected void command_CREATE(Object newObject, Object referenceObject) throws Exception {
    if (newObject instanceof WidgetInfo) {
      m_row.command_CREATE((WidgetInfo) newObject, (Cell) referenceObject);
    }
  }

  @Override
  protected void command_MOVE(Object object, Object referenceObject) throws Exception {
    if (object instanceof Cell) {
      m_row.command_MOVE((Cell) object, (Cell) referenceObject);
    }
  }

  @Override
  protected void command_ADD(Object object, Object referenceObject) throws Exception {
    if (object instanceof Cell) {
      m_row.command_MOVE((Cell) object, (Cell) referenceObject);
    }
    if (object instanceof WidgetInfo) {
      m_row.command_ADD((WidgetInfo) object, (Cell) referenceObject);
    }
  }
}