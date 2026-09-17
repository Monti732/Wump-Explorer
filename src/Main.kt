import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlinx.cli.default

fun main(args: Array<String>) {
  val parser = ArgParser("wump")
  
  val name by parser.option(
    ArgType.String,
    shortName = "n",
    description = "the name of the file name."
  ).default("World")
  
  parser.parse(args)
  
  println("Hello, $name")
}