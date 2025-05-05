public abstract class User
{
   private String ID;
   private String userName;
   private String password;
   private String name;
   private String email;

   public User()
   {
    ID = null;
    userName = null;
    password = null;
    name = null;
    email = null;
   }

   public User(String[] list)
   {
    ID = list[0];
    userName = list[1];
    password = list[2];
    name = list[3];
    email = list[4];
   }
      public User(User user)
   {
    ID = user.getID();
    userName = user.getUserName();
    password = user.getPassword();
    name = user.getName();
    email = user.getEmail();
   }

   public String getID()
   {
    return this.ID;
   }
   public String getUserName()
   {
    return this.userName;
   }

   public String getPassword()
   {
    return this.password;
   }
   public void setPassword(String input)
   {
    this.password = input;
   }

   public String getName()
   {
    return this.name;
   }
   public void setName(String input)
   {
    this.name = input;
   }

   public String getEmail()
   {
    return this.email;
   }
   public void setEmail(String input)
   {
    this.email = input;
   }

   public String toString()
   {
    return "ID: "+getID()+"\nName: "+getName()+"\nEmail: "+getEmail();
   }
}