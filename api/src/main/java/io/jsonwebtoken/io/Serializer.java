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
package io.jsonwebtoken.io;

/**
 * A {@code Serializer} is able to convert a Java object into a formatted data byte array.  It is expected this data
 * can be reconstituted back into a Java object with a matching {@link Deserializer}.
 *
 * @param <T> The type of object to serialize.
 * @since 0.10.0
 */
public interface Serializer<T> {

    /**
     * Convert the specified Java object into a formatted data byte array.
     *
     * @param t the object to serialize
     * @return the serialized byte array representing the specified object.
     * @throws SerializationException if there is a problem converting the object to a byte array.
     */
    byte[] serialize(T t) throws SerializationException;

}
