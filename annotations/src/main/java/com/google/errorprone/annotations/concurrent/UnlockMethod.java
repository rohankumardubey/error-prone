/*
 * Copyright 2014 The Error Prone Authors.
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

package com.google.errorprone.annotations.concurrent;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The method to which this annotation is applied releases one or more locks. The caller must hold
 * the locks when the function is entered, and will not hold them when it completes.
 *
 * <p>This annotation does not apply to built-in (synchronization) locks, which cannot be released
 * without being acquired in the same method.
 *
 * <p>The arguments determine which locks the annotated method releases:
 *
 * <ul>
 *   <li><code>field-name</code>: The lock is referenced by the final instance field specified by
 *       <em>field-name</em>.
 *   <li><code>class-name.this.field-name</code>: For inner classes, it may be necessary to
 *       disambiguate 'this'; the <em>class-name.this</em> designation allows you to specify which
 *       'this' reference is intended.
 *   <li><code>class-name.field-name</code>: The lock is referenced by the static final field
 *       specified by <em>class-name.field-name</em>.
 *   <li><code>method-name()</code>: The lock object is returned by calling the named nullary
 *       method.
 * </ul>
 *
 * @deprecated the correctness of this annotation is not enforced; it will soon be removed.
 */
@Target(METHOD)
@Retention(CLASS)
@Deprecated
public @interface UnlockMethod {
  String[] value();
}
