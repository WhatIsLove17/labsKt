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

enum class Alignment
{
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}