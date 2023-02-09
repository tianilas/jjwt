package io.jsonwebtoken.security;

import io.jsonwebtoken.Identifiable;
import io.jsonwebtoken.JweHeader;
import io.jsonwebtoken.lang.Assert;
import io.jsonwebtoken.lang.Classes;
import io.jsonwebtoken.lang.Registry;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collection;

public final class Algorithms {

    /**
     * Prevent instantiation
     */
    private Algorithms() {
        throw new AssertionError("io.jsonwebtoken.security.Algorithms may not be instantiated.");
    }

    /**
     * Registry of all standard JWE Key Management Algorithms defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4">JWA (RFC 7518) Key Management Algorithms</a>
     * and formalized in <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-7.1">
     * JSON Web Signature and Encryption Algorithms Registry</a>.  The variable is named &quot;{@code alg}&quot; for two
     * reasons:
     * <ul>
     *     <li>The variable name equals the name of the JWE header that contains the key management algorithm
     *     {@link KeyAlgorithm#getId() identifier} values, self-documenting its purpose in referenced code.</li>
     *     <li>It is short and simpler to reference {@code Jwe.alg.A256GCMKW} in application
     *     code instead of the more verbose static class variable alternative of, say,
     *     {@code StandardKeyAlgorithms.A256GCMKW}. For example:
     * <blockquote><pre>Jwts.builder()...
     *   .encryptWith(secretKey, Jwe.alg.A256GCMKW, Jwe.enc.A256GCM)
     *   //.encryptWith(secretKey, StandardKeyAlgorithms.A256GCMKW, StandardEncryptionAlgorithms...
     *   .build();</pre></blockquote>
     *     </li>
     * </ul>
     *
     * @see HashAlgorithm
     * @see HashAlgorithm#getId()
     */
    public static final StandardHashAlgorithms hash = new StandardHashAlgorithms();

    /**
     * Registry of all standard JWE Encryption Algorithms defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-5.1">JWA (RFC 7518) Encryption Algorithms</a> and
     * formalized in the
     * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-7.1">
     * JSON Web Signature and Encryption Algorithms Registry</a>.  The variable is named &quot;{@code enc}&quot; for two
     * reasons:
     * <ul>
     *     <li>The variable name equals the name of the JWE header that contains the encryption algorithm
     *     {@link AeadAlgorithm#getId() identifier} values, self-documenting its purpose in referenced code.</li>
     *     <li>It is short and simpler to reference {@code Jwe.enc.A256GCM} in application
     *     code instead of the more verbose static class variable alternative of, say,
     *     {@code Jwe.StandardEncryptionAlgorithms.A256GCM}. For example:
     * <blockquote><pre>Jwts.builder()...
     *   .encryptWith(secretKey, Algorithms.enc.A256GCM)
     *   //.encryptWith(secretKey, StandardEncryptionAlgorithms.A256GCM
     *   .build();</pre></blockquote>
     *     </li>
     * </ul>
     *
     * @see AeadAlgorithm
     * @see AeadAlgorithm#getId()
     */
    public static final StandardEncryptionAlgorithms enc = new StandardEncryptionAlgorithms();

    /**
     * Registry of all standard JWE Key Management Algorithms defined in
     * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4">JWA (RFC 7518) Key Management Algorithms</a>
     * and formalized in <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-7.1">
     * JSON Web Signature and Encryption Algorithms Registry</a>.  The variable is named &quot;{@code alg}&quot; for two
     * reasons:
     * <ul>
     *     <li>The variable name equals the name of the JWE header that contains the key management algorithm
     *     {@link KeyAlgorithm#getId() identifier} values, self-documenting its purpose in referenced code.</li>
     *     <li>It is short and simpler to reference {@code Jwe.alg.A256GCMKW} in application
     *     code instead of the more verbose static class variable alternative of, say,
     *     {@code StandardKeyAlgorithms.A256GCMKW}. For example:
     * <blockquote><pre>Jwts.builder()...
     *   .encryptWith(secretKey, Jwe.alg.A256GCMKW, Jwe.enc.A256GCM)
     *   //.encryptWith(secretKey, StandardKeyAlgorithms.A256GCMKW, StandardEncryptionAlgorithms...
     *   .build();</pre></blockquote>
     *     </li>
     * </ul>
     *
     * @see KeyAlgorithm
     * @see KeyAlgorithm#getId()
     */
    public static final StandardKeyAlgorithms key = new StandardKeyAlgorithms();

    /**
     * Constant definitions and utility methods for JWT-standard hash algorithms as specified by
     * <a href="https://www.rfc-editor.org/rfc/rfc9278#name-hash-algorithms-identifier">RFC 9278 (JWK Thumbprint URI),
     * Section 4</a>. Per that RFC, each hash algorithm corresponds to a uniquely-identified algorithm defined in the
     * <a href="https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg">IANA Named
     * Information Hash Algorithm Registry</a>.
     *
     * @see HashAlgorithm
     * @see #values()
     * @see #find(String)
     * @see #get(String)
     * @since JJWT_RELEASE_VERSION
     */
    public static final class StandardHashAlgorithms implements Registry<String, HashAlgorithm> {

        private static final Registry<String, HashAlgorithm> IMPL =
                Classes.newInstance("io.jsonwebtoken.impl.security.HashAlgorithmsBridge");

        /**
         * <a href="https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg">IANA
         * hash algorithm</a> with an {@link Identifiable#getId() id} (aka IANA &quot;{@code Hash Name String}&quot;)
         * value of {@code sha-256}.  Per the IANA registry, this algorithm is defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc6920.html">RFC 6920</a> and is the same as the
         * native Java JCA {@code SHA-256} {@code MessageDigest} algorithm.
         */
        public final HashAlgorithm SHA256 = get("sha-256");

        /**
         * Prevent external instantiation.
         */
        private StandardHashAlgorithms() {
        }

        /**
         * Returns common
         * <a href="https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg">IANA Hash
         * Algorithms</a> as an unmodifiable collection.
         *
         * @return common
         * <a href="https://www.iana.org/assignments/named-information/named-information.xhtml#hash-alg">IANA Hash
         * Algorithms</a> as an unmodifiable collection.
         */
        public Collection<HashAlgorithm> values() {
            return IMPL.values();
        }

        /**
         * Returns the {@code HashAlgorithm} instance with the specified IANA algorithm {@code id}, or throws an
         * {@link IllegalArgumentException} if there is no supported algorithm for the specified {@code id}. The
         * {@code id} parameter is expected to equal one of the string values in the <b>{@code Hash Name String}</b>
         * column within the
         * <a href="https://www.iana.org/assignments/named-information/named-information.xhtml">IANA Named Information
         * Hash Algorithm Registry</a> table. If a supported instance result is not mandatory, consider using the
         * {@link #find(String)} method instead.
         *
         * @param id an IANA {@code Hash Name String} hash algorithm identifier.
         * @return the associated {@code HashAlgorithm} instance.
         * @throws IllegalArgumentException if there is no supported algorithm for the specified identifier.
         * @see #find(String)
         * @see <a href="https://www.iana.org/assignments/named-information/named-information.xhtml">IANA Named
         * Information Hash Algorithm Registry</a>
         */
        @Override
        public HashAlgorithm get(String id) {
            return IMPL.get(id);
        }

        /**
         * Returns the {@code HashAlgorithm} instance with the specified IANA algorithm {@code id}, or {@code null} if
         * the specified {@code id} cannot be found. The {@code id} parameter is expected to equal one of the string
         * values in the <b>{@code Hash Name String}</b> column within the
         * <a href="https://www.iana.org/assignments/named-information/named-information.xhtml">IANA Named Information
         * Hash Algorithm Registry</a> table.  If a standard instance must be resolved, consider using the
         * {@link #get(String)} method instead.
         *
         * @param id an IANA {@code Hash Name String} hash algorithm identifier
         * @return the associated {@code HashAlgorithm} instance if found or {@code null} otherwise.
         * @see <a href="https://www.iana.org/assignments/named-information/named-information.xhtml">IANA Named Information
         * Hash Algorithm Registry</a>
         * @see #get(String)
         */
        @Override
        public HashAlgorithm find(String id) {
            return IMPL.find(id);
        }
    }

    /**
     * Constant definitions and utility methods for all standard
     * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-5.1">JWA (RFC 7518) Encryption Algorithms</a>.
     *
     * @see AeadAlgorithm
     * @see #values()
     * @see #get(String)
     * @see #find(String)
     * @since JJWT_RELEASE_VERSION
     */
    public static final class StandardEncryptionAlgorithms implements Registry<String, AeadAlgorithm> {

        private static final Registry<String, AeadAlgorithm> IMPL =
                Classes.newInstance("io.jsonwebtoken.impl.security.EncryptionAlgorithmsBridge");

        /**
         * {@code AES_128_CBC_HMAC_SHA_256} authenticated encryption algorithm as defined by
         * <a href="https://tools.ietf.org/html/rfc7518#section-5.2.3">RFC 7518, Section 5.2.3</a>.  This algorithm
         * requires a 256-bit (32 byte) key.
         */
        public final AeadAlgorithm A128CBC_HS256 = get("A128CBC-HS256");

        /**
         * {@code AES_192_CBC_HMAC_SHA_384} authenticated encryption algorithm, as defined by
         * <a href="https://tools.ietf.org/html/rfc7518#section-5.2.4">RFC 7518, Section 5.2.4</a>. This algorithm
         * requires a 384-bit (48 byte) key.
         */
        public final AeadAlgorithm A192CBC_HS384 = get("A192CBC-HS384");

        /**
         * {@code AES_256_CBC_HMAC_SHA_512} authenticated encryption algorithm, as defined by
         * <a href="https://tools.ietf.org/html/rfc7518#section-5.2.5">RFC 7518, Section 5.2.5</a>.  This algorithm
         * requires a 512-bit (64 byte) key.
         */
        public final AeadAlgorithm A256CBC_HS512 = get("A256CBC-HS512");

        /**
         * &quot;AES GCM using 128-bit key&quot; as defined by
         * <a href="https://tools.ietf.org/html/rfc7518#section-5.3">RFC 7518, Section 5.3</a><b><sup>1</sup></b>.  This
         * algorithm requires a 128-bit (16 byte) key.
         *
         * <p><b><sup>1</sup></b> Requires Java 8 or a compatible JCA Provider (like BouncyCastle) in the runtime
         * classpath. If on Java 7 or earlier, BouncyCastle will be used automatically if found in the runtime
         * classpath.</p>
         */
        public final AeadAlgorithm A128GCM = get("A128GCM");

        /**
         * &quot;AES GCM using 192-bit key&quot; as defined by
         * <a href="https://tools.ietf.org/html/rfc7518#section-5.3">RFC 7518, Section 5.3</a><b><sup>1</sup></b>.  This
         * algorithm requires a 192-bit (24 byte) key.
         *
         * <p><b><sup>1</sup></b> Requires Java 8 or a compatible JCA Provider (like BouncyCastle) in the runtime
         * classpath. If on Java 7 or earlier, BouncyCastle will be used automatically if found in the runtime
         * classpath.</p>
         */
        public final AeadAlgorithm A192GCM = get("A192GCM");

        /**
         * &quot;AES GCM using 256-bit key&quot; as defined by
         * <a href="https://tools.ietf.org/html/rfc7518#section-5.3">RFC 7518, Section 5.3</a><b><sup>1</sup></b>.  This
         * algorithm requires a 256-bit (32 byte) key.
         *
         * <p><b><sup>1</sup></b> Requires Java 8 or a compatible JCA Provider (like BouncyCastle) in the runtime
         * classpath. If on Java 7 or earlier, BouncyCastle will be used automatically if found in the runtime
         * classpath.</p>
         */
        public final AeadAlgorithm A256GCM = get("A256GCM");

        /**
         * Prevent external instantiation.
         */
        private StandardEncryptionAlgorithms() {
        }

        /**
         * Returns all JWE-standard AEAD encryption algorithms as an unmodifiable collection.
         *
         * @return all JWE-standard AEAD encryption algorithms as an unmodifiable collection.
         */
        public Collection<AeadAlgorithm> values() {
            return IMPL.values();
        }

        /**
         * Returns the JWE-standard Encryption Algorithm with the specified
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-5.1">{@code enc} algorithm identifier</a> or
         * throws an {@link IllegalArgumentException} if there is no JWE-standard algorithm for the specified
         * {@code id}.  If a JWE-standard instance result is not mandatory, consider using the {@link #find(String)}
         * method instead.
         *
         * @param id a JWE standard {@code enc} algorithm identifier
         * @return the associated Encryption Algorithm instance.
         * @throws IllegalArgumentException if there is no JWE-standard algorithm for the specified identifier.
         * @see #find(String)
         * @see <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-5.1">RFC 7518, Section 5.1</a>
         */
        @Override
        public AeadAlgorithm get(String id) {
            return IMPL.get(id);
        }

        /**
         * Returns the JWE Encryption Algorithm with the specified
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-5.1">{@code enc} algorithm identifier</a> or
         * {@code null} if a JWE-standard algorithm for the specified {@code id} cannot be found.  If a JWE-standard
         * instance must be resolved, consider using the {@link #get(String)} method instead.
         *
         * @param id a JWE standard {@code enc} algorithm identifier
         * @return the associated standard Encryption Algorithm instance or {@code null} otherwise.
         * @see #get(String)
         * @see <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-5.1">RFC 7518, Section 5.1</a>
         */
        @Override
        public AeadAlgorithm find(String id) {
            return IMPL.find(id);
        }
    }

    /**
     * Constant definitions and utility methods for all
     * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4">JWA (RFC 7518) Key Management Algorithms</a>.
     *
     * @see #values()
     * @see #find(String)
     * @see #get(String)
     * @since JJWT_RELEASE_VERSION
     */
    public static final class StandardKeyAlgorithms implements Registry<String, KeyAlgorithm<?, ?>> {

        private static final Registry<String, KeyAlgorithm<?, ?>> REGISTRY =
                Classes.newInstance("io.jsonwebtoken.impl.security.KeyAlgorithmsBridge");

        //prevent instantiation
        private StandardKeyAlgorithms() {
        }

        /**
         * Returns all JWA-standard
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4">Key Management Algorithms</a> as an
         * unmodifiable collection.
         *
         * @return all JWA-standard
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4">Key Management Algorithms</a> as an
         * unmodifiable collection.
         */
        public Collection<KeyAlgorithm<?, ?>> values() {
            return REGISTRY.values();
        }

        /**
         * Returns the JWE Key Management Algorithm with the specified
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.1">{@code alg} key algorithm identifier</a> or
         * {@code null} if an algorithm for the specified {@code id} cannot be found.  If a JWA-standard
         * instance must be resolved, consider using the {@link #get(String)} method instead.
         *
         * @param id a JWA standard {@code alg} key algorithm identifier
         * @return the associated KeyAlgorithm instance or {@code null} otherwise.
         * @see <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.1">RFC 7518, Section 4.1</a>
         * @see #get(String)
         */
        public KeyAlgorithm<?, ?> find(String id) {
            return REGISTRY.find(id);
        }

        /**
         * Returns the JWE Key Management Algorithm with the specified
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.1">{@code alg} key algorithm identifier</a> or
         * throws an {@link IllegalArgumentException} if there is no JWE-standard algorithm for the specified
         * {@code id}.  If a JWE-standard instance result is not mandatory, consider using the {@link #find(String)}
         * method instead.
         *
         * @param id a JWA standard {@code alg} key algorithm identifier
         * @return the associated {@code KeyAlgorithm} instance.
         * @throws IllegalArgumentException if there is no JWA-standard algorithm for the specified identifier.
         * @see #find(String)
         * @see <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.1">RFC 7518, Section 4.1</a>
         */
        public KeyAlgorithm<?, ?> get(String id) throws IllegalArgumentException {
            return REGISTRY.get(id);
        }

        // do not change this visibility.  Raw type method signature not be publicly exposed
        @SuppressWarnings("unchecked")
        private <T> T doGet(String id) {
            Assert.hasText(id, "id cannot be null or empty.");
            return (T) get(id);
        }

        /**
         * Key algorithm reflecting direct use of a shared symmetric key as the JWE AEAD encryption key, as defined
         * by <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.5">RFC 7518 (JWA), Section 4.5</a>.  This
         * algorithm does not produce encrypted key ciphertext.
         */
        public final KeyAlgorithm<SecretKey, SecretKey> DIRECT = doGet("dir");

        /**
         * AES Key Wrap algorithm with default initial value using a 128-bit key, as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.4">RFC 7518 (JWA), Section 4.4</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *     specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with a 128-bit shared symmetric key using the
         *     AES Key Wrap algorithm, producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Decrypts the encrypted key ciphertext with the 128-bit shared symmetric key,
         *     using the AES Key Unwrap algorithm, producing the decryption key plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final SecretKeyAlgorithm A128KW = doGet("A128KW");

        /**
         * AES Key Wrap algorithm with default initial value using a 192-bit key, as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.4">RFC 7518 (JWA), Section 4.4</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *     specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with a 192-bit shared symmetric key using the
         *     AES Key Wrap algorithm, producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Decrypts the encrypted key ciphertext with the 192-bit shared symmetric key,
         *     using the AES Key Unwrap algorithm, producing the decryption key plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final SecretKeyAlgorithm A192KW = doGet("A192KW");

        /**
         * AES Key Wrap algorithm with default initial value using a 256-bit key, as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.4">RFC 7518 (JWA), Section 4.4</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *     specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with a 256-bit shared symmetric key using the
         *     AES Key Wrap algorithm, producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Decrypts the encrypted key ciphertext with the 256-bit shared symmetric key,
         *     using the AES Key Unwrap algorithm, producing the decryption key plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final SecretKeyAlgorithm A256KW = doGet("A256KW");

        /**
         * Key wrap algorithm with AES GCM using a 128-bit key, as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7">RFC 7518 (JWA), Section 4.7</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *     specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Generates a new secure-random 96-bit Initialization Vector to use during key wrap/encryption.</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with a 128-bit shared symmetric key using the
         *     AES GCM Key Wrap algorithm with the generated Initialization Vector, producing encrypted key ciphertext
         *     and GCM authentication tag.</li>
         *     <li>Sets the generated initialization vector as the required
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.1">&quot;iv&quot;
         *     (Initialization Vector) Header Parameter</a></li>
         *     <li>Sets the resulting GCM authentication tag as the required
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.2">&quot;tag&quot;
         *     (Authentication Tag) Header Parameter</a></li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Obtains the required initialization vector from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.1">&quot;iv&quot;
         *     (Initialization Vector) Header Parameter</a></li>
         *     <li>Obtains the required GCM authentication tag from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.2">&quot;tag&quot;
         *     (Authentication Tag) Header Parameter</a></li>
         *     <li>Decrypts the encrypted key ciphertext with the 128-bit shared symmetric key, the initialization vector
         *     and GCM authentication tag using the AES GCM Key Unwrap algorithm, producing the decryption key
         *     plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final SecretKeyAlgorithm A128GCMKW = doGet("A128GCMKW");

        /**
         * Key wrap algorithm with AES GCM using a 192-bit key, as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7">RFC 7518 (JWA), Section 4.7</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *     specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Generates a new secure-random 96-bit Initialization Vector to use during key wrap/encryption.</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with a 192-bit shared symmetric key using the
         *     AES GCM Key Wrap algorithm with the generated Initialization Vector, producing encrypted key ciphertext
         *     and GCM authentication tag.</li>
         *     <li>Sets the generated initialization vector as the required
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.1">&quot;iv&quot;
         *     (Initialization Vector) Header Parameter</a></li>
         *     <li>Sets the resulting GCM authentication tag as the required
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.2">&quot;tag&quot;
         *     (Authentication Tag) Header Parameter</a></li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Obtains the required initialization vector from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.1">&quot;iv&quot;
         *     (Initialization Vector) Header Parameter</a></li>
         *     <li>Obtains the required GCM authentication tag from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.2">&quot;tag&quot;
         *     (Authentication Tag) Header Parameter</a></li>
         *     <li>Decrypts the encrypted key ciphertext with the 192-bit shared symmetric key, the initialization vector
         *     and GCM authentication tag using the AES GCM Key Unwrap algorithm, producing the decryption key \
         *     plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final SecretKeyAlgorithm A192GCMKW = doGet("A192GCMKW");

        /**
         * Key wrap algorithm with AES GCM using a 256-bit key, as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7">RFC 7518 (JWA), Section 4.7</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *     specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Generates a new secure-random 96-bit Initialization Vector to use during key wrap/encryption.</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with a 256-bit shared symmetric key using the
         *     AES GCM Key Wrap algorithm with the generated Initialization Vector, producing encrypted key ciphertext
         *     and GCM authentication tag.</li>
         *     <li>Sets the generated initialization vector as the required
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.1">&quot;iv&quot;
         *     (Initialization Vector) Header Parameter</a></li>
         *     <li>Sets the resulting GCM authentication tag as the required
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.2">&quot;tag&quot;
         *     (Authentication Tag) Header Parameter</a></li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Obtains the required initialization vector from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.1">&quot;iv&quot;
         *     (Initialization Vector) Header Parameter</a></li>
         *     <li>Obtains the required GCM authentication tag from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.7.1.2">&quot;tag&quot;
         *     (Authentication Tag) Header Parameter</a></li>
         *     <li>Decrypts the encrypted key ciphertext with the 256-bit shared symmetric key, the initialization vector
         *     and GCM authentication tag using the AES GCM Key Unwrap algorithm, producing the decryption key \
         *     plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final SecretKeyAlgorithm A256GCMKW = doGet("A256GCMKW");

        /**
         * Key encryption algorithm using <code>PBES2 with HMAC SHA-256 and &quot;A128KW&quot; wrapping</code>
         * as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8">RFC 7518 (JWA), Section 4.8</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Determines the number of PBDKF2 iterations via the JWE header's
         *     {@link JweHeader#getPbes2Count() pbes2Count} value.  If that value is not set, a suitable number of
         *     iterations will be chosen based on
         *     <a href="https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html#pbkdf2">OWASP
         *     PBKDF2 recommendations</a> and then that value is set as the JWE header {@code pbes2Count} value.</li>
         *     <li>Generates a new secure-random salt input and sets it as the JWE header
         *     {@link JweHeader#getPbes2Salt() pbes2Salt} value.</li>
         *     <li>Derives a 128-bit Key Encryption Key with the PBES2-HS256 password-based key derivation algorithm,
         *     using the provided password, iteration count, and input salt as arguments.</li>
         *     <li>Generates a new secure-random Content Encryption {@link SecretKey} suitable for use with a
         *      specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated Content Encryption {@code SecretKey} with the {@code A128KW} key wrap
         *      algorithm using the 128-bit derived password-based Key Encryption Key from step {@code #3},
         *      producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     Content Encryption {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated
         *     {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the required PBKDF2 input salt from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8.1.1">&quot;p2s&quot;
         *     (PBES2 Salt Input) Header Parameter</a></li>
         *     <li>Obtains the required PBKDF2 iteration count from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8.1.2">&quot;p2c&quot;
         *     (PBES2 Count) Header Parameter</a></li>
         *     <li>Derives the 128-bit Key Encryption Key with the PBES2-HS256 password-based key derivation algorithm,
         *     using the provided password, obtained salt input, and obtained iteration count as arguments.</li>
         *     <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Decrypts the encrypted key ciphertext with with the {@code A128KW} key unwrap
         *      algorithm using the 128-bit derived password-based Key Encryption Key from step {@code #3},
         *      producing the decryption key plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final KeyAlgorithm<Password, Password> PBES2_HS256_A128KW = doGet("PBES2-HS256+A128KW");

        /**
         * Key encryption algorithm using <code>PBES2 with HMAC SHA-384 and &quot;A192KW&quot; wrapping</code>
         * as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8">RFC 7518 (JWA), Section 4.8</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Determines the number of PBDKF2 iterations via the JWE header's
         *     {@link JweHeader#getPbes2Count() pbes2Count} value.  If that value is not set, a suitable number of
         *     iterations will be chosen based on
         *     <a href="https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html#pbkdf2">OWASP
         *     PBKDF2 recommendations</a> and then that value is set as the JWE header {@code pbes2Count} value.</li>
         *     <li>Generates a new secure-random salt input and sets it as the JWE header
         *     {@link JweHeader#getPbes2Salt() pbes2Salt} value.</li>
         *     <li>Derives a 192-bit Key Encryption Key with the PBES2-HS384 password-based key derivation algorithm,
         *     using the provided password, iteration count, and input salt as arguments.</li>
         *     <li>Generates a new secure-random Content Encryption {@link SecretKey} suitable for use with a
         *      specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated Content Encryption {@code SecretKey} with the {@code A192KW} key wrap
         *      algorithm using the 192-bit derived password-based Key Encryption Key from step {@code #3},
         *      producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     Content Encryption {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated
         *     {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the required PBKDF2 input salt from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8.1.1">&quot;p2s&quot;
         *     (PBES2 Salt Input) Header Parameter</a></li>
         *     <li>Obtains the required PBKDF2 iteration count from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8.1.2">&quot;p2c&quot;
         *     (PBES2 Count) Header Parameter</a></li>
         *     <li>Derives the 192-bit Key Encryption Key with the PBES2-HS384 password-based key derivation algorithm,
         *     using the provided password, obtained salt input, and obtained iteration count as arguments.</li>
         *     <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Decrypts the encrypted key ciphertext with with the {@code A192KW} key unwrap
         *      algorithm using the 192-bit derived password-based Key Encryption Key from step {@code #3},
         *      producing the decryption key plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final KeyAlgorithm<Password, Password> PBES2_HS384_A192KW = doGet("PBES2-HS384+A192KW");

        /**
         * Key encryption algorithm using <code>PBES2 with HMAC SHA-512 and &quot;A256KW&quot; wrapping</code>
         * as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8">RFC 7518 (JWA), Section 4.8</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Determines the number of PBDKF2 iterations via the JWE header's
         *     {@link JweHeader#getPbes2Count() pbes2Count} value.  If that value is not set, a suitable number of
         *     iterations will be chosen based on
         *     <a href="https://cheatsheetseries.owasp.org/cheatsheets/Password_Storage_Cheat_Sheet.html#pbkdf2">OWASP
         *     PBKDF2 recommendations</a> and then that value is set as the JWE header {@code pbes2Count} value.</li>
         *     <li>Generates a new secure-random salt input and sets it as the JWE header
         *     {@link JweHeader#getPbes2Salt() pbes2Salt} value.</li>
         *     <li>Derives a 256-bit Key Encryption Key with the PBES2-HS512 password-based key derivation algorithm,
         *     using the provided password, iteration count, and input salt as arguments.</li>
         *     <li>Generates a new secure-random Content Encryption {@link SecretKey} suitable for use with a
         *      specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated Content Encryption {@code SecretKey} with the {@code A256KW} key wrap
         *      algorithm using the 256-bit derived password-based Key Encryption Key from step {@code #3},
         *      producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     Content Encryption {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated
         *     {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the required PBKDF2 input salt from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8.1.1">&quot;p2s&quot;
         *     (PBES2 Salt Input) Header Parameter</a></li>
         *     <li>Obtains the required PBKDF2 iteration count from the
         *     <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.8.1.2">&quot;p2c&quot;
         *     (PBES2 Count) Header Parameter</a></li>
         *     <li>Derives the 256-bit Key Encryption Key with the PBES2-HS512 password-based key derivation algorithm,
         *     using the provided password, obtained salt input, and obtained iteration count as arguments.</li>
         *     <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Decrypts the encrypted key ciphertext with with the {@code A256KW} key unwrap
         *      algorithm using the 256-bit derived password-based Key Encryption Key from step {@code #3},
         *      producing the decryption key plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final KeyAlgorithm<Password, Password> PBES2_HS512_A256KW = doGet("PBES2-HS512+A256KW");

        /**
         * Key Encryption with {@code RSAES-PKCS1-v1_5}, as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.2">RFC 7518 (JWA), Section 4.2</a>.
         * This algorithm requires a key size of 2048 bits or larger.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *     specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with the RSA key wrap algorithm, using the JWE
         *     recipient's RSA Public Key, producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Receives the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Decrypts the encrypted key ciphertext with the RSA key unwrap algorithm, using the JWE recipient's
         *     RSA Private Key, producing the decryption key plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}. </li>
         * </ol>
         */
        public final KeyAlgorithm<PublicKey, PrivateKey> RSA1_5 = doGet("RSA1_5");

        /**
         * Key Encryption with {@code RSAES OAEP using default parameters}, as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.3">RFC 7518 (JWA), Section 4.3</a>.
         * This algorithm requires a key size of 2048 bits or larger.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *     specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with the RSA OAEP with SHA-1 and MGF1 key wrap algorithm,
         *     using the JWE recipient's RSA Public Key, producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Receives the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Decrypts the encrypted key ciphertext with the RSA OAEP with SHA-1 and MGF1 key unwrap algorithm,
         *     using the JWE recipient's RSA Private Key, producing the decryption key plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}. </li>
         * </ol>
         */
        public final KeyAlgorithm<PublicKey, PrivateKey> RSA_OAEP = doGet("RSA-OAEP");

        /**
         * Key Encryption with {@code RSAES OAEP using SHA-256 and MGF1 with SHA-256}, as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.3">RFC 7518 (JWA), Section 4.3</a>.
         * This algorithm requires a key size of 2048 bits or larger.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *     specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with the RSA OAEP with SHA-256 and MGF1 key wrap
         *     algorithm, using the JWE recipient's RSA Public Key, producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Receives the encrypted key ciphertext embedded in the received JWE.</li>
         *     <li>Decrypts the encrypted key ciphertext with the RSA OAEP with SHA-256 and MGF1 key unwrap algorithm,
         *     using the JWE recipient's RSA Private Key, producing the decryption key plaintext.</li>
         *     <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *     JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}. </li>
         * </ol>
         */
        public final KeyAlgorithm<PublicKey, PrivateKey> RSA_OAEP_256 = doGet("RSA-OAEP-256");

        /**
         * Key Agreement with {@code ECDH-ES using Concat KDF} as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6">RFC 7518 (JWA), Section 4.6</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random Elliptic Curve public/private key pair on the same curve as the
         *     JWE recipient's EC Public Key.</li>
         *     <li>Generates a shared secret with the ECDH key agreement algorithm using the generated EC Private Key
         *     and the JWE recipient's EC Public Key.</li>
         *     <li><a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.2">Derives</a> a symmetric Content
         *     Encryption {@code SecretKey} with the Concat KDF algorithm using the
         *     generated shared secret and any available
         *     {@link JweHeader#getAgreementPartyUInfo() PartyUInfo} and
         *     {@link JweHeader#getAgreementPartyVInfo() PartyVInfo}.</li>
         *     <li>Sets the generated EC key pair's Public Key as the required
         *      <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.1.1">&quot;epk&quot;
         *      (Ephemeral Public Key) Header Parameter</a> to be transmitted in the JWE.</li>
         *     <li>Returns the derived symmetric {@code SecretKey} for JJWT to use to encrypt the entire JWE with the
         *     associated {@link AeadAlgorithm}. Encrypted key ciphertext is not produced with this algorithm, so
         *     the resulting JWE will not contain any embedded key ciphertext.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the required ephemeral Elliptic Curve Public Key from the
         *      <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.1.1">&quot;epk&quot;
         *      (Ephemeral Public Key) Header Parameter</a>.</li>
         *     <li>Validates that the ephemeral Public Key is on the same curve as the recipient's EC Private Key.</li>
         *     <li>Obtains the shared secret with the ECDH key agreement algorithm using the obtained EC Public Key
         *      and the JWE recipient's EC Private Key.</li>
         *     <li><a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.2">Derives</a> the symmetric Content
         *      Encryption {@code SecretKey} with the Concat KDF algorithm using the
         *      obtained shared secret and any available
         *      {@link JweHeader#getAgreementPartyUInfo() PartyUInfo} and
         *      {@link JweHeader#getAgreementPartyVInfo() PartyVInfo}.</li>
         *      <li>Returns the derived symmetric {@code SecretKey} for JJWT to use to decrypt the entire
         *      JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final KeyAlgorithm<PublicKey, PrivateKey> ECDH_ES = doGet("ECDH-ES");

        /**
         * Key Agreement with Key Wrapping via
         * <code>ECDH-ES using Concat KDF and CEK wrapped with &quot;A128KW&quot;</code> as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6">RFC 7518 (JWA), Section 4.6</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random Elliptic Curve public/private key pair on the same curve as the
         *     JWE recipient's EC Public Key.</li>
         *     <li>Generates a shared secret with the ECDH key agreement algorithm using the generated EC Private Key
         *     and the JWE recipient's EC Public Key.</li>
         *     <li><a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.2">Derives</a> a 128-bit symmetric Key
         *     Encryption {@code SecretKey} with the Concat KDF algorithm using the
         *     generated shared secret and any available
         *     {@link JweHeader#getAgreementPartyUInfo() PartyUInfo} and
         *     {@link JweHeader#getAgreementPartyVInfo() PartyVInfo}.</li>
         *     <li>Sets the generated EC key pair's Public Key as the required
         *      <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.1.1">&quot;epk&quot;
         *      (Ephemeral Public Key) Header Parameter</a> to be transmitted in the JWE.</li>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *      specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with the {@code A128KW} key wrap
         *      algorithm using the derived symmetric Key Encryption Key from step {@code #3}, producing encrypted key ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the required ephemeral Elliptic Curve Public Key from the
         *      <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.1.1">&quot;epk&quot;
         *      (Ephemeral Public Key) Header Parameter</a>.</li>
         *     <li>Validates that the ephemeral Public Key is on the same curve as the recipient's EC Private Key.</li>
         *     <li>Obtains the shared secret with the ECDH key agreement algorithm using the obtained EC Public Key
         *      and the JWE recipient's EC Private Key.</li>
         *     <li><a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.2">Derives</a> the symmetric Key
         *      Encryption {@code SecretKey} with the Concat KDF algorithm using the
         *      obtained shared secret and any available
         *      {@link JweHeader#getAgreementPartyUInfo() PartyUInfo} and
         *      {@link JweHeader#getAgreementPartyVInfo() PartyVInfo}.</li>
         *      <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *      <li>Decrypts the encrypted key ciphertext with the AES Key Unwrap algorithm using the
         *      128-bit derived symmetric key from step {@code #4}, producing the decryption key plaintext.</li>
         *      <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *      JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final KeyAlgorithm<PublicKey, PrivateKey> ECDH_ES_A128KW = doGet("ECDH-ES+A128KW");

        /**
         * Key Agreement with Key Wrapping via
         * <code>ECDH-ES using Concat KDF and CEK wrapped with &quot;A192KW&quot;</code> as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6">RFC 7518 (JWA), Section 4.6</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random Elliptic Curve public/private key pair on the same curve as the
         *     JWE recipient's EC Public Key.</li>
         *     <li>Generates a shared secret with the ECDH key agreement algorithm using the generated EC Private Key
         *     and the JWE recipient's EC Public Key.</li>
         *     <li><a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.2">Derives</a> a 192-bit symmetric Key
         *     Encryption {@code SecretKey} with the Concat KDF algorithm using the
         *     generated shared secret and any available
         *     {@link JweHeader#getAgreementPartyUInfo() PartyUInfo} and
         *     {@link JweHeader#getAgreementPartyVInfo() PartyVInfo}.</li>
         *     <li>Sets the generated EC key pair's Public Key as the required
         *      <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.1.1">&quot;epk&quot;
         *      (Ephemeral Public Key) Header Parameter</a> to be transmitted in the JWE.</li>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *      specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with the {@code A192KW} key wrap
         *      algorithm using the derived symmetric Key Encryption Key from step {@code #3}, producing encrypted key
         *      ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the required ephemeral Elliptic Curve Public Key from the
         *      <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.1.1">&quot;epk&quot;
         *      (Ephemeral Public Key) Header Parameter</a>.</li>
         *     <li>Validates that the ephemeral Public Key is on the same curve as the recipient's EC Private Key.</li>
         *     <li>Obtains the shared secret with the ECDH key agreement algorithm using the obtained EC Public Key
         *      and the JWE recipient's EC Private Key.</li>
         *     <li><a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.2">Derives</a> the 192-bit symmetric
         *      Key Encryption {@code SecretKey} with the Concat KDF algorithm using the
         *      obtained shared secret and any available
         *      {@link JweHeader#getAgreementPartyUInfo() PartyUInfo} and
         *      {@link JweHeader#getAgreementPartyVInfo() PartyVInfo}.</li>
         *      <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *      <li>Decrypts the encrypted key ciphertext with the AES Key Unwrap algorithm using the
         *      192-bit derived symmetric key from step {@code #4}, producing the decryption key plaintext.</li>
         *      <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *      JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final KeyAlgorithm<PublicKey, PrivateKey> ECDH_ES_A192KW = doGet("ECDH-ES+A192KW");

        /**
         * Key Agreement with Key Wrapping via
         * <code>ECDH-ES using Concat KDF and CEK wrapped with &quot;A256KW&quot;</code> as defined by
         * <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6">RFC 7518 (JWA), Section 4.6</a>.
         *
         * <p>During JWE creation, this algorithm:</p>
         * <ol>
         *     <li>Generates a new secure-random Elliptic Curve public/private key pair on the same curve as the
         *     JWE recipient's EC Public Key.</li>
         *     <li>Generates a shared secret with the ECDH key agreement algorithm using the generated EC Private Key
         *     and the JWE recipient's EC Public Key.</li>
         *     <li><a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.2">Derives</a> a 256-bit symmetric Key
         *     Encryption {@code SecretKey} with the Concat KDF algorithm using the
         *     generated shared secret and any available
         *     {@link JweHeader#getAgreementPartyUInfo() PartyUInfo} and
         *     {@link JweHeader#getAgreementPartyVInfo() PartyVInfo}.</li>
         *     <li>Sets the generated EC key pair's Public Key as the required
         *      <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.1.1">&quot;epk&quot;
         *      (Ephemeral Public Key) Header Parameter</a> to be transmitted in the JWE.</li>
         *     <li>Generates a new secure-random content encryption {@link SecretKey} suitable for use with a
         *      specified {@link AeadAlgorithm} (using {@link AeadAlgorithm#keyBuilder()}).</li>
         *     <li>Encrypts this newly-generated {@code SecretKey} with the {@code A256KW} key wrap
         *      algorithm using the derived symmetric Key Encryption Key from step {@code #3}, producing encrypted key
         *      ciphertext.</li>
         *     <li>Returns the encrypted key ciphertext for inclusion in the final JWE as well as the newly-generated
         *     {@code SecretKey} for JJWT to use to encrypt the entire JWE with associated {@link AeadAlgorithm}.</li>
         * </ol>
         * <p>For JWE decryption, this algorithm:</p>
         * <ol>
         *     <li>Obtains the required ephemeral Elliptic Curve Public Key from the
         *      <a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.1.1">&quot;epk&quot;
         *      (Ephemeral Public Key) Header Parameter</a>.</li>
         *     <li>Validates that the ephemeral Public Key is on the same curve as the recipient's EC Private Key.</li>
         *     <li>Obtains the shared secret with the ECDH key agreement algorithm using the obtained EC Public Key
         *      and the JWE recipient's EC Private Key.</li>
         *     <li><a href="https://www.rfc-editor.org/rfc/rfc7518.html#section-4.6.2">Derives</a> the 256-bit symmetric
         *      Key Encryption {@code SecretKey} with the Concat KDF algorithm using the
         *      obtained shared secret and any available
         *      {@link JweHeader#getAgreementPartyUInfo() PartyUInfo} and
         *      {@link JweHeader#getAgreementPartyVInfo() PartyVInfo}.</li>
         *      <li>Obtains the encrypted key ciphertext embedded in the received JWE.</li>
         *      <li>Decrypts the encrypted key ciphertext with the AES Key Unwrap algorithm using the
         *      256-bit derived symmetric key from step {@code #4}, producing the decryption key plaintext.</li>
         *      <li>Returns the decryption key plaintext as a {@link SecretKey} for JJWT to use to decrypt the entire
         *      JWE using the JWE's identified &quot;enc&quot; {@link AeadAlgorithm}.</li>
         * </ol>
         */
        public final KeyAlgorithm<PublicKey, PrivateKey> ECDH_ES_A256KW = doGet("ECDH-ES+A256KW");
    }

}
