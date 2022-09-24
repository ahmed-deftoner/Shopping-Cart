package Models

class Items {
    private var Name : String
    private var Description : String
    private var Price : Float
    private var Rating : Int
    private var Image : String
    private var Maker : String

    constructor(n: String, d: String, p: Float, r: Int, i: String, m: String) {
        Name = n
        Description = d
        Price = p
        Rating = r
        Image = i
        Maker = m
    }
}