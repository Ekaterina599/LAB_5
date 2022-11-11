package component
import csstype.PropertyName
import data.studentList
import emotion.css.css
import emotion.react.css
import react.*
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.select

external interface GradeProps: Props {
    var grade: Int
    var setGrade: (Int) -> Unit
}

val CGrade =FC<GradeProps>("Grade") { props ->
    val list = listOf(1,2,3,4,5)
    select {
        css{ backgroundColor = useContext(colorContext)[props.grade-1] }
        onChange = { props.setGrade(it.target.value.toInt()) }
        value = props.grade.toString()
        list.mapIndexed { index, it ->
            option {
                css{ backgroundColor = useContext(colorContext)[index]}
                +it.toString()
            }
        }
    }
}



