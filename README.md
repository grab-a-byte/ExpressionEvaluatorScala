## sbt project compiled with Scala 3

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

For more information on the sbt-dotty plugin, see the
[scala3-example-project](https://github.com/scala/scala3-example-project/blob/main/README.md).

### Key parts
- Lexer simply makes tokens to remove whitespace and parse basic integers
- Parser uses Shunting Yard algorithm to turn the tokens into Reverse Polish Notation (RPN)
- Evaluator evaluates the RPN tokens to a final value


### Other ideas
- Instead of parsing a AST, just have a reduction that reduces them in order using match expressions and build new lists.
