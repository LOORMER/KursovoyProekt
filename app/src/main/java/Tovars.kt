class Tovars {
    var id = 0
    var name: String? = null
    var cost = 0
    var comments: String? = null
    var byTicket : Boolean = false

    constructor()
    constructor(id: Int, name: String?,cost : Int, comments: String?, byTicket:Boolean) {
        this.id = id
        this.cost = cost
        this.name = name
        this.comments = comments
        this.byTicket = byTicket
    }
}