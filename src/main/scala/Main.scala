import lexer.Lexer
import parser.Parser
import evaluator.Evaluator
import scala.util.{Success, Failure}

@main
def hello(args: Array[String]): Unit =
  val tokens = Lexer.lex(args.apply(0), List())
  val parsed = Parser.parse(tokens)
  val result = Evaluator.eval(parsed);
  result match
    case Failure(_) => println("Unable to compute result")
    case Success(x) => println("Result: " + x.toString())


