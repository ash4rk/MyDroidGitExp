package com.example.mydroidgitexp

interface Numbers {

    fun sum(): Int
    fun diff(): Int

    class Base(
        private val firstNumber: Int,
        private val secondNumber: Int
    ) : Numbers {

        override fun sum(): Int {
            return firstNumber + secondNumber
        }

        override fun diff(): Int {
            return firstNumber - secondNumber
        }
    }

}