package com.example.gamemath

import android.content.Context
import java.io.FileNotFoundException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@Suppress("UNREACHABLE_CODE")
class FileHelper {


    fun write(scores : ArrayList<Int> , context : Context , FILNAME : String){

        var fos = context.openFileOutput(FILNAME, Context.MODE_PRIVATE)
        var oas = ObjectOutputStream(fos)
        oas.writeObject(scores)
        oas.close()
    }

    fun read(context : Context , FILNAME : String) : ArrayList<Int>{
        var scores = ArrayList<Int>()
        try {
            var fis = context.openFileInput(FILNAME)
            var ois = ObjectInputStream(fis)
            scores = ois.readObject() as ArrayList<Int>
            return scores
        }catch (e : FileNotFoundException){
            return scores
        }




    }
}