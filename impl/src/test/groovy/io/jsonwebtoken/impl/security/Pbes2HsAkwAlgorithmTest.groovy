/*
 * Copyright (C) 2020 jsonwebtoken.io
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

import io.jsonwebtoken.JweHeader
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Algorithms
import io.jsonwebtoken.security.KeyRequest
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.Password
import org.junit.Test

import static org.junit.Assert.assertEquals
import static org.junit.Assert.fail

@SuppressWarnings('SpellCheckingInspection')
class Pbes2HsAkwAlgorithmTest {

    private static Password KEY = Keys.forPassword("12345678".toCharArray())
    private static List<Pbes2HsAkwAlgorithm> ALGS = [Algorithms.key.PBES2_HS256_A128KW,
                                                     Algorithms.key.PBES2_HS384_A192KW,
                                                     Algorithms.key.PBES2_HS512_A256KW] as List<Pbes2HsAkwAlgorithm>

    @Test
    void testInsufficientIterations() {
        for (Pbes2HsAkwAlgorithm alg : ALGS) {
            int iterations = 50 // must be 1000 or more
            JweHeader header = Jwts.headerBuilder().setPbes2Count(iterations).build() as JweHeader
            KeyRequest<Password> req = new DefaultKeyRequest<>(KEY, null, null, header, Algorithms.enc.A256GCM)
            try {
                alg.getEncryptionKey(req)
                fail()
            } catch (IllegalArgumentException iae) {
                assertEquals Pbes2HsAkwAlgorithm.MIN_ITERATIONS_MSG_PREFIX + iterations, iae.getMessage()
            }
        }
    }

    // for manual/developer testing only.  Takes a long time and there is no deterministic output to assert
    /*
    @Test
    void test() {

        def alg = Algorithms.key.PBES2_HS256_A128KW

        int desiredMillis = 100
        int iterations = Algorithms.key.estimateIterations(alg, desiredMillis)
        println "Estimated iterations: $iterations"

        int tries = 30
        int skip = 6
        //double scale = 0.5035246727

        def password = 'hellowor'.toCharArray()
        def header = new DefaultJweHeader().setPbes2Count(iterations)
        def key = Keys.forPassword(password)
        def req = new DefaultKeyRequest(null, null, key, header, Algorithms.enc.A128GCM)
        int sum = 0
        for (int i = 0; i < tries; i++) {
            long start = System.currentTimeMillis()
            alg.getEncryptionKey(req)
            long end = System.currentTimeMillis()
            long duration = end - start
            if (i >= skip) {
                sum += duration
            }
            println "Try $i: ${alg.id} took $duration millis"
        }
        long avg = Math.round(sum / (tries - skip))
        println "Average duration: $avg"
        println "scale factor: ${desiredMillis / avg}"
    }
     */
}
