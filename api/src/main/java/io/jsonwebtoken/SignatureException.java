/*
 * Copyright (C) 2014 jsonwebtoken.io
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
package io.jsonwebtoken;

import io.jsonwebtoken.security.SecurityException;

/**
 * Exception indicating that either calculating a signature or verifying an existing signature of a JWT failed.
 *
 * @since 0.1
 * @deprecated in favor of {@link io.jsonwebtoken.security.SignatureException}; this class will be removed before 1.0
 */
@Deprecated
public class SignatureException extends SecurityException {

    /**
     * Creates a new instance with the specified explanation message.
     *
     * @param message the message explaining why the exception is thrown.
     */
    public SignatureException(String message) {
        super(message);
    }

    /**
     * Creates a new instance with the specified explanation message and underlying cause.
     *
     * @param message the message explaining why the exception is thrown.
     * @param cause   the underlying cause that resulted in this exception being thrown.
     */
    public SignatureException(String message, Throwable cause) {
        super(message, cause);
    }
}
