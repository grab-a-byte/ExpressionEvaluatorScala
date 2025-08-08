package evaluator

import parser.*
import lexer.Token
import scala.collection.mutable.Stack
import scala.util.{Try, Failure, Success}
import scala.util.boundary, boundary.break

object Evaluator {
  def eval(input: List[Token]): Try[Double] = {
    val stack = Stack[Double]()
    var result = boundary {
      for (i <- input) {
        i match {
          case Token.NUMBER(value) => stack.push(value)
          case Token.LPAREN   => break("No LParen allowed in evaluator input")
          case Token.RPAREN   => break("No RParen allowed in evaluator input")
          case Token.PLUS     => {
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b + a)
          }
          case Token.SUBTRACT => {
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b - a)
          }
          case Token.MULTIPLY => {
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b * a)
          }
          case Token.DIVIDE   => {
            val a = stack.pop()
            val b = stack.pop()
            stack.push(b / a)
          }
        }
      }
    }

    result match {
      case ()          => Success(stack.pop())
      case v: String => Failure(new Exception(v))
    }

  }
}
