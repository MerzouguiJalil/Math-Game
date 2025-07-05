package com.example.gamemath

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.ObjectInputStream

class helper2 {
    fun read(context: Context,FILNAME : String) : ArrayList<String> {

        var array : ArrayList<String>
        try {
            var fis : FileInputStream = context.openFileInput(FILNAME)
            var ois = ObjectInputStream(fis)
            array = ois.readObject() as ArrayList<String>

        }catch (e : FileNotFoundException) {
            array = ArrayList<String>()
        }
        return  array
    }
}