package Model

class Repository {

    var employees: MutableList<Employee>? = null

    fun populateList(): MutableList<Employee>? {
        val firstEmployee =
            Employee(1,"Flavio Sampson", "fsampson30@gmail.com", "Mobile Developer", "2199747878","","")
        val secondEmployee =
            Employee(2,"Sergio Ruy", "sergio@email.com", "Backend Developer", "5587746546","","")
        val thirdEmployee =
            Employee(3,"Joao Silva", "joao@email.com", "Frontend Developer", "5522447789","","")
        val fourthEmployee =
            Employee(4,"Fernanda Sampson", "fernanda@email.com", "Mobile Developer", "5588797654654", "","")
        val fifthEmployee =
            Employee(5, "Natalia Sampson", "natalia@email.com", "Mobile Developer", "554477987987654","","")
        employees?.add(firstEmployee)
        employees?.add(secondEmployee)
        employees?.add(thirdEmployee)
        employees?.add(fourthEmployee)
        employees?.add(fifthEmployee)
        return employees
    }

}
