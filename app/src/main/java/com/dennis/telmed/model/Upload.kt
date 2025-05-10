package com.dennis.telmed.model

class Upload{
    var name:String=""
    var speciality:String=""
    var experience:String=""
    var imageUrl:String=""
    var id:String=""

    constructor(name:String,speciality:String,experience:String,imageUrl:String,id:String){

        this.name=name
        this.speciality=speciality
        this.experience=experience
        this.imageUrl=imageUrl
        this.id=id

    }
    constructor()
}