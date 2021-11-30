package lab1.program

import lab1.program.text_editor.*

fun main()
{
    //var text = ArrayList<String>()
    //println("\nВведите текст для форматирования:\n")
    //lab1.program.inputText(text)
    val text = arrayListOf("Ох , лето красное! " +
            "любил бы я тебя , Когда б не зной , да пыль, да комары , да мухи . " +
            "Ты , все душевные способности губя , " +
            "Нас мучишь ; как поля , мы страждем от засухи ; " +
            "Лишь как бы напоить , да освежить себя — " +
            "Иной в нас мысли нет , и жаль зимы старухи, " +
            "И , проводив ее блинами и вином , " +
            "Поминки ей творим мороженым и льдом .")

    for(type in Alignment.values())
    {
        println("-------------------------------------------")
        alignText(type, 20, text)
        outputText(text)
    }
}

fun inputText(text : ArrayList<String>)
{
    text.clear()
    do
    {
        val line : String? = readLine()
        if (line != null && line != "")
            if (text.size == 0)
                text.add(line)
            else
                text[0] = text[0] + " " + line
    } while(line != "" && line != null)
}

fun outputText(text : ArrayList <String>)
{
    text.forEach {
        println(it)
    }
}
