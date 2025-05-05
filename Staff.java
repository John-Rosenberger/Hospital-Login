public class Staff extends User
{
    private String department;

    public Staff()
    {
        super();
    }
    public Staff(String[] list)
    {
        super(list);
        department = list[5];
    }
    public Staff(Staff user)
    {
        super(user);
        department = user.getDepartment();
    }

    public String getDepartment()
    {
        return this.department;
    }
    public void setDepartment(String input)
    {
        this.department = input;
    }
    
    public String toString()
    {
        return super.toString() + "\nDepartment: " + getDepartment();
    }
}