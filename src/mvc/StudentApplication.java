package mvc;

import mvc.view.StudView;

public class StudentApplication {
    public static void main(String[] args) {
        try {
            new StudView();  // Launch the UI
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
