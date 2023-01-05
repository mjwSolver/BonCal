package com.visualprogrammingclass.boncal.helpers

import com.visualprogrammingclass.boncal.models.authentication.Region
import com.visualprogrammingclass.boncal.models.authentication.User

object statics {
    var token: String = ""
    var user: User = User(
        "", "", "", 0,
        false, "", "",
        Region(
            "", "", 0, "", ""
        ),
        "", 0.00)
//    var logged: Boolean = false
}
