import lexer.*;

class LexerTest extends munit.FunSuite {
 test("Lexer gives correct tokens"){
    val result = Lexer.lex("1 + 2", List())
    val expected = List(Token.NUMBER(1), Token.PLUS, Token.NUMBER(2))

    assertEquals(result, expected)
 }

 test("Empty input simply returns EOI (End of input)") {
    val result = Lexer.lex("", List())
    val expected = List()

    assertEquals(result, expected)

    val result2 = Lexer.lex("   ", List())
    assertEquals(result2, expected)
 }

 test("All types of expression"){
    val result = Lexer.lex("1+2-3*4/(5-6)", List())
    val expected = List(
        Token.NUMBER(1),
        Token.PLUS,
        Token.NUMBER(2),
        Token.SUBTRACT,
        Token.NUMBER(3),
        Token.MULTIPLY,
        Token.NUMBER(4),
        Token.DIVIDE,
        Token.LPAREN,
        Token.NUMBER(5),
        Token.SUBTRACT,
        Token.NUMBER(6),
        Token.RPAREN,
    )
 }
}
