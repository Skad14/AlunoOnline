package com.pjx.skad.myapplication

class User(val uid: String, val nome: String, val matricula: String, val profileImageUrl: String) {
    constructor() : this("", "", "", "")
}