package ru.vsls.utils

import java.io.IOException

class NotFoundException(override val message: String) : IOException(message)
class UnknownException(override val message: String) : IOException(message)
class NoInternetException(override val message: String) : IOException(message)
class UnauthorizedException(override val message: String) : IOException(message)
class BadRequestException(override val message: String) : IOException(message)
class ServerException(override val message: String) : IOException(message)
class NonValidFieldsException(override val message: String) : IOException(message)
class FailedStateException(override val message: String) : IOException(message)