import java.util.ArrayList;
import java.io.*;
public class Printer
{
    private String userFile = "patient.csv";
    private String adminFile = "medicalstaff.csv";

   public void printEmail(ArrayList<Patient> pArr, ArrayList<Staff> sArr, String file)
   {
      String temp;
      int least;
      ArrayList<String> email = new ArrayList<String>();

      for (int i = 0; i < pArr.size(); i++)
      {
        email.add(pArr.get(i).getEmail());
      }
      for (int i = 0; i < sArr.size(); i++)
      {
        email.add(sArr.get(i).getEmail());
      }

      for(int i = 0; i < email.size()-1; i++)
      {
        least = i;
        for(int j = i+1; j < email.size(); j++)
        {
            if (email.get(j).compareTo(email.get(least)) < 0)
            {
                least = j;
            }
        }

        temp = email.get(least);
        email.set(least, email.get(i));
        email.set(i, temp);
      }
      try
      {
        PrintWriter write = new PrintWriter(new FileWriter(file, true));
        for (int i = 0; i < email.size(); i++)
        {
            write.println(email.get(i));
        }
        write.close();
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }

   }

      public void printName(ArrayList<Patient> arr, String file)
   {
      Patient temp;
      int least;

      for(int i = 0; i < arr.size()-1; i++)
      {
        least = i;
        for(int j = i+1; j < arr.size(); j++)
        {
            if (arr.get(j).getName().compareTo(arr.get(least).getName()) < 0)
            {
                least = j;
            }
        }
        temp = arr.get(least);
        arr.set(least, arr.get(i));
        arr.set(i, temp);
      }
      this.print(arr, file);
   }

   public void print(ArrayList<Patient> arr, String file)
   {
      try
      {
        PrintWriter write = new PrintWriter(new FileWriter(file, true));
        for (int i = 0; i < arr.size(); i++)
        {
            write.println(arr.get(i).report());
        }
        write.close();
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
   }

   public void staffInfo(String user, String file)
   {
      try
      {
        PrintWriter write = new PrintWriter(new FileWriter(file, true));
        write.println("Account type: Staff\n"+user);
        write.close();
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
   }
   public void patientInfo(String user, String file)
   {
      try
      {
        PrintWriter write = new PrintWriter(new FileWriter(file, true));
        write.println("Account type: Patient\n"+user);
        write.close();
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }
   }

   public void saveFileS(ArrayList<Staff> list)
   {
        try 
        {
            PrintWriter write = new PrintWriter(new FileWriter(adminFile, false));
            for (int i = 0; i < list.size(); i++)
            {
                Staff s = new Staff(list.get(i));
                write.println(s.getID() + ","+s.getUserName()+","+s.getPassword()+","+s.getName()+","+s.getEmail()+","+s.getDepartment());
            }
            write.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
   }

      public void saveFileP(ArrayList<Patient> list)
   {
        try 
        {
            PrintWriter write = new PrintWriter(new FileWriter(userFile, false));
            for (int i = 0; i < list.size(); i++)
            {
                Patient s = new Patient(list.get(i));
                write.println(s.getID() + ","+s.getUserName()+","+s.getPassword()+","+s.getName()+","+s.getEmail()+","+s.getDetails());
            }
            write.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
   }
}