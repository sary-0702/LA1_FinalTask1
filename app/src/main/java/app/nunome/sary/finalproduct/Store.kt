package app.nunome.sary.finalproduct

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Store(
    //@PrimaryKey open var id: String = UUID.randomUUID().toString(),
    open var storesname: String = "",
    open var foodtype: String = "",
    open var foodprice: String = "",
    open var check: String = ""
) : RealmObject()