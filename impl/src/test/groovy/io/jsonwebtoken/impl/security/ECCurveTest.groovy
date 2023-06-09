/*
 * Copyright (C) 2022 jsonwebtoken.io
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
package io.jsonwebtoken.impl.security

import org.junit.Test

import java.security.interfaces.ECPublicKey
import java.security.spec.ECPoint

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

class ECCurveTest {

    @Test
    void testContainsTrue() {
        ECCurve curve = (ECCurve) Curves.P_256
        def pair = curve.keyPairBuilder().build()
        ECPublicKey ecPub = (ECPublicKey) pair.getPublic()
        assertTrue(curve.contains(ecPub.getW()))
    }

    @Test
    void testContainsFalse() {
        assertFalse(((ECCurve) Curves.P_256).contains(new ECPoint(BigInteger.ONE, BigInteger.ONE)))
    }
}
