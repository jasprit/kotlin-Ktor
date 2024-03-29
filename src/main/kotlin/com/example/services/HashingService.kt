package com.example.services

import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.security.SecureRandom

interface HashingService {
    fun generateSaltedHash(value: String, saltLength: Int = 32): SaltedHash
    fun verify(value: String, saltedHash: SaltedHash): Boolean
}

data class SaltedHash(val hash: String, val salt: String)

class SHA256HashingService : HashingService {
    override fun generateSaltedHash(value: String, saltLength: Int): SaltedHash {
        val salt = ByteArray(saltLength)
        SecureRandom().nextBytes(salt)
        val saltAsHex = Hex.encodeHexString(salt)
        val hash = DigestUtils.sha256Hex("$saltAsHex$value")
        return SaltedHash(hash = hash, salt = saltAsHex)
    }

    override fun verify(value: String, saltedHash: SaltedHash): Boolean {
        return DigestUtils.sha256Hex(saltedHash.salt + value) == saltedHash.hash
    }
}
