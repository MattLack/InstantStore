package ufrpe.mobile.instantstore.model


data class Photo(val img: String?, val txt: String?, val author: String?, var id: String?){
}



/*
data class Photo{

    var img: String? = null
    var txt: String?= null
    var author: String?= null
    var id: String?= null

    constructor() {}

    constructor(img: String, txt: String, author: String, id: String){
        this.id = id
        this.img = img
        this.author = author
        this.txt = txt
    }

    constructor(img: String?, txt: String?, author: String?){
        this.img = img
        this.author = author
        this.txt = txt
    }

    fun toMap(): Map<String, Any> {

        val result = HashMap<String, Any>()
        result.put("img", img!!)
        result.put("id", id!!)
        result.put("author", author!!)
        result.put("txt", txt!!)

        return result
    }

}*/
