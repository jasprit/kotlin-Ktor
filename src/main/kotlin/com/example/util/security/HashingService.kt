package com.example.util.security

interface HashingService {
	
	fun generateSaltedHash(value: String, saltLength: Int = 32): SaltedHash
	fun verify(value: String, saltedHash: SaltedHash): Boolean
}