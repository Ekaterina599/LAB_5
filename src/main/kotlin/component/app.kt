package component

import csstype.*
import csstype.PrintColorAdjust.Companion.exact
import data.*
import emotion.react.css
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ul
import react.router.Route
import react.router.Routes
import react.router.dom.HashRouter
import react.router.dom.Link

var colorContext = createContext<List<Color>>()
val shortOutput = createContext("Full")

val app = FC<Props>("App") {
    val (mode, setMode) = useState("Full")
    val courses = courseList.map { useState(it) }
    val progress = listOf("excellent student", "good student", "loser student")
    var ListColor by useState { listOf(1, 2, 3, 4, 5).map { Color("#0000ff") } }
    CColor {
        listcolor = ListColor
        setColor = { indx, clr ->
            ListColor = ListColor.mapIndexed { indexState, colorState ->
                if (indexState == indx) clr else colorState
            }
        }
    }
    CModePicker {
        _mode = mode
        _setMode = setMode
    }
    colorContext.Provider(ListColor) {
        shortOutput.Provider(mode) {
            HashRouter { //провайдер инф-и о маршруте
                 div {
                    css { display = Display.flex }
                    courses.map { (_course, _) ->
                        val name = _course.name
                        Link {
                            css { flex = Flex.minContent }
                            +"Course/$name"
                            to = "Course/$name"
                        }
                    }
                    progress.map {
                        val name = it
                        Link {
                            css { flex = Flex.minContent }
                            +"List/$name"
                            to = "List/$name"
                        }
                    }
                }
                Routes {
                    Route {
                        path = "Course/:name" //параметр маршрута для курса
                        element = CCoursePicker.create { this.courses = courses }
                    }
                    Route {
                        path = "List/:name" //параметр маршрута для курса
                        element = CGradesPicker.create { this.courseState = courses }
                    }
                }
            }
        }
    }
}