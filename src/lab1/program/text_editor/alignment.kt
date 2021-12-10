package lab1.program.text_editor

fun alignText(alignment: Alignment = Alignment.JUSTIFY, width: Int = 25, text: String): String {
    val textList = ArrayList<String>()
    textList.add(text.replace("\n", " "))
    spaceDeleter(textList)
    wordTransfer(width, textList)
    when (alignment) {
        Alignment.JUSTIFY -> alignToWidth(width, textList)
        Alignment.CENTER -> centerAlignment(width, textList)
        Alignment.LEFT -> leftAlignment(width, textList)
        Alignment.RIGHT -> rightAlignment(width, textList)
        //else -> return
    }
    return textList.joinToString("\n")
}

private fun spaceDeleter(text: MutableList<String>) {
// removing last spaces
    if (text[0][0] == ' ') {
        var i = 1
        while (text[0][i] == ' ')
            i++
        text[0] = text[0].substring(i)
    }

// removing first spaces
    if (text[0][text[0].length - 1] == ' ') {
        var i = text[0].length - 2
        while (text[0][i] == ' ')
            i--
        text[0] = text[0].substring(0, i + 1)
    }

// removing duplicate spaces
    var i = 0
    while (i < text[0].length) {
        if (text[0][i] == ' ') {
            var j = i + 1
            while (text[0][j] == ' ')
                j++
            text[0] = text[0].removeRange(i + 1, j)
        }
        i++
    }

// removing spaces before special symbols
    i = 1
    while (i < text[0].length) {
        if (text[0][i] in arrayOf(',', '.', '!', ':', ';', '?') && text[0][i - 1] == ' ') {
            text[0] = text[0].removeRange(i - 1, i)
        }
        i++
    }
}

private fun wordTransfer(width: Int, text: MutableList<String>) {
    var i = 0
    while (i < text.size) {
        while (text[i].length > width) {
            // adding new line if this is last
            if (i == text.size - 1) text.add("")

            if (text[i].lastIndexOf(' ') != -1) {
                // transfer one word if there is space
                text[i + 1] = text[i].substringAfterLast(' ') +
                        if (text[i + 1].isNotEmpty()) {" "} else {""} + text[i + 1]
                text[i] = text[i].substringBeforeLast(' ')
            }
            else {
                val transferredSymbols = if (text[i].length - width == 1 && width != 1 &&
                    text[i].last() in arrayOf('!', ',', '.', ':', '-', ';', '?')
                ) 1 else 0
                // transfer few symbols if there isn't space
                text[i + 1] = text[i].substring(width - transferredSymbols) + " " + text[i + 1]
                text[i] = text[i].substring(0, width - transferredSymbols)
            }
        }
        i++
    }
}

private fun alignToWidth(width: Int, text: MutableList<String>) {
    for (i in 0 until text.size) {
        if (text[i].length < width) {
            // needSpaces is count of spaces needed for add to line
            val needSpaces = width - text[i].length
            // placesForSpaces is count of places we can insert spaces
            val placesForSpaces = text[i].count { it == ' ' }
            // spacesForOnePlace is same count of spaces for each place
            val spacesForOnePlace = if (placesForSpaces != 0) needSpaces / placesForSpaces
            else 0
            // remainingSpaces is remainder of spaces
            var remainingSpaces = if (placesForSpaces != 0) needSpaces % placesForSpaces
            else 0
            // remSpacesIsThere is indicator that remainingSpaces != 0
            var remSpacesIsThere = 1
            var currentPlaceIndex = 0
            for (j in 1..placesForSpaces) {
                if (remainingSpaces == 0) remSpacesIsThere = 0
                // looking for next space
                currentPlaceIndex = text[i].indexOf(' ', currentPlaceIndex)
                // insert the spaces
                text[i] = text[i].substring(0, currentPlaceIndex + 1) +
                        " ".repeat(spacesForOnePlace + remSpacesIsThere) +
                        text[i].substring(currentPlaceIndex + 1)
                currentPlaceIndex += spacesForOnePlace + remSpacesIsThere + 1
                remainingSpaces--
            }
        }
    }
}

private fun leftAlignment(width: Int, text: MutableList<String>) {
    for (i in 0 until text.size)
        text[i] = text[i] + " ".repeat(width - text[i].length)
}

private fun rightAlignment(width: Int, text: MutableList<String>) {
    for (i in 0 until text.size)
        text[i] = " ".repeat(width - text[i].length) + text[i]
}

private fun centerAlignment(width: Int, text: MutableList<String>) {
    for (i in 0 until text.size)
        text[i] = " ".repeat((width - text[i].length) / 2) + text[i] +
                " ".repeat((width - text[i].length) - (width - text[i].length) / 2)
}

enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}