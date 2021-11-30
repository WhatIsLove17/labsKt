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

private fun spaceDeleter(text : ArrayList<String>)
{
// removing last spaces
    if (text[0][0] == ' ')
    {
        var i = 1
        while(text[0][i] == ' ')
            i++
        text[0] = text[0].substring(i)
    }
//////////////

// removing first spaces
    if (text[0][text[0].length - 1] == ' ')
    {
        var i = text[0].length - 2
        while(text[0][i] == ' ')
            i--
        text[0] = text[0].substring(0, i+1)
    }
///////////////

// removing duplicate spaces
    var i = 0
    while(i < text[0].length)
    {
        if (text[0][i] == ' ')
        {
            var j = i + 1
            while (text[0][j] == ' ')
                j++
            text[0] = text[0].removeRange(i+1, j)
        }
        i++
    }
///////////////

// removing spaces before special symbols
    i = 1;
    while(i < text[0].length)
    {
        if (text[0][i] in arrayOf(',', '.', '!', ':', ';', '?') && text[0][i-1] == ' ')
        {
            text[0] = text[0].removeRange(i-1, i)
        }
        i++
    }
}

private fun wordTransfer(width : Int, text : ArrayList<String>)
{
    var i = 0
    while(i < text.size)
    {
        while(text[i].length > width)
        {
            // adding new line if this is last
            if (i == text.size-1) text.add("")

            if (text[i].lastIndexOf(' ') != -1) {
            // transfer one word if there is space
                text[i + 1] = text[i].substringAfterLast(' ') +
                        if (text[i+1].length != 0) {" "} else {""} + text[i + 1]
                text[i] = text[i].substringBeforeLast(' ')
            }
            else
            {
            // transfer few symbols if there isn't space
                text[i + 1] = text[i].substring(width -
                        if (text[i].length - width == 1 && text[i].last() in arrayOf('!', ',', '.', ':', '-', ';', '?'))
                        {1} else {0}) + " " + text[i + 1]
                text[i] = text[i].substring(0, width)
            }
        }
        i++
    }
}

enum class Alignment
{
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}