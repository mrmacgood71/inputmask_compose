package it.macgood.inputmask.exceptions

class NotBankCardNumberException(override val message: String = "There is not a bank number") : Exception() {}
class NotCountryCodeException(override val message: String = "The symbols that mean the country code are not specified") : Exception() {}
class NotIpNumberException(override val message: String = "Octet can not be bigger than 255") : Exception() {}