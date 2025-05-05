import java.util.ArrayList;
import java.io.*;
public class Manager
{
    private int perms = 0;
    private int userNum;
    private ArrayList<Patient> patientList = new ArrayList<Patient>();
    private ArrayList<Staff> staffList = new ArrayList<Staff>();

    public Manager(int user, String pFile, String sFile)
    {
        patientList.add(new Patient(new String[] {"0", "Guest","SuperSecretGuestPassword","Guest","Guest","Guest"}));
        fillPatientList(readList(pFile));
        fillStaffList(readList(sFile));
        sortP(patientList);
        sortS(staffList);
        userNum = user;
    }

    public int getPerms()
    {
        return this.perms;
    }
    public void setPerms(int input)
    {
        this.perms = input;
    }
    
    public Patient getPatient(int num)
    {
        return patientList.get(num);
    }
    public void setPatient(String[] list)
    {
        Patient thisPatient = new Patient(list);
        patientList.add(thisPatient);
    }
    public int numberOfPatients()
    {
        return patientList.size();
    }
    public ArrayList<Patient> getPatientList()
    {
        return this.patientList;
    }

    public Staff getStaff(int num)
    {
        return staffList.get(num);
    }
    public void setStaff(String[] list)
    {
        Staff thisStaff = new Staff(list);
        staffList.add(thisStaff);
    }
    public int staffSize()
    {
        return staffList.size();
    }
    public ArrayList<Staff> getStaffList()
    {
        return this.staffList;
    }

    //editing own info
    public String userInfo()
    {
        if (perms == 1)
        {
            return "Username: " + staffList.get(userNum).getUserName() + "\nPassword: " + staffList.get(userNum).getPassword() + "\n" + staffList.get(userNum).toString();
        }
        else 
        {
            return "Username: " + patientList.get(userNum).getUserName() + "\nPassword: " + patientList.get(userNum).getPassword() + "\n" + patientList.get(userNum).toString();
        }
    }
    public void setUserName(String input)
    {
        if (perms == 1)
        {
            staffList.get(userNum).setName(input);
        }
        else 
        {
            patientList.get(userNum).setName(input);
        }
    }
    public void setUserPassword(String input)
    {
        if (perms == 1)
        {
            staffList.get(userNum).setPassword(input);
        }
        else 
        {
            patientList.get(userNum).setPassword(input);
        }
    }
    public void setUserEmail(String input)
    {
        if (perms == 1)
        {
            staffList.get(userNum).setEmail(input);
        }
        else 
        {
            patientList.get(userNum).setEmail(input);
        }
    }
    public void setUserDepartment(String input)
    {
        staffList.get(userNum).setDepartment(input);
    }

    //editing patient info
    public void setPatientName(int p, String input)
    {
        patientList.get(p).setName(input);
    }
    public void setPatientEmail(int p, String input)
    {
        patientList.get(p).setEmail(input);
    }
    public void setPatientDetails(int p, String input)
    {
        patientList.get(p).setDetails(input);
    }

    public void fillPatientList(ArrayList<String> arr)
    {
        String[] list;
        for (int i = 0; i < arr.size(); i++)
        {
            list = arr.get(i).split(",");
            setPatient(list);
        }
    }
    public void fillStaffList(ArrayList<String> arr)
    {
        String[] list;
        for (int i = 0; i < arr.size(); i++)
        {
            list = arr.get(i).split(",");
            setStaff(list);
        }
    }

    public ArrayList<String> readList (String file)
    {
        ArrayList<String> list = new ArrayList<String>();
        String line;
        try 
        {
            FileReader fileToRead = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileToRead);
            do
            {
                line = reader.readLine();
                if(line != null)
                {
                    list .add(line);
                }
            } while (line != null);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public void sortP(ArrayList<Patient> list)
    {
        Patient temp;
        int least;
        for (int i = 0; i < list.size() - 1; i++)
        {
            least = i;
            for (int j = i + 1; j < list.size(); j++)
            {
                if (Integer.parseInt(list.get(j).getID()) < Integer.parseInt(list.get(least).getID()))
                {
                    least = j;
                }
            }
            temp = list.get(i);
            list.set(i, list.get(least));
            list.set(least, temp);
        }
    }
    public void sortS(ArrayList<Staff> list)
    {
        Staff temp;
        int least;
        for (int i = 0; i < list.size() - 1; i++)
        {
            least = i;
            for (int j = i + 1; j < list.size(); j++)
            {
                if (Integer.parseInt(list.get(j).getID()) < Integer.parseInt(list.get(least).getID()))
                {
                    least = j;
                }
            }
            temp = list.get(i);
            list.set(i, list.get(least));
            list.set(least, temp);
        }
    }
}