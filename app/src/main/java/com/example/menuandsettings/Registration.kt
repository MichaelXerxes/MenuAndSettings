package com.example.menuandsettings

 class Registration {
     var id:Int=0
    var name: String=""
    var email: String=""
    var password: String=""
     constructor(ID:Int,name:String,email:String,pass:String){
         this.id=ID
         this.name=name
         this.email=email
         this.password=pass
     }
     constructor(name:String,email: String,pass: String)
     constructor(){
         fun toString():String {
             var str=""
             str+="ID: $id  Name: $name Email: $email Password: $password"
             return str
         }

     }

}