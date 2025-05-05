public class Patient extends User
{
    private String details;

    public Patient()
    {
        super();
    }
    public Patient(String[] list)
    {
        super(list);
        details = list[5];
    }
    public Patient(Patient user)
    {
        super(user);
        details = user.getDetails();
    }

    public String getDetails()
    {
        return this.details;
    }
    public void setDetails(String input)
    {
        this.details = input;
    }
    public String toString()
    {
        return super.toString() + "\nTreatment notes: " + getDetails();

    }
    public String report()
    {
        return super.toString();

    }
}