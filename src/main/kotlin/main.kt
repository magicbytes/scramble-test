import kotlin.math.min

val wordsList = listOf(
    "aba",
    "abaca",
    "abacas",
    "abaci",
    "aback",
    "abacterial",
    "abacus"
)

data class Word(val value: String)

data class Letter(val value: Char, val points: Int)

//data class Tile(val value: Char)

data class Rack(val letters: List<Letter>) {
}


fun main(args: Array<String>) {
    val onePointers = "E, A, I, O, N, R, T, L, S, U".splitData(1)
    val twoPointers = "D, G".splitData(2)
    val threePointers = "B, C, M, P".splitData(3)
    val fourPointers = "F, H, V, W, Y".splitData(4)
    val fivePointers = "K".splitData(5)
    val eightPointers = "J,X".splitData(8)
    val tenPointers = "Q,Z".splitData(10)

    // 1.
    val alphabet = onePointers + twoPointers + threePointers + fourPointers + fivePointers + eightPointers + tenPointers
    println(calculateWordPoints("GUARDIAN", alphabet))

    // 2. extract 7 letters
    val sevenLetters = alphabet.extract7Letters()
    val playerRack = Rack(sevenLetters)
    println("Players rack: $playerRack")

    // 3. have a bag
    val bag = createLetterTiles(12, 'E') +
            createLetterTiles(9, 'A', 'I') +
            createLetterTiles(8, 'O') +
            createLetterTiles(6, 'N', 'R', 'T') +
            createLetterTiles(4, 'L', 'S', 'U', 'D') +
            createLetterTiles(3, 'G') +
            createLetterTiles(2, 'B', 'C', 'M', 'P', 'F', 'H', 'V', 'W', 'Y') +
            createLetterTiles(1, 'K', 'J', 'X', 'Q', 'Z')
    val sevenTiles = bag.getMeTiles(7)
    val player2Rack = Rack(sevenTiles)
    println("Player 2 rack: $sevenTiles")

    println("Combination of words: ${generateAllCombinations(alphabet.take(4))}")
}

fun List<Letter>.getMeTiles(numberOfTiles: Int): List<Letter> {
    val shuffledTiles = this.shuffled()
    val actualNumberTiles = min(numberOfTiles, shuffledTiles.size)
    return shuffledTiles.take(actualNumberTiles)
}

fun createLetterTiles(count: Int, vararg values: Char): List<Letter> {
    return values.map { char ->
        (0 until count).map { Letter(char, -1) }
    }.flatten()
}

fun List<Letter>.extract7Letters(): List<Letter> {
    val shuffledAlphabet = this.shuffled()
    return shuffledAlphabet.take(7)
}

fun calculateWordPoints(word: String, alphabet: List<Letter>): Int {
    return word.toCharArray().sumOf { wordChar ->
        val alphabetChar = alphabet.first { it.value == wordChar }
        alphabetChar.points
    }
}

fun String.splitData(points: Int): List<Letter> {
    return this.split(",").map {
        val text = it.trim()
        Letter(text.toCharArray().first(), points)
    }
}

fun generateAllCombinations(letters: List<Letter>): List<Word> {
    // a b c -> a, ab, ac, b, ba, bc, c, ca, cb | abc, bca, 

    // take first letter and generate with each letter available
    //

    // 12, 13, 14, 123, 124,
    var resultArray = mutableListOf<Word>()

//    resultArray.add(Word(lett)[0])
    for (k in 0 until letters.size) {

    }
    for (i in 0 until letters.size) {
        val startOfWord = letters.take(i + 1).joinToString(separator = "") { it.value.toString() }
        for (j in 1 until letters.size) {
            resultArray.add(Word(startOfWord + letters[j].value.toString()))
        }
    }

    return resultArray
}