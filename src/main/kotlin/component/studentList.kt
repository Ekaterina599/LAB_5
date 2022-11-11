package component

import csstype.*
import data.Student
import data.studentList
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.tr
import kotlin.math.roundToInt


external interface StudentListProps : Props {
    var list: List<Student>
    var marked: List<Boolean>
    var clickItem: (Int) -> Unit
    var gradesList: List<Int>
    var setGradeList: (Int, Int) -> Unit
}

val CStudentList = FC<StudentListProps>("StudentList") { props ->
    props.list.mapIndexed { index, _student ->
        tr {
            td {
                css {
                    background = NamedColor.lightpink
                    padding = Padding(vertical = 10.px, horizontal = 10.px)
                    border = Border(width = 2.px, style = LineStyle.solid)
                }
                CStudent { student = _student }

                if (props.marked[index])
                    css { fontWeight = FontWeight.bold }

                onClick = { props.clickItem(index) }
            }
            td {
                css {
                    padding = Padding(vertical = 10.px, horizontal = 10.px)
                    border = Border(width = 2.px, style = LineStyle.solid)
                }
                CGrade {
                    grade = props.gradesList[index]
                    setGrade = { props.setGradeList(index, it) }
                }
            }
        }
    }
    tr {
        label { +"Средняя оценка: ${props.gradesList.average()}" }
    }
}
