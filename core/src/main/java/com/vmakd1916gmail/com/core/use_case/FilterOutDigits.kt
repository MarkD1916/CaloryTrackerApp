package com.vmakd1916gmail.com.core.use_case

class FilterOutDigits {

    operator fun invoke(text: String): String{
        return text.filter { it.isDigit() }
    }

}