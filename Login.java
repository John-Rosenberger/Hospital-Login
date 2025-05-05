import java.util.Scanner;
public class Login
{
    public int type;
    private String userFile = "patient.csv";
    private String adminFile = "medicalstaff.csv";

    public Manager logon(String user, String pass) throws LoginException
    {
        Manager m = new Manager(0, userFile, adminFile);
        int userVar = findMatch(m, user, pass);
        return new Manager(userVar, userFile, adminFile);
    }

    public int findMatch(Manager m, String user, String pass) throws LoginException
    {
        Staff s;
        Patient p;
            for (int i = 0; i < m.staffSize(); i++)
            {
                s = m.getStaff(i);
                if (s.getUserName().equals(user))
                {
                    if(s.getPassword().equals(pass))
                    {
                        type = 1;
                        return i;
                    }
                }
            }
            for (int i = 0; i < m.numberOfPatients(); i++)
            {
                p = m.getPatient(i);
                if (p.getUserName().equals(user))
                {
                    if (p.getPassword().equals(pass))
                    {
                        type = 0;
                        return i;
                    }
                }
            }
        throw new LoginException("Incorrect username or password entered. Please try again.");
    }
}