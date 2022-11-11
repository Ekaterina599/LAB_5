package component

import csstype.Color
import react.FC
import react.Props
import react.StateSetter
import react.dom.html.InputType
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
//Создайте компонент, который позволит выбрать цвет для каждой оценки

external interface ColorGrades: Props {
    var listcolor: List<Color>
    var setColor: (Int, Color) -> Unit
}

val CColor = FC<ColorGrades>("ChangeColor") { props ->
    listOf(1,2,3,4,5).mapIndexed { _index, grade ->
        label { +grade.toString() }
        input {
            type = InputType.color
            value = props.listcolor[_index].toString()
            onChange = { props.setColor(_index, Color(it.target.value)) }
        }
    }
}