package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    /* private val i: Int = 5                  //val is like Final variable and it's known as immutable in kotlin and can be initialized only single time.(same as final)
    val a: Int = 3
    val b: Int = 2
    var max: Int = 0                        //var is like general variable and it's known as a mutable variable in kotlin and can be assigned multiple times.
    var x = 5

    val items = listOf(1,2,3,4)

    //Array
    val id = arrayOf(1,2,3,4)
    val firstId = id[0]
    val lastId = id[id.size-1]


    //raw string  declared within triple quote (""" """). It provides facility to declare String in new lines and contain multiple lines
    val text1 = """
        Welcome
        to
        Kotlin
    """

    //escape declared within double quote (" ") and may contain escape characters like '\n', '\t', '\b' etc.
    val text2 = "hello\n"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (j in 1..4){
            println(j)

        }

        if (i in 1..10){
            println("Your number found--$i")
        }



        if (a > b){
            max = a          //As expression
        } else {                //val max = if(a > b) a else b
            max = b
        }
        println("Max is "+max)



        when(i){
            1 -> print("i is 1")                //1,2 -> print("i is either 1,2")
            2 -> print("i is 2")
            else -> {
                print("i is neither 1 nor 2")
            }
        }

        for (i in items) println("Values of array is"+i);

        println("-- Example of while loop --")

        while (x <= 10){
            println(x)
            x++
        }

        println("-- Example of Do while loop --")

        do {
            x = x+1
            x++
        }while (x <= 10)

        //return type

        println("Double value of x is -- "+doubleMe(x))


        println("Example of Break and Continue")

        myLabel@ for(x in 1..10) { // appling the custom label
            if(x == 5) {
                println("I am inside if block with value"+x+"\n-- hence it will close the operation")
                break@myLabel //specifing the label
            } else {
                println("I am inside else block with value"+x)
                continue@myLabel
            }
        }
*/

    //input from user

    fun main(args: ArrayList<String>) {

        println("Enter your name")
        var nameOf = readLine()
        println("Enter your age")
        var age: Int = Integer.valueOf(readLine().toString())
        println("Your name is $nameOf & Your age is $age")

        printMsg("Hello")

    }

    fun printlAll(vararg msg: String) {
        for (m in msg) {
            println(m)
        }
    }

    fun printMsg(msg: String): Unit {
        println(msg)
        printlAll("Hello", "Hi", "How", "Are", "You")
    }

    //for undo
    //btn.setOnCLickListener{
        //.undo()
//}

    //Generic class      Type casting is evitable- No need to typecast the object.
    //Type safety- Generic allows only single type of object at a time.
    //Compile time safety- Generics code is checked at compile time for the parameterized type so that it avoids run time error.

    class ABC<T>(Text : T){
        var x = Text

        init {
            println(x)
        }

        fun main(args: ArrayList<String>){
            var name : ABC<String> =
                ABC<String>("ABc")
            var age : ABC<Int> =
                ABC<Int>(29)
            var per : ABC<Double> =
                ABC(20.12)

        }
    }





/*
    private fun doubleMe(x: Int): Int {
        return 2*x
    }*/


}
