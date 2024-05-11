package com.mugss.core.network.api.errors

class NoSeedValuesException(
    override val message: String = "No seeds got in parameters. Add at least one of them"
) : Exception(message)