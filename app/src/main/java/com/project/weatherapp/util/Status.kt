package com.project.weatherapp.util

enum class Status(val stat: String) {
    GOOD("200"),
    BAD("500"),
    LOST("404"),
    NEXT("1000")
}