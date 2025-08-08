import lexer.Lexer
import parser.Parser
import evaluator.Evaluator
class EvaluatorTest extends munit.FunSuite  {
    test("1 + 2 = 3") {
        val tokens = Lexer.lex("1 + 2", List())
        val parsed = Parser.parse(tokens)
        val evaluated = Evaluator.eval(parsed)

        assertEquals(evaluated.get, 3.0)
    }

    test("1 + (10-4 /2) = 9") {
        val tokens = Lexer.lex("1 + (10-4 /2)", List())
        val parsed = Parser.parse(tokens)
        val evaluated = Evaluator.eval(parsed)

        assertEquals(evaluated.get, 9.0)
    }

    test("1/2 = 0.5"){
        val tokens = Lexer.lex("1/2", List())
        val parsed = Parser.parse(tokens)
        val evaluated = Evaluator.eval(parsed)

        assertEquals(evaluated.get, 0.5)
    }
}
