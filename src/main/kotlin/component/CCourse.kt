package component

import data.Course
import react.FC
import react.Props
import react.StateSetter
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3

external interface CourseProps : Props {
    var course: Course
    var setCourse: StateSetter<Course>
}

val CCourse = FC<CourseProps>("course") { props ->
    div {
        h3 { +props.course.name }
        CStudentList {
            list = props.course.students
            marked = props.course.marked
            gradesList = props.course.grades
            setGradeList = {indexGrade, grade ->
                props.setCourse(
                    props.course.copy(
                        grades = props.course.grades.mapIndexed{index, gradeLast ->
                            if(index == indexGrade) grade else gradeLast
                        }
                    )
                )
            }
            clickItem = { clickIndex ->
                props.setCourse(
                    props.course.copy(
                        marked = props.course.marked.mapIndexed { index, mark ->
                            if (index == clickIndex) !mark else mark })
                )
            }
        }
    }
}