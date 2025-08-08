import lexer.*
import parser.*

class ParserTest extends munit.FunSuite {
  test("Basic Addition Creates Corrrect Tree") {
    val tokens = Lexer.lex("1+2", List())
    val tree = Parser.parse(tokens)

    val expected = List(
      Token.NUMBER(1),
      Token.NUMBER(2),
      Token.PLUS
    )

    assertEquals(tree, expected)
  }

  test("Parenthesized Expression") {
    val tokens = Lexer.lex("1 * (2 + 3 / 4 - 7)", List())
    val tree = Parser.parse(tokens)

    val expected = List(
      Token.NUMBER(1),
      Token.NUMBER(2),
      Token.NUMBER(3),
      Token.NUMBER(4),
      Token.DIVIDE,
      Token.PLUS,
      Token.NUMBER(7),
      Token.SUBTRACT,
      Token.MULTIPLY
    )

    assertEquals(tree, expected)
  }
}
