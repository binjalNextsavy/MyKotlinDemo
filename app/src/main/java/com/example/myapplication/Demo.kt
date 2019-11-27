package com.example.myapplication

import kotlin.run as run1

//This is demo class
class MyCLass {

    //Companion object is similar to static method in java
    companion object{
        private var name: String = "ABC"

        fun Hello(msg : String){
            println(msg)
        }
    }



    fun printMe() {
        println("Name is $name")

    }
}

//Nested class
class Outer {
    class inner {
        fun foo() = "Hey! how are you?"
    }
}

//Constructor
class Person(val name: String, var age: Int) {
    //body
    val message: String = "Hey"

    constructor(name: String, age: Int, message: String) : this(name, age)   //secondary constructor
}

//Inheritance -- use the keyword "open" to start class declaration to make its allowble to inheritance

/*open class ABC{
    fun think(){
        print("I'm thinking")
    }
}
class BCD: ABC(){

}*/

//override
open class ABC {
    open fun think() {
        print("I'm thinking")
    }
}

class BCD : ABC() {
    override fun think() {
        println("I'm thinking")
    }
}


//Default Named Argument
fun run1(num: Int = 5, text: Char = 'a') {
    println("paramater: $num & $text")
}


fun main(args: Array<String>) {
    //object call
    val obj = MyCLass()
    obj.printMe()

    //Call Companion method using class name.method name
    MyCLass.Hello("Hi")

    //Nested class Kotlin, nested class is by default static, hence, it can be accessed without creating any object of that class.
    val demo = Outer.inner().foo()
    println(demo)

    //constructor
    val p1 = Person("ABC", 20)


    //primary call
    println("Name is -- ${p1.name}")
    println("Age is -- ${p1.age}")

    //secondary call
    println("${p1.name} -- ${p1.age} -- ${p1.message}")

    //Inheritance
    var obj1 = BCD()
    obj1.think()


    //Default Named Argument
    run1()
    run1(3)
    run1(text = 'a')

    var myarr: IntArray = intArrayOf(1, 2)

    var myarr1 = Array<Int>(5) { 0 }

    //null value for(use ?)
    var str: String? = "Hello"
    str = null
    var value: Int? = 3
    value = null

    //nullable tpye - smart cast
    var string: String? = "Hello!"
    if (string != null) { // smart cast
        println(string.length) // It works now!
    }

    //list
    var list = listOf("a", "b")
    for (element in list) {
        println(element)
    }

    var intList: List<Int> = listOf<Int>(1, 2, 3)
    var stringList: List<String> = listOf<String>("Ajay", "Vijay", "Prakash")
    var anyList: List<Any> = listOf<Any>(1, 2, "a", "b")

    val arrayList: ArrayList<String> = ArrayList(5)

    val myMap: Map<Int, String> = mapOf<Int, String>(1 to "a", 2 to "b")
    println(myMap.getValue(2))


    val hashMap: HashMap<Int, String> = HashMap<Int, String>()
    hashMap.put(1, "a")
    hashMap.put(2, "b")

    for (key in hashMap.keys) {
        println("Element at $key = ${hashMap[key]}")
    }

}


class Registration(email : String, pwd: String ,age: Int, gender: Char){
        var email_id: String = email
            get() {
                return field.toLowerCase()
            }

        var password: String = pwd
            set(value) {
                field = if (value.length > 6) value else throw java.lang.IllegalArgumentException("Password is too small")
            }

        var age: Int = age
            set(value) {
                field = if (value > 18) value else throw java.lang.IllegalArgumentException("Age must be 18+")
            }

        var gender: Char = gender
            set(value) {
                field = if (value == 'F') value else throw java.lang.IllegalArgumentException("User Should be female")
            }

    fun main(args: Array<String>) {
        val xyz = Registration("abc@mail.com","12345",18,'F')

        println("${xyz.email_id}")
        xyz.email_id = "xyz.mail.com"
        println("${xyz.email_id}")
        println("${xyz.password}")
        println("${xyz.age}")
        println("${xyz.gender}")
    }

}

class Demo<T>(Text : T){
    var x = Text

    init {
        println(x)
    }

    fun main(args: Array<String>){
        var name : Demo<String> = Demo("ABc")
        var age : Demo<String> = Demo("20")

    }
}








