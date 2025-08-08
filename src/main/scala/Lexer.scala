package lexer

import scala.annotation.tailrec

enum Token {
    case NUMBER (value: Int)
    case LPAREN
    case RPAREN
    case PLUS
    case SUBTRACT
    case MULTIPLY
    case DIVIDE
}

object Lexer {
    @tailrec
    def lex(input: String, currentTokens: List[Token]) : List[Token] = {

        val whitespace = input.takeWhile(c => c == ' ' || c == '\t')

        if (input.length() == 0 || input.length() == whitespace.length()) {
            return currentTokens.reverse
        }

        var (token, count) = input.substring(whitespace.length(), whitespace.length()+1) match  {
            case ")" => (Token.RPAREN, 1)
            case "(" => (Token.LPAREN, 1)
            case "*" => (Token.MULTIPLY, 1)
            case "/" => (Token.DIVIDE, 1)
            case "+" => (Token.PLUS, 1)
            case "-" => (Token.SUBTRACT, 1)
            case "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8"| "9" => {
                val num = input.substring(whitespace.length()).takeWhile(p => p <= '9' && p >= '0')
                (Token.NUMBER(num.toInt), num.length())
            }
        }

        val newInput = input.substring(whitespace.length() + count, input.length())
        return lex(newInput, token::currentTokens)
    }
}
