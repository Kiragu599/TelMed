package com.dennis.telmed.model

// Doctor Model: This is a blueprint for the doctor information.
class Doctor{
    var name:String=""
    var specialty:String=""
    var experience:String=""
    var id:String=""

    constructor(name:String,specialty:String,experience:String,id:String){
        this.name=name
        this.specialty=specialty
        this.experience=experience
        this.id=id

    }
    constructor()
}