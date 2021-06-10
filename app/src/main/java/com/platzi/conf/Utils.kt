package com.platzi.conf

class Utils {

    fun checkPassword(password: String): Boolean {
        if (password.length >= 10) {
            if (password.matches(".*\\d.*".toRegex())){
                return true
            }
            else {
                return false
            }
        }
        return false
    }
}