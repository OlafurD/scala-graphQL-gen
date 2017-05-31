import org.scalatest.FunSuite
import sangria.ast.{FieldDefinition, NotNullType, ObjectTypeDefinition}
import sangria.parser.QueryParser
import sangria.schema.Schema

import scala.meta._

class Playground extends FunSuite {

  test("sometimes you have to test things") {

    val schemaFromFile = scala.io.Source
      .fromURL(getClass.getResource("schema.graphql"))
      .mkString // todo relative value

    val parsedSchema = QueryParser.parse(schemaFromFile)
    val schemaAst    = Schema.buildFromAst(parsedSchema.get)

    parsedSchema.get.definitions.collect {
      case ObjectTypeDefinition(name,
                                interfaces,
                                fields,
                                directives,
                                comments,
                                trailingComments,
                                position) if name != "Query" && name != "Mutation" => {
       /* println(s"name is ${name}")
        println(s"interfaces are ${interfaces}")
        println(s"fields are ${fields}")
        println(s"directives are ${directives}")
        println(s"comments are ${comments}")
        println(s"trailing comments are ${trailingComments}")
        println(s"position is ${position}")*/

        val blu = fields collect {
          case FieldDefinition(fName, fType, fArguments, fDirectives, fComments, fPos) => {
            /*println(s"Field name ${fName}")
            println(fType.namedType.name)*/
            val t = fType match {
              case NotNullType(x, _) => x.namedType.name
              case nl => s"Option[${nl.namedType.name}]"
            }
            //println(t)
            s"$fName: $t"
          }
        }

        val ble = s"case class $name(${blu.mkString(", ")})"
        println(ble)
      }
    }

    assert(true)
  }

}
