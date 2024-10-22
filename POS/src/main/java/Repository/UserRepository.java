package Repository;

import Main.User;

import java.io.*;

public class UserRepository {


    public static User getUserCorrect(User user) {
        try {
            File file = new File("user.txt");
            if (!file.exists()) file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                if (user.getUserName().equals(lines[0]) && user.getPassword().equals(lines[1])) {
                    return user;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static boolean isValid(User user) {
        try {
            boolean isDuplicate = false;
            File file = new File("user.txt");
            if (!file.exists()) file.createNewFile();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lines = line.split(",");
                if (user.getUserName().equals(lines[0])) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                bw.write(user.getUserName() + "," + user.getPassword() + "\n");
                bw.close();
                return true;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


}
