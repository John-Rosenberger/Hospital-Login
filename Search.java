public class Search
{
    public int findID(String key, Manager m)
    {
    int low = 0;
    int mid;
    int high = m.numberOfPatients() - 1;
    while (low <= high) {
        mid = (low + high) / 2;
        if (m.getPatient(mid).getID().compareTo(key) == 0) 
        {
            return mid;
        }
        else if (m.getPatient(mid).getID().compareTo(key) > 0) 
        {
            high = mid + 1;
        }
        else if (m.getPatient(mid).getID().compareTo(key) < 0)
        {
            low = mid - 1;
        }
    }
    System.out.println("ID: -1");
    return -1;
    }
}