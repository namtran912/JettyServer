package Model

/**
  * Created by CPU11179-local on 8/31/2016.
  */

import javax.xml.bind.annotation._

@XmlRootElement
class Person(var name: String) {

    def this() {
        this ("")
    }

    def getName: String = name
    def setName(name: String): Unit = this.name = name
}
