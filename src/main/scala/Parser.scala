package parser

import lexer.*
import scala.annotation.tailrec
import scala.collection.mutable.{Stack, Queue}

object Parser {
  def parse(tokens: List[Token]): List[Token] = {
    var operators: Stack[Token] = Stack()
    var output: Queue[Token] = Queue()

    for (tok <- tokens) {
      tok match {
        case Token.NUMBER(_)  => output.enqueue(tok)
        case Token.LPAREN => operators.push(tok)
        case Token.RPAREN        =>  {
            val ops = operators.popWhile(op => op != Token.LPAREN)
            operators.pop() //pop the '(' token
            output.enqueueAll(ops)
        }
        case Token.PLUS | Token.SUBTRACT | Token.MULTIPLY | Token.DIVIDE => {
          val curr = getPrec(tok)
          while (!operators.isEmpty && curr < getPrec(operators.top)) {
            output.enqueue(operators.pop())
          }
          operators.push(tok)
        }
      }
    }


    while (!operators.isEmpty){
        val op = operators.pop()
        output.enqueue(op)
    }
    output.toList
  }

  private def getPrec(tok: Token): Int = {
    tok match {
      case Token.LPAREN   => 0
      case Token.SUBTRACT => 1
      case Token.PLUS     => 2
      case Token.MULTIPLY => 3
      case Token.DIVIDE   => 4
    }
  }
}
