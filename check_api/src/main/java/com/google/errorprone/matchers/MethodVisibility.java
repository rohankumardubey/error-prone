/*
 * Copyright 2012 The Error Prone Authors.
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

package com.google.errorprone.matchers;

import static com.google.errorprone.util.ASTHelpers.getSymbol;

import com.google.errorprone.VisitorState;
import com.sun.source.tree.MethodTree;
import java.util.Set;
import javax.lang.model.element.Modifier;

/**
 * A matcher for method visibility (public, private, protected, or default).
 *
 * @author eaftan@google.com (Eddie Aftandilian)
 */
public class MethodVisibility implements Matcher<MethodTree> {

  private final Visibility visibility;

  public MethodVisibility(Visibility visibility) {
    this.visibility = visibility;
  }

  @Override
  public boolean matches(MethodTree t, VisitorState state) {
    Set<Modifier> modifiers = getSymbol(t).getModifiers();
    if (visibility == Visibility.DEFAULT) {
      return !modifiers.contains(Visibility.PUBLIC.toModifier())
          && !modifiers.contains(Visibility.PROTECTED.toModifier())
          && !modifiers.contains(Visibility.PRIVATE.toModifier());
    } else {
      return modifiers.contains(visibility.toModifier());
    }
  }

  /** The visibility of a member. */
  public enum Visibility {
    PUBLIC(Modifier.PUBLIC),
    PROTECTED(Modifier.PROTECTED),
    DEFAULT(null),
    PRIVATE(Modifier.PRIVATE);

    private final Modifier correspondingModifier;

    Visibility(Modifier correspondingModifier) {
      this.correspondingModifier = correspondingModifier;
    }

    public Modifier toModifier() {
      return correspondingModifier;
    }
  }
}
