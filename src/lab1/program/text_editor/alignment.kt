package lab1.program.text_editor

fun alignText(alignment: Alignment, width: Int, text : ArrayList<String>)
{
    toOneLine(text)
    spaceDeleter(text)
    wordTransfer(width, text)
    when(alignment)
    {
        Alignment.JUSTIFY -> alignToWidth(width, text)
        Alignment.CENTER -> centerAlignment(width, text)
        Alignment.LEFT -> leftAlignment(width, text)
        Alignment.RIGHT -> rightAlignment(width, text)
        else -> return
    }
}

private fun toOneLine(text : ArrayList<String>)
{
    for(i in text.size-1 downTo 1)
    {
        text[i - 1] = text[i-1] + " " + text[i]
        text.removeAt(text.lastIndex)
    }
}

enum class Alignment
{
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}