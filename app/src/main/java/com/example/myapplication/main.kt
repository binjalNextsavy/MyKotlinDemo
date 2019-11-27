package com.example.myapplication

fun square() : (Int) -> Unit{
    return {
       //it: implicit name of a single parameter

        //It's very common that a lambda expression has only one parameter.
     println(it * it)
    }
}



//Generic class    allow us to define classes, methods and properties which are accessible using different data types
                    //Same as Templates in c++

class Company<T> (text : T){
    var x = text
    init{
        println(x)
    }
}
fun main(args: Array<String>){

    square()(3)

    var name: Company<String> = Company<String>("GeeksforGeeks")
    var rank: Company<Int> = Company<Int>(12)
    var dvalue : Company<Double> = Company(20.12)
}
