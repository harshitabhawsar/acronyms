package com.example.acronyms.util

/**
 * This is Util class which validates if acronym  provided by user is valid or not.
 */
object ValidationUtil {
    private const val SUCCESS_MESSAGE = "Success"
    private const val EMPTY_SF_MESSAGE = "Please provide valid acronym."
    private const val SINGLE_CHAR_SF_MESSAGE = "Acronym can't be single character."
    private const val NON_ALPHABET_SF_MESSAGE = "Acronym can contain only alphabets."
    const val NETWORK_ERROR_MESSAGE = "Please check Internet Connectivity."
    const val RESPONSE_ERROR_MESSAGE = "Acronym Not Found."
    fun isValid(acronym: String): Pair<Boolean, String> {
        return if (acronym.isEmpty())
            Pair(false, EMPTY_SF_MESSAGE)
        else if (acronym.length == 1)
            Pair(false, SINGLE_CHAR_SF_MESSAGE)
        else if (!(acronym.matches("^[a-zA-Z]*$".toRegex())))
            Pair(false, NON_ALPHABET_SF_MESSAGE)
        else
            Pair(true, SUCCESS_MESSAGE)
    }
}