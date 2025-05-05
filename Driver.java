import java.util.Scanner; 
//import java.util.ArrayList;
public class Driver
{
    //I decided it wouldn't make sense for a patient to be able to edit their treatment notes, so I didn't include that one.
    //In addition, I did not allow staff to access patient usernames or passwords because that's weird and I don't like it.
    public static Printer r = new Printer();
   
   public static void main(String[]args)
   {
        Scanner scnr = new Scanner (System.in);
        Manager m = null;
        try
        {
        m = loginAttempt(scnr);
        }
        catch(LoginException e)
        {
            System.out.println(e.getMessage());
        }

        String input;
        boolean exit = false;


        if (m != null)
        {
            for (int i = 0; i < m.numberOfPatients(); i++)
            {
                System.out.println(m.getPatient(i).toString());
            }
            while (exit == false)
            {
                System.out.println("\n\n=====================\nEnter a number to perform an action.");
                System.out.println("0 - Log out\n1 - View user info\n2 - Edit user info\n3 - View patient report");
                if (m.getPerms() == 1)
                {
                    System.out.println("4 - View patient info");
                }
                input = scnr.nextLine();
                System.out.println("\n");
                switch(input)
                {
                    case "0": 
                        exit = true;
                        System.out.println("Successfully logged out!");
                        break;
                    case "1": 
                        System.out.println(m.userInfo());
                        break;
                    case "2":
                        editInfo(scnr, m);
                        break;
                    case "3":
                        patientReport(scnr, m);
                        break;
                    case "4":
                        if (m.getPerms() == 1)
                        {
                            patientSearch(scnr, m);
                        }
                        break;
                }
                //A delay. It feels better to me this way.
                try 
                {
                    Thread.sleep(1500);
                } 
                catch (Exception e) 
                {
                    e.printStackTrace();
                }
            }
        }
   }

   public static Manager loginAttempt(Scanner scnr) throws LoginException
   {
        Login currentSession = new Login();
        Manager m = null;
        int count = 4;

        do 
        {
            String user;
            String pass;
            System.out.println("Username: ");
            user = scnr.nextLine();
            System.out.println("Password: ");
            pass = scnr.nextLine();
            try  
            {
                m = currentSession.logon(user, pass);
            }
            catch (LoginException e)
            {
                System.out.println(e.getMessage());
                System.out.println((count - 1)+" attempts left");
            }
            count--;
            if (count == 0)
            {
                throw new LoginException("Please try again in an hour.");
            }
        } while ((m == null));
        m.setPerms(currentSession.type);
        return m;
   }

   public static void editInfo(Scanner scnr, Manager m)
   {
    String inputNum = "";
    String inputWord;
    do
    {
    System.out.println("What would you like to edit?\n0 - Save and exit\n1 - Name\n2 - Password\n3 - email");
    if (m.getPerms() == 1)
    {
        System.out.println("4 - Department");
    }
    inputNum = scnr.next();
    scnr.nextLine();
        switch(inputNum)
        {
            case "0":
                System.out.println("Saving changes...");
                if (m.getPerms() == 1)
                {
                    r.saveFileS(m.getStaffList());
                }
                else 
                {
                    r.saveFileP(m.getPatientList());
                }
                return;
            case "1":
                System.out.println("Please enter your name.");
                inputWord = scnr.nextLine();
                m.setUserName(inputWord);
                System.out.println("\n\n"+m.userInfo());
                break;
            case "2":
                System.out.println("Please enter your new password");
                inputWord = scnr.nextLine();
                m.setUserPassword(inputWord);
                System.out.println("\n\n"+m.userInfo());
                break;
            case "3":
                System.out.println("Please enter your email address");
                inputWord = scnr.nextLine();
                m.setUserEmail(inputWord);
                System.out.println("\n\n"+m.userInfo());
                break;
            case "4":
                if (m.getPerms() == 1)
                {
                    System.out.println("Please enter your department");
                    inputWord = scnr.nextLine();
                    m.setUserDepartment(inputWord);
                    System.out.println("\n\n"+m.userInfo());
                }
                break;
        }
        //A delay. It feels better to me this way.
        try 
        {
            Thread.sleep(1500);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }    while (!inputNum.equals("0"));
   }

    //doctor admin methods
   public static void editPatientInfo(Scanner scnr, Manager m, int p)
   {
    String inputNum = "";
    String inputWord;
    do
    {
    System.out.println("What would you like to edit?\n0 - Save and exit\n1 - Name\n2 - email");
    System.out.println("3 - Treatment info");

    inputNum = scnr.next();
    scnr.nextLine();
        switch(inputNum)
        {
            case "0":
                System.out.println("Saving changes...");
                r.saveFileP(m.getPatientList());
                return;
            case "1":
                System.out.println("Please enter the patient's name.");
                inputWord = scnr.nextLine();
                m.setPatientName(p, inputWord);
                System.out.println("\n\n"+m.getPatient(p).toString());
                
                break;
            case "2":
                System.out.println("Please enter the patient's email address");
                inputWord = scnr.nextLine();
                m.setPatientEmail(p, inputWord);
                System.out.println("\n\n"+m.getPatient(p).toString());
                break;
            case "3":
                System.out.println("Please enter the patient's treatment notes");
                inputWord = scnr.nextLine();
                m.setPatientDetails(p, inputWord);
                System.out.println("\n\n"+m.getPatient(p).toString());
                break;
        }
        //A delay. It feels better to me this way.
        try 
        {
            Thread.sleep(1500);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }    while (!inputNum.equals("0"));
   }

   public static void patientSearch(Scanner scnr, Manager m)
   {    
        Search s = new Search();
        String input;
        int pNum;
        System.out.println("Please enter the ID of the patient");
        input = scnr.nextLine();
        pNum = s.findID(input, m);

        do
        {
            System.out.println("\n\n"+m.getPatient(pNum).toString());
            //A delay. It feels better to me this way.
            try 
            {
                Thread.sleep(1500);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            System.out.println("\n\n=====================\nEnter a number to perform an action.");
            System.out.println("0 - Go back\n1 - Edit patient info");
            input = scnr.next();
            scnr.nextLine();
            System.out.println("\n");
            switch(input)
            {
                case "0": 
                    break;
                case "1": 
                    editPatientInfo(scnr, m, pNum);
                    break;
            }
        } while (!input.equals("0"));
   }

   public static void patientReport(Scanner scnr, Manager m)
   {
    String file;
    String input;

    System.out.println("What kind of report would you like?\n0 - Cancel\n1 - List of patients\n2 - List of emails\n3 - Personal report");
    input = scnr.next();
    scnr.nextLine();
    do{
     switch (input)
     {
        case "0":
            break;
        case "1":
            System.out.println("Which file should the report be written to?");
            file = scnr.nextLine();
            System.out.println("How should the patient list be sorted?\n0 - Cancel\n1 - By ID\n2 - By name");
            do 
            {
                input = scnr.next();
                scnr.nextLine();
                switch (input)
                {
                    case "0":
                        break;
                    case "1":
                        r.print(m.getPatientList(), file);
                        System.out.println("Report successfully printed.");
                        input = "0";
                        break;
                    case "2":
                        r.printName(m.getPatientList(), file);
                        System.out.println("Report successfully printed.");
                        input = "0";
                        break;
                }
            } while (!input.equals("0"));
            break;
        case "2":
            System.out.println("Which file should the report be written to?");
            file = scnr.nextLine();
            r.printEmail(m.getPatientList(), m.getStaffList(), file);
            System.out.println("Report successfully printed.");
            input = "0";
            break;
        case "3":
            System.out.println("Which file should the report be written to?");
            file = scnr.nextLine();
            if (m.getPerms() == 1)
            {
                r.staffInfo(m.userInfo(), file);
            }
            else 
            {
                r.patientInfo(m.userInfo(), file);
            }
            System.out.println("Report successfully printed.");
            input = "0";
            break;
     }
    }while (!input.equals("0"));
   }
}
