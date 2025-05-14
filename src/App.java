public class App {
    public static void main(String[] args) throws Exception {
        // Ini adalah titik masuk aplikasi
        App app = new App();
        app.showSplashScreen();
    }

    // Splash screen
    public void showSplashScreen() {
        System.out.println("Welcome to the Application!");
        System.out.println("Loading...");
        try {
            Thread.sleep(2000); // Simulate loading time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Loading complete!");
    }
}
