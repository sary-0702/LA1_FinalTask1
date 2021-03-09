package app.nunome.sary.finalproduct

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Memo (
        @PrimaryKey open var id: String = UUID.randomUUID().toString(),
        open var storeId : String = "",
        open var memo: String = ""
) : RealmObject()