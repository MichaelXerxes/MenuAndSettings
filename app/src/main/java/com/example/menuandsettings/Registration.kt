package com.example.menuandsettings

data class Registration (
    val name:String,
    val email:String,
    val password:String,
    val isCHecked:Boolean=false
        )